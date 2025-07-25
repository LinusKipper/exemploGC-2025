/* The following code was generated by JFlex 1.4.3 on 6/28/25, 2:30 AM */

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 6/28/25, 2:30 AM from the specification file
 * <tt>exemploGC.flex</tt>
 */
class Yylex {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\20\1\2\2\0\1\3\22\0\1\20\1\27\1\56\1\0"+
    "\1\4\1\23\1\30\1\0\1\23\1\23\1\23\1\21\1\23\1\22"+
    "\1\0\1\23\12\1\1\23\1\23\1\25\1\26\1\24\1\23\1\0"+
    "\1\7\1\55\1\10\1\55\1\11\1\15\6\55\1\16\1\14\1\13"+
    "\2\55\1\6\1\17\1\5\6\55\1\23\1\0\1\23\1\0\1\12"+
    "\1\0\1\40\1\41\1\53\1\43\1\45\1\35\1\55\1\51\1\32"+
    "\1\55\1\54\1\36\1\47\1\33\1\37\2\55\1\44\1\52\1\34"+
    "\1\46\1\42\1\50\3\55\1\23\1\31\1\23\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\2\3\1\1\1\4\1\5\7\6"+
    "\2\1\13\4\1\1\2\0\1\7\1\10\1\11\1\12"+
    "\1\13\1\14\1\15\1\16\1\17\1\4\1\20\7\4"+
    "\1\21\6\4\3\0\1\22\2\4\1\23\13\4\1\24"+
    "\2\0\1\25\2\4\1\26\1\4\1\27\1\4\1\30"+
    "\1\31\1\32\3\4\2\0\1\33\1\34\1\35\1\4"+
    "\1\36\1\37\1\4\2\0\1\40\1\4\2\0\1\4"+
    "\2\0\1\41\1\42\2\0\1\43\1\44";

