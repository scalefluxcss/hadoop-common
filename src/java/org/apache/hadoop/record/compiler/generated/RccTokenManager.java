/* Generated By:JavaCC: Do not edit this line. RccTokenManager.java */
/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.record.compiler.generated;
import org.apache.hadoop.record.compiler.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @deprecated Replaced by <a href="http://hadoop.apache.org/avro/">Avro</a>.
 */
@Deprecated
public class RccTokenManager implements RccConstants
{
  public  java.io.PrintStream debugStream = System.out;
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
  private final int jjMoveStringLiteralDfa0_1()
  {
    return jjMoveNfa_1(0, 0);
  }
  private final void jjCheckNAdd(int state)
  {
    if (jjrounds[state] != jjround)
      {
        jjstateSet[jjnewStateCnt++] = state;
        jjrounds[state] = jjround;
      }
  }
  private final void jjAddStates(int start, int end)
  {
    do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
    } while (start++ != end);
  }
  private final void jjCheckNAddTwoStates(int state1, int state2)
  {
    jjCheckNAdd(state1);
    jjCheckNAdd(state2);
  }
  private final void jjCheckNAddStates(int start, int end)
  {
    do {
      jjCheckNAdd(jjnextStates[start]);
    } while (start++ != end);
  }
  private final void jjCheckNAddStates(int start)
  {
    jjCheckNAdd(jjnextStates[start]);
    jjCheckNAdd(jjnextStates[start + 1]);
  }
  private final int jjMoveNfa_1(int startState, int curPos)
  {
    int[] nextStates;
    int startsAt = 0;
    jjnewStateCnt = 3;
    int i = 1;
    jjstateSet[0] = startState;
    int j, kind = 0x7fffffff;
    for (;;)
      {
        if (++jjround == 0x7fffffff)
          ReInitRounds();
        if (curChar < 64)
          {
            long l = 1L << curChar;
            MatchLoop: do
              {
                switch(jjstateSet[--i])
                  {
                  case 0:
                    if ((0x2400L & l) != 0L)
                      {
                        if (kind > 6)
                          kind = 6;
                      }
                    if (curChar == 13)
                      jjstateSet[jjnewStateCnt++] = 1;
                    break;
                  case 1:
                    if (curChar == 10 && kind > 6)
                      kind = 6;
                    break;
                  case 2:
                    if (curChar == 13)
                      jjstateSet[jjnewStateCnt++] = 1;
                    break;
                  default : break;
                  }
              } while(i != startsAt);
          }
        else if (curChar < 128)
          {
            long l = 1L << (curChar & 077);
            MatchLoop: do
              {
                switch(jjstateSet[--i])
                  {
                  default : break;
                  }
              } while(i != startsAt);
          }
        else
          {
            int i2 = (curChar & 0xff) >> 6;
            long l2 = 1L << (curChar & 077);
            MatchLoop: do
              {
                switch(jjstateSet[--i])
                  {
                  default : break;
                  }
              } while(i != startsAt);
          }
        if (kind != 0x7fffffff)
          {
            jjmatchedKind = kind;
            jjmatchedPos = curPos;
            kind = 0x7fffffff;
          }
        ++curPos;
        if ((i = jjnewStateCnt) == (startsAt = 3 - (jjnewStateCnt = startsAt)))
          return curPos;
        try { curChar = input_stream.readChar(); }
        catch(java.io.IOException e) { return curPos; }
      }
  }
  private final int jjStopStringLiteralDfa_0(int pos, long active0)
  {
    switch (pos)
      {
      case 0:
        if ((active0 & 0xfff800L) != 0L)
          {
            jjmatchedKind = 32;
            return 4;
          }
        return -1;
      case 1:
        if ((active0 & 0xfff800L) != 0L)
          {
            jjmatchedKind = 32;
            jjmatchedPos = 1;
            return 4;
          }
        return -1;
      case 2:
        if ((active0 & 0x7ef800L) != 0L)
          {
            jjmatchedKind = 32;
            jjmatchedPos = 2;
            return 4;
          }
        if ((active0 & 0x810000L) != 0L)
          return 4;
        return -1;
      case 3:
        if ((active0 & 0x24000L) != 0L)
          return 4;
        if ((active0 & 0x7cb800L) != 0L)
          {
            jjmatchedKind = 32;
            jjmatchedPos = 3;
            return 4;
          }
        return -1;
      case 4:
        if ((active0 & 0x41000L) != 0L)
          return 4;
        if ((active0 & 0x78a800L) != 0L)
          {
            jjmatchedKind = 32;
            jjmatchedPos = 4;
            return 4;
          }
        return -1;
      case 5:
        if ((active0 & 0x680800L) != 0L)
          return 4;
        if ((active0 & 0x10a000L) != 0L)
          {
            jjmatchedKind = 32;
            jjmatchedPos = 5;
            return 4;
          }
        return -1;
      default :
        return -1;
      }
  }
  private final int jjStartNfa_0(int pos, long active0)
  {
    return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
  }
  private final int jjStopAtPos(int pos, int kind)
  {
    jjmatchedKind = kind;
    jjmatchedPos = pos;
    return pos + 1;
  }
  private final int jjStartNfaWithStates_0(int pos, int kind, int state)
  {
    jjmatchedKind = kind;
    jjmatchedPos = pos;
    try { curChar = input_stream.readChar(); }
    catch(java.io.IOException e) { return pos + 1; }
    return jjMoveNfa_0(state, pos + 1);
  }
  private final int jjMoveStringLiteralDfa0_0()
  {
    switch(curChar)
      {
      case 44:
        return jjStopAtPos(0, 29);
      case 46:
        return jjStopAtPos(0, 30);
      case 47:
        return jjMoveStringLiteralDfa1_0(0x120L);
      case 59:
        return jjStopAtPos(0, 28);
      case 60:
        return jjStopAtPos(0, 26);
      case 62:
        return jjStopAtPos(0, 27);
      case 98:
        return jjMoveStringLiteralDfa1_0(0x20c000L);
      case 99:
        return jjMoveStringLiteralDfa1_0(0x1000L);
      case 100:
        return jjMoveStringLiteralDfa1_0(0x80000L);
      case 102:
        return jjMoveStringLiteralDfa1_0(0x40000L);
      case 105:
        return jjMoveStringLiteralDfa1_0(0x12000L);
      case 108:
        return jjMoveStringLiteralDfa1_0(0x20000L);
      case 109:
        return jjMoveStringLiteralDfa1_0(0x800800L);
      case 117:
        return jjMoveStringLiteralDfa1_0(0x100000L);
      case 118:
        return jjMoveStringLiteralDfa1_0(0x400000L);
      case 123:
        return jjStopAtPos(0, 24);
      case 125:
        return jjStopAtPos(0, 25);
      default :
        return jjMoveNfa_0(0, 0);
      }
  }
  private final int jjMoveStringLiteralDfa1_0(long active0)
  {
    try { curChar = input_stream.readChar(); }
    catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
    }
    switch(curChar)
      {
      case 42:
        if ((active0 & 0x100L) != 0L)
          return jjStopAtPos(1, 8);
        break;
      case 47:
        if ((active0 & 0x20L) != 0L)
          return jjStopAtPos(1, 5);
        break;
      case 97:
        return jjMoveStringLiteralDfa2_0(active0, 0x800000L);
      case 101:
        return jjMoveStringLiteralDfa2_0(active0, 0x400000L);
      case 108:
        return jjMoveStringLiteralDfa2_0(active0, 0x41000L);
      case 110:
        return jjMoveStringLiteralDfa2_0(active0, 0x12000L);
      case 111:
        return jjMoveStringLiteralDfa2_0(active0, 0xa8800L);
      case 115:
        return jjMoveStringLiteralDfa2_0(active0, 0x100000L);
      case 117:
        return jjMoveStringLiteralDfa2_0(active0, 0x200000L);
      case 121:
        return jjMoveStringLiteralDfa2_0(active0, 0x4000L);
      default :
        break;
      }
    return jjStartNfa_0(0, active0);
  }
  private final int jjMoveStringLiteralDfa2_0(long old0, long active0)
  {
    if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0); 
    try { curChar = input_stream.readChar(); }
    catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
    }
    switch(curChar)
      {
      case 97:
        return jjMoveStringLiteralDfa3_0(active0, 0x1000L);
      case 99:
        return jjMoveStringLiteralDfa3_0(active0, 0x402000L);
      case 100:
        return jjMoveStringLiteralDfa3_0(active0, 0x800L);
      case 102:
        return jjMoveStringLiteralDfa3_0(active0, 0x200000L);
      case 110:
        return jjMoveStringLiteralDfa3_0(active0, 0x20000L);
      case 111:
        return jjMoveStringLiteralDfa3_0(active0, 0x48000L);
      case 112:
        if ((active0 & 0x800000L) != 0L)
          return jjStartNfaWithStates_0(2, 23, 4);
        break;
      case 116:
        if ((active0 & 0x10000L) != 0L)
          return jjStartNfaWithStates_0(2, 16, 4);
        return jjMoveStringLiteralDfa3_0(active0, 0x104000L);
      case 117:
        return jjMoveStringLiteralDfa3_0(active0, 0x80000L);
      default :
        break;
      }
    return jjStartNfa_0(1, active0);
  }
  private final int jjMoveStringLiteralDfa3_0(long old0, long active0)
  {
    if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0); 
    try { curChar = input_stream.readChar(); }
    catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
    }
    switch(curChar)
      {
      case 97:
        return jjMoveStringLiteralDfa4_0(active0, 0x40000L);
      case 98:
        return jjMoveStringLiteralDfa4_0(active0, 0x80000L);
      case 101:
        if ((active0 & 0x4000L) != 0L)
          return jjStartNfaWithStates_0(3, 14, 4);
        break;
      case 102:
        return jjMoveStringLiteralDfa4_0(active0, 0x200000L);
      case 103:
        if ((active0 & 0x20000L) != 0L)
          return jjStartNfaWithStates_0(3, 17, 4);
        break;
      case 108:
        return jjMoveStringLiteralDfa4_0(active0, 0xa000L);
      case 114:
        return jjMoveStringLiteralDfa4_0(active0, 0x100000L);
      case 115:
        return jjMoveStringLiteralDfa4_0(active0, 0x1000L);
      case 116:
        return jjMoveStringLiteralDfa4_0(active0, 0x400000L);
      case 117:
        return jjMoveStringLiteralDfa4_0(active0, 0x800L);
      default :
        break;
      }
    return jjStartNfa_0(2, active0);
  }
  private final int jjMoveStringLiteralDfa4_0(long old0, long active0)
  {
    if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0); 
    try { curChar = input_stream.readChar(); }
    catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
    }
    switch(curChar)
      {
      case 101:
        return jjMoveStringLiteralDfa5_0(active0, 0x208000L);
      case 105:
        return jjMoveStringLiteralDfa5_0(active0, 0x100000L);
      case 108:
        return jjMoveStringLiteralDfa5_0(active0, 0x80800L);
      case 111:
        return jjMoveStringLiteralDfa5_0(active0, 0x400000L);
      case 115:
        if ((active0 & 0x1000L) != 0L)
          return jjStartNfaWithStates_0(4, 12, 4);
        break;
      case 116:
        if ((active0 & 0x40000L) != 0L)
          return jjStartNfaWithStates_0(4, 18, 4);
        break;
      case 117:
        return jjMoveStringLiteralDfa5_0(active0, 0x2000L);
      default :
        break;
      }
    return jjStartNfa_0(3, active0);
  }
  private final int jjMoveStringLiteralDfa5_0(long old0, long active0)
  {
    if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0); 
    try { curChar = input_stream.readChar(); }
    catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
    }
    switch(curChar)
      {
      case 97:
        return jjMoveStringLiteralDfa6_0(active0, 0x8000L);
      case 100:
        return jjMoveStringLiteralDfa6_0(active0, 0x2000L);
      case 101:
        if ((active0 & 0x800L) != 0L)
          return jjStartNfaWithStates_0(5, 11, 4);
        else if ((active0 & 0x80000L) != 0L)
          return jjStartNfaWithStates_0(5, 19, 4);
        break;
      case 110:
        return jjMoveStringLiteralDfa6_0(active0, 0x100000L);
      case 114:
        if ((active0 & 0x200000L) != 0L)
          return jjStartNfaWithStates_0(5, 21, 4);
        else if ((active0 & 0x400000L) != 0L)
          return jjStartNfaWithStates_0(5, 22, 4);
        break;
      default :
        break;
      }
    return jjStartNfa_0(4, active0);
  }
  private final int jjMoveStringLiteralDfa6_0(long old0, long active0)
  {
    if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0); 
    try { curChar = input_stream.readChar(); }
    catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
    }
    switch(curChar)
      {
      case 101:
        if ((active0 & 0x2000L) != 0L)
          return jjStartNfaWithStates_0(6, 13, 4);
        break;
      case 103:
        if ((active0 & 0x100000L) != 0L)
          return jjStartNfaWithStates_0(6, 20, 4);
        break;
      case 110:
        if ((active0 & 0x8000L) != 0L)
          return jjStartNfaWithStates_0(6, 15, 4);
        break;
      default :
        break;
      }
    return jjStartNfa_0(5, active0);
  }
  static final long[] jjbitVec0 = {
    0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
  };
  private final int jjMoveNfa_0(int startState, int curPos)
  {
    int[] nextStates;
    int startsAt = 0;
    jjnewStateCnt = 5;
    int i = 1;
    jjstateSet[0] = startState;
    int j, kind = 0x7fffffff;
    for (;;)
      {
        if (++jjround == 0x7fffffff)
          ReInitRounds();
        if (curChar < 64)
          {
            long l = 1L << curChar;
            MatchLoop: do
              {
                switch(jjstateSet[--i])
                  {
                  case 0:
                    if (curChar == 34)
                      jjCheckNAdd(1);
                    break;
                  case 1:
                    if ((0xfffffffbffffffffL & l) != 0L)
                      jjCheckNAddTwoStates(1, 2);
                    break;
                  case 2:
                    if (curChar == 34 && kind > 31)
                      kind = 31;
                    break;
                  case 4:
                    if ((0x3ff000000000000L & l) == 0L)
                      break;
                    if (kind > 32)
                      kind = 32;
                    jjstateSet[jjnewStateCnt++] = 4;
                    break;
                  default : break;
                  }
              } while(i != startsAt);
          }
        else if (curChar < 128)
          {
            long l = 1L << (curChar & 077);
            MatchLoop: do
              {
                switch(jjstateSet[--i])
                  {
                  case 0:
                    if ((0x7fffffe07fffffeL & l) == 0L)
                      break;
                    if (kind > 32)
                      kind = 32;
                    jjCheckNAdd(4);
                    break;
                  case 1:
                    jjAddStates(0, 1);
                    break;
                  case 4:
                    if ((0x7fffffe87fffffeL & l) == 0L)
                      break;
                    if (kind > 32)
                      kind = 32;
                    jjCheckNAdd(4);
                    break;
                  default : break;
                  }
              } while(i != startsAt);
          }
        else
          {
            int i2 = (curChar & 0xff) >> 6;
            long l2 = 1L << (curChar & 077);
            MatchLoop: do
              {
                switch(jjstateSet[--i])
                  {
                  case 1:
                    if ((jjbitVec0[i2] & l2) != 0L)
                      jjAddStates(0, 1);
                    break;
                  default : break;
                  }
              } while(i != startsAt);
          }
        if (kind != 0x7fffffff)
          {
            jjmatchedKind = kind;
            jjmatchedPos = curPos;
            kind = 0x7fffffff;
          }
        ++curPos;
        if ((i = jjnewStateCnt) == (startsAt = 5 - (jjnewStateCnt = startsAt)))
          return curPos;
        try { curChar = input_stream.readChar(); }
        catch(java.io.IOException e) { return curPos; }
      }
  }
  private final int jjMoveStringLiteralDfa0_2()
  {
    switch(curChar)
      {
      case 42:
        return jjMoveStringLiteralDfa1_2(0x200L);
      default :
        return 1;
      }
  }
  private final int jjMoveStringLiteralDfa1_2(long active0)
  {
    try { curChar = input_stream.readChar(); }
    catch(java.io.IOException e) {
      return 1;
    }
    switch(curChar)
      {
      case 47:
        if ((active0 & 0x200L) != 0L)
          return jjStopAtPos(1, 9);
        break;
      default :
        return 2;
      }
    return 2;
  }
  static final int[] jjnextStates = {
    1, 2, 
  };
  public static final String[] jjstrLiteralImages = {
    "", null, null, null, null, null, null, null, null, null, null, 
    "\155\157\144\165\154\145", "\143\154\141\163\163", "\151\156\143\154\165\144\145", "\142\171\164\145", 
    "\142\157\157\154\145\141\156", "\151\156\164", "\154\157\156\147", "\146\154\157\141\164", 
    "\144\157\165\142\154\145", "\165\163\164\162\151\156\147", "\142\165\146\146\145\162", 
    "\166\145\143\164\157\162", "\155\141\160", "\173", "\175", "\74", "\76", "\73", "\54", "\56", null, null, };
  public static final String[] lexStateNames = {
    "DEFAULT", 
    "WithinOneLineComment", 
    "WithinMultiLineComment", 
  };
  public static final int[] jjnewLexState = {
    -1, -1, -1, -1, -1, 1, 0, -1, 2, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
    -1, -1, -1, -1, -1, -1, -1, -1, 
  };
  static final long[] jjtoToken = {
    0x1fffff801L, 
  };
  static final long[] jjtoSkip = {
    0x37eL, 
  };
  static final long[] jjtoSpecial = {
    0x360L, 
  };
  static final long[] jjtoMore = {
    0x480L, 
  };
  protected SimpleCharStream input_stream;
  private final int[] jjrounds = new int[5];
  private final int[] jjstateSet = new int[10];
  StringBuffer image;
  int jjimageLen;
  int lengthOfMatch;
  protected char curChar;
  public RccTokenManager(SimpleCharStream stream){
    if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
    input_stream = stream;
  }
  public RccTokenManager(SimpleCharStream stream, int lexState){
    this(stream);
    SwitchTo(lexState);
  }
  public void ReInit(SimpleCharStream stream)
  {
    jjmatchedPos = jjnewStateCnt = 0;
    curLexState = defaultLexState;
    input_stream = stream;
    ReInitRounds();
  }
  private final void ReInitRounds()
  {
    int i;
    jjround = 0x80000001;
    for (i = 5; i-- > 0;)
      jjrounds[i] = 0x80000000;
  }
  public void ReInit(SimpleCharStream stream, int lexState)
  {
    ReInit(stream);
    SwitchTo(lexState);
  }
  public void SwitchTo(int lexState)
  {
    if (lexState >= 3 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
    else
      curLexState = lexState;
  }

  protected Token jjFillToken()
  {
    Token t = Token.newToken(jjmatchedKind);
    t.kind = jjmatchedKind;
    String im = jjstrLiteralImages[jjmatchedKind];
    t.image = (im == null) ? input_stream.GetImage() : im;
    t.beginLine = input_stream.getBeginLine();
    t.beginColumn = input_stream.getBeginColumn();
    t.endLine = input_stream.getEndLine();
    t.endColumn = input_stream.getEndColumn();
    return t;
  }

  int curLexState = 0;
  int defaultLexState = 0;
  int jjnewStateCnt;
  int jjround;
  int jjmatchedPos;
  int jjmatchedKind;

  public Token getNextToken() 
  {
    int kind;
    Token specialToken = null;
    Token matchedToken;
    int curPos = 0;

    EOFLoop :
      for (;;)
        {   
          try   
            {     
              curChar = input_stream.BeginToken();
            }     
          catch(java.io.IOException e)
            {        
              jjmatchedKind = 0;
              matchedToken = jjFillToken();
              matchedToken.specialToken = specialToken;
              return matchedToken;
            }
          image = null;
          jjimageLen = 0;

          for (;;)
            {
              switch(curLexState)
                {
                case 0:
                  try { input_stream.backup(0);
                  while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
                    curChar = input_stream.BeginToken();
                  }
                  catch (java.io.IOException e1) { continue EOFLoop; }
                  jjmatchedKind = 0x7fffffff;
                  jjmatchedPos = 0;
                  curPos = jjMoveStringLiteralDfa0_0();
                  break;
                case 1:
                  jjmatchedKind = 0x7fffffff;
                  jjmatchedPos = 0;
                  curPos = jjMoveStringLiteralDfa0_1();
                  if (jjmatchedPos == 0 && jjmatchedKind > 7)
                    {
                      jjmatchedKind = 7;
                    }
                  break;
                case 2:
                  jjmatchedKind = 0x7fffffff;
                  jjmatchedPos = 0;
                  curPos = jjMoveStringLiteralDfa0_2();
                  if (jjmatchedPos == 0 && jjmatchedKind > 10)
                    {
                      jjmatchedKind = 10;
                    }
                  break;
                }
              if (jjmatchedKind != 0x7fffffff)
                {
                  if (jjmatchedPos + 1 < curPos)
                    input_stream.backup(curPos - jjmatchedPos - 1);
                  if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
                    {
                      matchedToken = jjFillToken();
                      matchedToken.specialToken = specialToken;
                      if (jjnewLexState[jjmatchedKind] != -1)
                        curLexState = jjnewLexState[jjmatchedKind];
                      return matchedToken;
                    }
                  else if ((jjtoSkip[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
                    {
                      if ((jjtoSpecial[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
                        {
                          matchedToken = jjFillToken();
                          if (specialToken == null)
                            specialToken = matchedToken;
                          else
                            {
                              matchedToken.specialToken = specialToken;
                              specialToken = (specialToken.next = matchedToken);
                            }
                          SkipLexicalActions(matchedToken);
                        }
                      else 
                        SkipLexicalActions(null);
                      if (jjnewLexState[jjmatchedKind] != -1)
                        curLexState = jjnewLexState[jjmatchedKind];
                      continue EOFLoop;
                    }
                  jjimageLen += jjmatchedPos + 1;
                  if (jjnewLexState[jjmatchedKind] != -1)
                    curLexState = jjnewLexState[jjmatchedKind];
                  curPos = 0;
                  jjmatchedKind = 0x7fffffff;
                  try {
                    curChar = input_stream.readChar();
                    continue;
                  }
                  catch (java.io.IOException e1) { }
                }
              int error_line = input_stream.getEndLine();
              int error_column = input_stream.getEndColumn();
              String error_after = null;
              boolean EOFSeen = false;
              try { input_stream.readChar(); input_stream.backup(1); }
              catch (java.io.IOException e1) {
                EOFSeen = true;
                error_after = curPos <= 1 ? "" : input_stream.GetImage();
                if (curChar == '\n' || curChar == '\r') {
                  error_line++;
                  error_column = 0;
                }
                else
                  error_column++;
              }
              if (!EOFSeen) {
                input_stream.backup(1);
                error_after = curPos <= 1 ? "" : input_stream.GetImage();
              }
              throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
            }
        }
  }

  void SkipLexicalActions(Token matchedToken)
  {
    switch(jjmatchedKind)
      {
      default :
        break;
      }
  }
}
