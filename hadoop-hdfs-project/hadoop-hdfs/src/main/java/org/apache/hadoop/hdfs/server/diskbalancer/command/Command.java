/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.hadoop.hdfs.server.diskbalancer.command;

import com.google.common.base.Preconditions;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.CommonConfigurationKeys;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DFSConfigKeys;
import org.apache.hadoop.hdfs.DFSUtil;
import org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocol;
import org.apache.hadoop.hdfs.server.diskbalancer.connectors.ClusterConnector;
import org.apache.hadoop.hdfs.server.diskbalancer.connectors.ConnectorFactory;
import org.apache.hadoop.hdfs.server.diskbalancer.datamodel.DiskBalancerCluster;
import org.apache.hadoop.hdfs.server.diskbalancer.datamodel.DiskBalancerDataNode;
import org.apache.hadoop.hdfs.tools.DiskBalancer;
import org.apache.hadoop.net.NetUtils;
import org.apache.hadoop.security.UserGroupInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Common interface for command handling.
 */
public abstract class Command extends Configured {
  static final Logger LOG = LoggerFactory.getLogger(Command.class);
  private Map<String, String> validArgs = new HashMap<>();
  private URI clusterURI;
  private FileSystem fs = null;
  private DiskBalancerCluster cluster = null;
  private int topNodes;

  private static final Path DEFAULT_LOG_DIR = new Path("/system/diskbalancer");

  private Path diskBalancerLogs;

  /**
   * Constructs a command.
   */
  public Command(Configuration conf) {
    super(conf);
    // These arguments are valid for all commands.
    addValidCommandParameters(DiskBalancer.HELP, "Help for this command");
    addValidCommandParameters("arg", "");
    topNodes = 0;
  }

  /**
   * Executes the Client Calls.
   *
   * @param cmd - CommandLine
   * @throws IOException
   * @throws URISyntaxException
   */
  public abstract void execute(CommandLine cmd) throws Exception;

  /**
   * Gets extended help for this command.
   *
   * @return Help Message
   */
  protected abstract String getHelp();

  /**
   * verifies user provided URL.
   *
   * @param uri - UrlString
   * @return URL
   * @throws URISyntaxException, MalformedURLException
   */
  protected URI verifyURI(String uri)
      throws URISyntaxException, MalformedURLException {
    if ((uri == null) || uri.isEmpty()) {
      throw new MalformedURLException(
          "A valid URI is needed to execute this command.");
    }
    return new URI(uri);
  }

  /**
   * Process the URI and return the cluster with nodes setup. This is used in
   * all commands.
   *
   * @param cmd - CommandLine
   * @return DiskBalancerCluster
   * @throws Exception
   */
  protected DiskBalancerCluster readClusterInfo(CommandLine cmd) throws
      Exception {
    Preconditions.checkNotNull(cmd);
    Preconditions
        .checkState(cmd.getOptionValue(DiskBalancer.NAMENODEURI) != null,
            "Required argument missing : uri");

    setClusterURI(verifyURI(cmd.getOptionValue(DiskBalancer.NAMENODEURI)));
    LOG.debug("using name node URI : {}", this.getClusterURI());
    ClusterConnector connector = ConnectorFactory.getCluster(this.clusterURI,
        getConf());

    cluster = new DiskBalancerCluster(connector);

    LOG.debug("Reading cluster info");
    cluster.readClusterInfo();
    return cluster;
  }

  /**
   * Setup the outpath.
   *
   * @param path - Path or null to use default path.
   * @throws IOException
   */
  protected void setOutputPath(String path) throws IOException {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd-HH-mm-ss");
    Date now = new Date();

    fs = FileSystem.get(getClusterURI(), getConf());
    if (path == null || path.isEmpty()) {
      if (getClusterURI().getScheme().startsWith("file")) {
        diskBalancerLogs = new Path(
            System.getProperty("user.dir") + DEFAULT_LOG_DIR.toString() +
                Path.SEPARATOR + format.format(now));
      } else {
        diskBalancerLogs = new Path(DEFAULT_LOG_DIR.toString() +
            Path.SEPARATOR + format.format(now));
      }
    } else {
      diskBalancerLogs = new Path(path);
    }
    if (fs.exists(diskBalancerLogs)) {
      LOG.error("Another Diskbalancer instance is running ? - Target " +
          "Directory already exists. {}", diskBalancerLogs);
      throw new IOException("Another DiskBalancer files already exist at the " +
          "target location. " + diskBalancerLogs.toString());
    }
    fs.mkdirs(diskBalancerLogs);
  }