  private static int [] zzUnpackAction() {
    int [] result = new int[114];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\57\0\136\0\57\0\215\0\274\0\353\0\u011a"+
    "\0\u0149\0\u0178\0\57\0\u01a7\0\u01d6\0\u0205\0\u0234\0\u0263"+
    "\0\u0292\0\u02c1\0\u02f0\0\u031f\0\u034e\0\u037d\0\u03ac\0\u03db"+
    "\0\u040a\0\u0439\0\u0468\0\u0497\0\u04c6\0\u04f5\0\u0524\0\57"+
    "\0\57\0\57\0\57\0\57\0\57\0\57\0\57\0\57"+
    "\0\u0553\0\353\0\u0582\0\u05b1\0\u05e0\0\u060f\0\u063e\0\u066d"+
    "\0\u069c\0\353\0\u06cb\0\u06fa\0\u0729\0\u0758\0\u0787\0\u07b6"+
    "\0\u07e5\0\u0814\0\u0843\0\353\0\u0872\0\u08a1\0\353\0\u08d0"+
    "\0\u08ff\0\u092e\0\u095d\0\u098c\0\u09bb\0\u09ea\0\u0a19\0\u0a48"+
    "\0\u0a77\0\u0aa6\0\u07e5\0\u0ad5\0\u0b04\0\353\0\u0b33\0\u0b62"+
    "\0\353\0\u0b91\0\353\0\u0bc0\0\353\0\353\0\353\0\u0bef"+
    "\0\u0c1e\0\u0c4d\0\u0c7c\0\u0cab\0\353\0\353\0\353\0\u0cda"+
    "\0\353\0\353\0\u0d09\0\u0d38\0\u0d67\0\353\0\u0d96\0\u0dc5"+
    "\0\u0df4\0\u0e23\0\u0e52\0\u0e81\0\353\0\57\0\u0eb0\0\u0edf"+
    "\0\57\0\57";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[114];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\5\7\1\2\5\7"+
    "\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17"+
    "\1\20\1\21\1\22\1\7\1\23\1\24\3\7\1\25"+
    "\1\26\1\27\1\30\1\31\1\7\1\32\1\33\2\7"+
    "\1\34\2\7\1\35\60\0\1\3\57\0\1\4\61\0"+
    "\1\36\10\0\1\37\41\0\1\7\3\0\5\7\1\0"+
    "\5\7\12\0\24\7\21\0\1\10\57\0\1\40\4\0"+
    "\1\41\52\0\1\42\62\0\1\43\56\0\1\44\56\0"+
    "\1\45\56\0\1\46\60\0\1\47\57\0\1\50\26\0"+
    "\1\7\3\0\5\7\1\0\5\7\12\0\1\7\1\51"+
    "\1\7\1\52\20\7\2\0\1\7\3\0\5\7\1\0"+
    "\5\7\12\0\12\7\1\53\11\7\2\0\1\7\3\0"+
    "\5\7\1\0\5\7\12\0\4\7\1\54\1\55\1\56"+
    "\15\7\2\0\1\7\3\0\5\7\1\0\5\7\12\0"+
    "\5\7\1\57\4\7\1\60\11\7\2\0\1\7\3\0"+
    "\5\7\1\0\5\7\12\0\5\7\1\61\16\7\2\0"+
    "\1\7\3\0\5\7\1\0\5\7\12\0\5\7\1\62"+
    "\16\7\2\0\1\7\3\0\5\7\1\0\5\7\12\0"+
    "\13\7\1\63\10\7\2\0\1\7\3\0\5\7\1\0"+
    "\5\7\12\0\4\7\1\64\17\7\2\0\1\7\3\0"+
    "\5\7\1\0\5\7\12\0\6\7\1\65\15\7\2\0"+
    "\1\7\3\0\5\7\1\0\5\7\12\0\12\7\1\66"+
    "\4\7\1\67\4\7\2\0\1\7\3\0\5\7\1\0"+
    "\5\7\12\0\5\7\1\70\16\7\1\0\2\71\1\0"+
    "\54\71\6\0\1\72\63\0\1\73\44\0\1\7\3\0"+
    "\5\7\1\0\5\7\12\0\2\7\1\74\21\7\2\0"+
    "\1\7\3\0\5\7\1\0\5\7\12\0\14\7\1\75"+
    "\7\7\2\0\1\7\3\0\5\7\1\0\5\7\12\0"+
    "\5\7\1\76\16\7\2\0\1\7\3\0\5\7\1\0"+
    "\5\7\12\0\12\7\1\77\11\7\2\0\1\7\3\0"+
    "\5\7\1\0\5\7\12\0\4\7\1\100\17\7\2\0"+
    "\1\7\3\0\5\7\1\0\5\7\12\0\5\7\1\101"+
    "\16\7\2\0\1\7\3\0\5\7\1\0\5\7\12\0"+
    "\13\7\1\102\10\7\2\0\1\7\3\0\5\7\1\0"+
    "\5\7\12\0\1\103\23\7\2\0\1\7\3\0\5\7"+
    "\1\0\5\7\12\0\2\7\1\104\3\7\1\105\15\7"+
    "\2\0\1\7\3\0\5\7\1\0\5\7\12\0\20\7"+
    "\1\106\3\7\2\0\1\7\3\0\5\7\1\0\5\7"+
    "\12\0\1\107\23\7\2\0\1\7\3\0\5\7\1\0"+
    "\5\7\12\0\1\110\23\7\2\0\1\7\3\0\5\7"+
    "\1\0\5\7\12\0\1\111\23\7\2\0\1\7\3\0"+
    "\5\7\1\0\5\7\12\0\1\7\1\112\22\7\1\0"+
    "\2\71\1\0\53\71\1\113\7\0\1\114\66\0\1\115"+
    "\40\0\1\7\3\0\5\7\1\0\5\7\12\0\13\7"+
    "\1\116\10\7\2\0\1\7\3\0\5\7\1\0\5\7"+
    "\12\0\6\7\1\117\15\7\2\0\1\7\3\0\5\7"+
    "\1\0\5\7\12\0\20\7\1\120\3\7\2\0\1\7"+
    "\3\0\5\7\1\0\5\7\12\0\4\7\1\121\17\7"+
    "\2\0\1\7\3\0\5\7\1\0\5\7\12\0\6\7"+
    "\1\122\15\7\2\0\1\7\3\0\5\7\1\0\5\7"+
    "\12\0\11\7\1\123\12\7\2\0\1\7\3\0\5\7"+
    "\1\0\5\7\12\0\14\7\1\124\7\7\2\0\1\7"+
    "\3\0\5\7\1\0\5\7\12\0\11\7\1\125\12\7"+
    "\2\0\1\7\3\0\5\7\1\0\5\7\12\0\13\7"+
    "\1\126\10\7\2\0\1\7\3\0\5\7\1\0\5\7"+
    "\12\0\1\7\1\127\22\7\2\0\1\7\3\0\5\7"+
    "\1\0\5\7\12\0\2\7\1\130\21\7\2\0\1\7"+
    "\3\0\5\7\1\0\5\7\12\0\4\7\1\131\17\7"+
    "\2\0\1\7\3\0\5\7\1\0\5\7\12\0\2\7"+
    "\1\132\21\7\11\0\1\133\53\0\1\134\52\0\1\7"+
    "\3\0\5\7\1\0\5\7\12\0\2\7\1\135\21\7"+
    "\2\0\1\7\3\0\5\7\1\0\5\7\12\0\13\7"+
    "\1\136\10\7\2\0\1\7\3\0\5\7\1\0\5\7"+
    "\12\0\22\7\1\137\1\7\2\0\1\7\3\0\5\7"+
    "\1\0\5\7\12\0\12\7\1\140\11\7\2\0\1\7"+
    "\3\0\5\7\1\0\5\7\12\0\13\7\1\141\10\7"+
    "\2\0\1\7\3\0\5\7\1\0\5\7\12\0\13\7"+
    "\1\142\10\7\2\0\1\7\3\0\5\7\1\0\5\7"+
    "\12\0\1\143\23\7\12\0\1\144\53\0\1\145\51\0"+
    "\1\7\3\0\5\7\1\0\5\7\12\0\1\7\1\146"+
    "\22\7\2\0\1\7\3\0\5\7\1\0\5\7\12\0"+
    "\1\7\1\147\22\7\13\0\1\150\53\0\1\151\50\0"+
    "\1\7\3\0\5\7\1\0\5\7\12\0\14\7\1\152"+
    "\7\7\14\0\1\153\55\0\1\154\45\0\1\7\3\0"+
    "\5\7\1\0\5\7\12\0\13\7\1\155\10\7\15\0"+
    "\1\156\1\157\46\0\1\160\66\0\1\161\60\0\1\162"+
    "\37\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3854];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\1\1\1\11\6\1\1\11\22\1\2\0"+
    "\11\11\20\1\3\0\20\1\2\0\15\1\2\0\7\1"+
    "\2\0\2\1\2\0\1\1\2\0\1\1\1\11\2\0"+
    "\2\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[114];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
    yyline = 1;
  }