  /**
   * Sets the nodes to process.
   *
   * @param node - Node
   */
  protected void setNodesToProcess(DiskBalancerDataNode node) {
    List<DiskBalancerDataNode> nodelist = new LinkedList<>();
    nodelist.add(node);
    setNodesToProcess(nodelist);
  }

  /**
   * Sets the list of Nodes to process.
   *
   * @param nodes Nodes.
   */
  protected void setNodesToProcess(List<DiskBalancerDataNode> nodes) {
    if (cluster == null) {
      throw new IllegalStateException("Set nodes to process invoked before " +
          "initializing cluster. Illegal usage.");
    }
    cluster.setNodesToProcess(nodes);
  }

  /**
   * Returns a DiskBalancer Node from the Cluster or null if not found.
   *
   * @param nodeName - can the hostname, IP address or UUID of the node.
   * @return - DataNode if found.
   */
  DiskBalancerDataNode getNode(String nodeName) {
    DiskBalancerDataNode node = null;
    if (nodeName == null || nodeName.isEmpty()) {
      return node;
    }
    if (cluster.getNodes().size() == 0) {
      return node;
    }

    node = cluster.getNodeByName(nodeName);
    if (node != null) {
      return node;
    }

    node = cluster.getNodeByIPAddress(nodeName);
    if (node != null) {
      return node;
    }
    node = cluster.getNodeByUUID(nodeName);
    return node;
  }

  /**
   * Gets the node set from a file or a string.
   *
   * @param listArg - String File URL or a comma separated list of node names.
   * @return Set of node names
   * @throws IOException
   */
  private Set<String> getNodeList(String listArg) throws IOException {
    URL listURL;
    String nodeData;
    Set<String> resultSet = new TreeSet<>();

    if ((listArg == null) || listArg.isEmpty()) {
      return resultSet;
    }
    if (listArg.startsWith("file://")) {
      listURL = new URL(listArg);
      byte[] data = Files.readAllBytes(Paths.get(listURL.getPath()));
      nodeData = new String(data, Charset.forName("UTF-8"));
    } else {
      nodeData = listArg;
    }

    String[] nodes = nodeData.split(",");
    Collections.addAll(resultSet, nodes);
    return resultSet;
  }

  /**
   * Verifies if the command line options are sane.
   *
   * @param commandName - Name of the command
   * @param cmd         - Parsed Command Line
   */
  protected void verifyCommandOptions(String commandName, CommandLine cmd) {
    @SuppressWarnings("unchecked")
    Iterator<Option> iter = cmd.iterator();
    while (iter.hasNext()) {
      Option opt = iter.next();
      if (!validArgs.containsKey(opt.getArgName())) {
        String errMessage = String
            .format("%nInvalid argument found for command %s : %s%n",
                commandName, opt.getArgName());
        StringBuilder validArguments = new StringBuilder();
        validArguments.append("Valid arguments are : %n");
        for (Map.Entry<String, String> args : validArgs.entrySet()) {
          String key = args.getKey();
          String desc = args.getValue();
          String s = String.format("\t %s : %s %n", key, desc);
          validArguments.append(s);
        }
        LOG.error(errMessage + validArguments.toString());
        throw new IllegalArgumentException("Invalid Arguments found.");
      }
    }
  }

  /**
   * Gets cluster URL.
   *
   * @return - URL
   */
  public URI getClusterURI() {
    return clusterURI;
  }

  /**
   * Set cluster URL.
   *
   * @param clusterURI - URL
   */
  public void setClusterURI(URI clusterURI) {
    this.clusterURI = clusterURI;
  }