  public int getLine() {
      return yyline;
  }



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Yylex(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Yylex(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 156) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 21: 
          { return Parser.TRUE;
          }
        case 37: break;
        case 4: 
          { yyparser.yylval = new ParserVal(yytext());
            return Parser.ID;
          }
        case 38: break;
        case 17: 
          { return Parser.DO;
          }
        case 39: break;
        case 27: 
          { return Parser.FLOAT;
          }
        case 40: break;
        case 14: 
          { return Parser.AND;
          }
        case 41: break;
        case 31: 
          { return Parser.WHILE;
          }
        case 42: break;
        case 32: 
          { return Parser.RETURN;
          }
        case 43: break;
        case 18: 
          { return Parser.INT;
          }
        case 44: break;
        case 22: 
          { return Parser.BOOL;
          }
        case 45: break;
        case 6: 
          { return (int) yycharat(0);
          }
        case 46: break;
        case 34: 
          { yyparser.setDebug(true);
          }
        case 47: break;
        case 29: 
          { return Parser.BREAK;
          }
        case 48: break;
        case 13: 
          { return Parser.NEQ;
          }
        case 49: break;
        case 3: 
          { yyline++;
          }
        case 50: break;
        case 12: 
          { return Parser.EQ;
          }
        case 51: break;
        case 10: 
          { return Parser.GEQ;
          }
        case 52: break;
        case 1: 
          { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1;
          }
        case 53: break;
        case 11: 
          { return Parser.LEQ;
          }
        case 54: break;
        case 16: 
          { return Parser.IF;
          }
        case 55: break;
        case 30: 
          { return Parser.WRITE;
          }
        case 56: break;
        case 26: 
          { return Parser.MAIN;
          }
        case 57: break;
        case 19: 
          { return Parser.FOR;
          }
        case 58: break;
        case 33: 
          { return Parser.CONTINUE;
          }
        case 59: break;
        case 8: 
          { return Parser.PLUSE;
          }
        case 60: break;
        case 35: 
          { yyparser.setDebug(false);
          }
        case 61: break;
        case 20: 
          { yyparser.yylval = new ParserVal(yytext().substring(1, yylength() -1));
	     return Parser.LIT;
          }
        case 62: break;
        case 28: 
          { return Parser.FALSE;
          }
        case 63: break;
        case 9: 
          { return Parser.DEC;
          }
        case 64: break;
        case 2: 
          { yyparser.yylval = new ParserVal(yytext()); 
         return Parser.NUM;
          }
        case 65: break;
        case 7: 
          { return Parser.INC;
          }
        case 66: break;
        case 36: 
          { yyparser.listarTS();
          }
        case 67: break;
        case 15: 
          { return Parser.OR;
          }
        case 68: break;
        case 24: 
          { return Parser.READ;
          }
        case 69: break;
        case 25: 
          { return Parser.ELSE;
          }
        case 70: break;
        case 23: 
          { return Parser.VOID;
          }
        case 71: break;
        case 5: 
          { 
          }
        case 72: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return 0; }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