  /**
   * Copied from DFSAdmin.java. -- Creates a connection to dataNode.
   *
   * @param datanode - dataNode.
   * @return ClientDataNodeProtocol
   * @throws IOException
   */
  public ClientDatanodeProtocol getDataNodeProxy(String datanode)
      throws IOException {
    InetSocketAddress datanodeAddr = NetUtils.createSocketAddr(datanode);

    // For datanode proxy the server principal should be DN's one.
    getConf().set(CommonConfigurationKeys.HADOOP_SECURITY_SERVICE_USER_NAME_KEY,
        getConf().get(DFSConfigKeys.DFS_DATANODE_KERBEROS_PRINCIPAL_KEY, ""));

    // Create the client
    ClientDatanodeProtocol dnProtocol =
        DFSUtil.createClientDatanodeProtocolProxy(datanodeAddr, getUGI(),
            getConf(), NetUtils.getSocketFactory(getConf(),
                ClientDatanodeProtocol
                    .class));
    return dnProtocol;
  }

  /**
   * Returns UGI.
   *
   * @return UserGroupInformation.
   * @throws IOException
   */
  private static UserGroupInformation getUGI()
      throws IOException {
    return UserGroupInformation.getCurrentUser();
  }

  /**
   * Returns a file created in the cluster.
   *
   * @param fileName - fileName to open.
   * @return OutputStream.
   */
  protected FSDataOutputStream create(String fileName) throws IOException {
    Preconditions.checkNotNull(fileName);
    if(fs == null) {
      fs = FileSystem.get(getConf());
    }
    return fs.create(new Path(this.diskBalancerLogs, fileName));
  }

  /**
   * Returns a InputStream to read data.
   */
  protected FSDataInputStream open(String fileName) throws IOException {
    Preconditions.checkNotNull(fileName);
    if(fs == null) {
      fs = FileSystem.get(getConf());
    }
    return  fs.open(new Path(fileName));
  }

  /**
   * Returns the output path where the plan and snapshot gets written.
   *
   * @return Path
   */
  protected Path getOutputPath() {
    return diskBalancerLogs;
  }

  /**
   * Adds valid params to the valid args table.
   *
   * @param key
   * @param desc
   */
  protected void addValidCommandParameters(String key, String desc) {
    validArgs.put(key, desc);
  }

  /**
   * Returns the cluster.
   *
   * @return Cluster.
   */
  protected DiskBalancerCluster getCluster() {
    return cluster;
  }

  /**
   * returns default top number of nodes.
   * @return default top number of nodes.
   */
  protected int getDefaultTop() {
    return DiskBalancer.DEFAULT_TOP;
  }

  /**
   * Put output line to log and string buffer.
   * */
  protected void recordOutput(final StrBuilder result,
      final String outputLine) {
    LOG.info(outputLine);
    result.appendln(outputLine);
  }

  /**
   * Parse top number of nodes to be processed.
   * @return top number of nodes to be processed.
   */
  protected int parseTopNodes(final CommandLine cmd, final StrBuilder result) {
    String outputLine = "";
    int nodes = 0;
    final String topVal = cmd.getOptionValue(DiskBalancer.TOP);
    if (StringUtils.isBlank(topVal)) {
      outputLine = String.format(
          "No top limit specified, using default top value %d.",
          getDefaultTop());
      LOG.info(outputLine);
      result.appendln(outputLine);
      nodes = getDefaultTop();
    } else {
      try {
        nodes = Integer.parseInt(topVal);
      } catch (NumberFormatException nfe) {
        outputLine = String.format(
            "Top limit input is not numeric, using default top value %d.",
            getDefaultTop());
        LOG.info(outputLine);
        result.appendln(outputLine);
        nodes = getDefaultTop();
      }
    }

    return Math.min(nodes, cluster.getNodes().size());
  }

  /**
   * Set top number of nodes to be processed.
   * */
  public void setTopNodes(int topNodes) {
    this.topNodes = topNodes;
  }

  /**
   * Get top number of nodes to be processed.
   * @return top number of nodes to be processed.
   * */
  public int getTopNodes() {
    return topNodes;
  }
}
