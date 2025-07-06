//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 3 "exemploGC.y"
  import java.io.*;
  import java.util.ArrayList;
  import java.util.Stack;
//#line 21 "Parser.java"




public class Parser
             implements ParserTokens
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    3,    0,    4,    4,    5,    5,    8,   10,    7,    2,
    2,   12,   12,   11,   11,   13,   13,   13,   14,   14,
   14,   16,   17,    6,    1,    1,    1,    9,    9,   18,
   18,   18,   19,   18,   18,   18,   20,   21,   18,   22,
   18,   24,   25,   18,   18,   18,   28,   29,   30,   18,
   18,   18,   26,   26,   27,   27,   31,   23,   23,   15,
   15,   15,   15,   15,   15,   15,   15,   15,   15,   15,
   15,   15,   15,   15,   15,   15,   15,   15,   15,   15,
   15,   15,   15,   15,   32,   33,   15,   15,   15,
};
final static short yylen[] = {                            2,
    0,    3,    2,    1,    1,    1,    0,    0,    9,    2,
    0,    2,    0,    3,    6,    4,    2,    0,    3,    1,
    0,    0,    0,   11,    1,    1,    1,    2,    0,    3,
    2,    5,    0,    8,    7,    5,    0,    0,    7,    0,
    7,    0,    0,    9,    2,    2,    0,    0,    0,   12,
    3,    3,    1,    0,    1,    0,    0,    3,    0,    1,
    1,    1,    1,    3,    2,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    2,
    2,    2,    2,    3,    0,    0,    7,    4,    4,
};
final static short yydefred[] = {                         1,
    0,    0,   25,   26,   27,    0,    0,    0,    0,    0,
    0,    0,    4,    5,    6,   10,   14,    0,    0,    0,
    3,    0,    0,    0,    0,    7,    0,    0,   15,    0,
   17,    0,    0,   29,   22,    0,    0,    0,   16,    0,
   60,    0,    0,    0,    0,    0,    0,   37,   61,   62,
   42,    0,    0,    0,    0,    0,   29,    0,    0,   28,
   29,   80,   82,    0,    0,    0,    0,    0,    0,    0,
   81,   46,   45,    0,    0,   83,    0,    0,    0,    0,
   65,    0,    0,    9,    0,    0,    0,    0,    0,    0,
   85,    0,    0,    0,    0,    0,    0,    0,   31,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   51,   52,   64,   30,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   68,   69,
   70,    0,   89,    0,    0,    0,    0,    0,    0,    0,
    0,   47,    0,    0,   24,    0,    0,   36,   32,    0,
    0,   38,    0,    0,   88,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   35,    0,   57,   41,   39,    0,
   48,    0,   34,    0,   43,    0,   58,   44,    0,   49,
    0,   50,
};
final static short yydgoto[] = {                          1,
    6,    7,    2,   12,   13,   14,   15,   30,   37,   58,
    8,    0,   28,  103,   59,   38,  132,   60,  138,   74,
  160,  139,  168,   75,  178,  112,  163,  154,  176,  181,
  174,  124,  156,
};
final static short yysindex[] = {                         0,
    0, -180,    0,    0,    0, -249, -194, -180,  -56, -241,
 -227, -194,    0,    0,    0,    0,    0, -224,    7,   11,
    0,  -41,   17, -180,   14,    0, -181,  -28,    0,  -42,
    0,  -40, -180,    0,    0, -175,   51, -180,    0,  297,
    0,   45,   47,   48, -168,   34,   35,    0,    0,    0,
    0, -162,   56,   72,   81,   81,    0,  -24,  121,    0,
    0,    0,    0,   81,   81,   81,   81, -160, -164,   81,
    0,    0,    0,   59,   51,    0,   81,  299,   43,  128,
    0,  152,  -12,    0,   81,   81,   81,   81,   81,   81,
    0,   81,   81,   81,   81,   81,   81,   81,    0,   51,
  479,  479,  -27,  479,  163,   62,   63,  479,   81, -166,
  479,   49,   81,    0,    0,    0,    0,    1,    1,    1,
    1,  528,  514,   81,    1,    1,    8,    8,    0,    0,
    0,  -16,    0,   81,   54,   57,   58,   66,   77,  329,
   79,    0,  375,  479,    0,  479,   81,    0,    0,   81,
   51,    0,   81,   81,    0,   64,  410,  417, -148,   51,
  441,  479,   65,   81,    0,   67,    0,    0,    0,   76,
    0,  493,    0,   51,    0,   81,    0,    0,   84,    0,
   51,    0,
};
final static short yyrindex[] = {                         0,
    0, -127,    0,    0,    0,    0,    0,  -33,    0,    0,
    0,  137,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   15,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   13,    9,    0,  448,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   16,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   80,   86,  486,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   18,
  -26,  -19,    0,   27,    0,    0,   96,  100,    0,    0,
  -30,    0,    0,    0,    0,    0,    0,  579,  604,  617,
  738,  829,  -39,    0,  770,  779,  540,  572,    0,    0,
    0,    0,    0,    0,  455,    0,    0,    0,    0,    0,
    0,    0,    0,   89,    0,   31,    0,    0,    0,    0,
    0,    0,    0,   83,    0,    0,    0,    0,   30,    0,
    0,   91,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -32,    0,    0,    0,  110,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
   29,   -4,    0,    0,  140,    0,    0,    0,  -51,    0,
    0,    0,    0,    0,  846,    0,    0,  -74,    0,    0,
    0,    0,    0,    0,    0,  -22,    0,    0,    0,    0,
    0,    0,    0,
};
final static int YYTABLESIZE=1111;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         11,
  110,   77,   17,   16,   77,   83,   11,    9,   87,  100,
   53,   87,   32,  133,   84,   33,  134,   84,   77,   77,
   55,   79,   19,   77,   79,   87,   87,   56,   53,   20,
   87,   84,   84,   61,   18,   11,   22,   98,   79,   79,
   11,   11,   96,   94,   98,   95,   23,   97,   11,   96,
   24,   25,   27,   77,   97,   18,   21,   26,   18,   21,
   87,   36,   59,    3,    4,    5,   84,   20,   10,   59,
   20,   19,   29,   79,   19,   31,  159,    3,    4,    5,
   34,   39,   35,   55,   68,  169,   69,   70,   71,   11,
   56,   11,   72,   73,   76,   77,  106,  107,  109,  177,
   84,  114,  136,  137,   55,  141,  182,  142,  145,  150,
   57,   56,  117,   55,  147,  148,  149,  151,  153,  167,
   56,  164,   63,  171,  180,  173,   63,   63,   63,   63,
   63,   11,   63,   11,  175,   11,    2,    8,   54,   33,
   40,   56,   23,   63,   63,   63,   86,   63,   63,   55,
   54,   21,   59,  179,   59,    0,    0,   98,    0,    0,
    0,    0,   96,   94,   98,   95,    0,   97,    0,   96,
   94,    0,   95,   57,   97,    0,    0,    0,   63,   99,
   93,    0,   92,   91,    0,    0,  115,   93,   98,   92,
   91,    0,  116,   96,   94,    0,   95,    0,   97,   98,
    0,    0,    0,    0,   96,   94,    0,   95,    0,   97,
    0,   93,    0,   92,   91,    0,    0,    0,    0,    0,
    0,    0,   93,   11,   92,   91,    0,   11,    0,   11,
    0,   11,   11,   11,    0,   11,   11,   11,   11,   11,
   11,   11,   77,    0,   40,    0,   11,    0,   41,    0,
   11,   11,   42,   43,   44,  135,   45,   46,   47,   48,
   49,   50,   51,    0,    0,   11,    0,   52,    0,   11,
    0,   53,   54,   11,   11,   11,    0,   11,   11,   11,
   11,   11,   11,   11,    0,    0,   59,    0,   11,    0,
   59,    0,   11,   11,   59,   59,   59,    0,   59,   59,
   59,   59,   59,   59,   59,    0,    0,   40,    0,   59,
    0,   41,    0,   59,   59,   42,   43,   44,    0,   45,
   46,   47,   48,   49,   50,   51,    0,    0,   78,    0,
   52,    0,   79,    0,   53,   54,   66,   78,   66,    0,
   45,   41,    0,    0,   49,   50,    0,    0,    0,   45,
    0,   52,    0,   49,   50,    0,    0,   65,    0,   65,
   52,   63,   63,   63,   63,   98,   63,   63,    0,  152,
   96,   94,    0,   95,    0,   97,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   67,   93,  113,
   92,   91,    0,    0,    0,    0,   85,   86,   87,   88,
    0,   89,   90,   85,   86,   87,   88,    0,   89,   90,
    0,   98,    0,    0,    0,    0,   96,   94,    0,   95,
    0,   97,    0,    0,    0,    0,    0,   85,   86,   87,
   88,    0,   89,   90,   93,    0,   92,   91,   85,   86,
   87,   88,    0,   89,   90,    0,   98,    0,    0,    0,
    0,   96,   94,   98,   95,    0,   97,  166,   96,   94,
    0,   95,    0,   97,    0,    0,    0,  155,  165,   93,
    0,   92,   91,    0,    0,    0,   93,   98,   92,   91,
    0,  170,   96,   94,   63,   95,    0,   97,    0,   63,
   63,   88,   63,    0,   63,    0,   88,   88,    0,   88,
   93,   88,   92,   91,    0,    0,   63,   63,    0,   63,
   63,    0,    0,   88,   88,   98,   88,   88,    0,    0,
   96,   94,   60,   95,    0,   97,    0,   60,   60,   98,
   60,    0,   60,    0,   96,   94,    0,   95,   93,   97,
   92,   91,    0,    0,    0,   60,    0,   60,   60,    0,
   98,    0,   93,    0,   92,   96,   94,    0,   95,    0,
   97,    0,    0,    0,   98,   62,    0,   62,    0,   96,
   94,    0,   95,   93,   97,   92,   63,    0,   63,   64,
   66,   64,   66,   66,   66,    0,    0,   93,    0,   92,
    0,    0,    0,    0,    0,    0,    0,   66,   66,   66,
    0,   66,   66,    0,   85,   86,   87,   88,    0,   89,
   90,    0,   67,    0,   67,   67,   67,    0,    0,   73,
    0,    0,   73,    0,    0,    0,    0,    0,    0,   67,
   67,   67,   66,   67,   67,    0,   73,   73,   73,    0,
   73,   73,    0,    0,   74,    0,    0,   74,    0,    0,
   85,   86,   87,   88,    0,   89,   90,   75,    0,    0,
   75,   74,   74,   74,   67,   74,   74,    0,    0,    0,
    0,   73,    0,    0,   75,   75,   75,    0,   75,   75,
    0,    0,    0,    0,    0,   85,   86,   87,   88,    0,
   89,   90,   85,   86,   87,   88,   74,   89,   90,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   75,
    0,    0,    0,    0,    0,    0,   85,   86,   87,   88,
    0,   89,   90,   63,   63,   63,   63,    0,   63,   63,
   88,   88,   88,   88,    0,   88,   88,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   85,   86,   87,   88,    0,   89,
   90,   60,   60,   60,   60,    0,   60,   60,   85,   86,
   87,   88,    0,   89,   90,    0,    0,    0,   76,    0,
    0,   76,    0,    0,    0,    0,    0,    0,    0,   85,
   86,   87,   88,    0,   89,   76,   76,   76,    0,   76,
   76,    0,    0,   85,   86,   87,   88,    0,    0,    0,
   71,    0,    0,   71,    0,   66,   66,   66,   66,   72,
   66,   66,   72,    0,    0,    0,    0,   71,   71,   71,
   76,   71,   71,    0,    0,    0,   72,   72,   72,    0,
   72,   72,    0,    0,    0,    0,    0,   67,   67,   67,
   67,    0,   67,   67,   73,   73,   73,   73,    0,   73,
   73,    0,   71,    0,    0,    0,    0,    0,    0,   78,
    0,   72,   78,    0,    0,    0,    0,    0,    0,   74,
   74,   74,   74,    0,   74,   74,   78,   78,    0,    0,
    0,   78,   75,   75,   75,   75,    0,   75,   75,   80,
   81,   82,    0,    0,    0,    0,    0,    0,    0,  101,
  102,  104,  105,    0,    0,  108,    0,    0,    0,    0,
    0,   78,  111,    0,    0,    0,    0,    0,    0,    0,
  118,  119,  120,  121,  122,  123,    0,  125,  126,  127,
  128,  129,  130,  131,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  140,    0,    0,    0,  143,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  144,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  146,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  157,    0,    0,  158,    0,    0,  161,  162,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  172,
    0,    0,    0,   76,   76,   76,   76,    0,   76,   76,
    0,  111,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   71,   71,   71,   71,    0,
   71,   71,    0,    0,   72,   72,   72,   72,    0,   72,
   72,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   78,
   78,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   75,   41,   59,    8,   44,   57,   40,  257,   41,   61,
   41,   44,   41,   41,   41,   44,   44,   44,   58,   59,
   33,   41,  264,   63,   44,   58,   59,   40,   59,  257,
   63,   58,   59,   38,   91,    7,  261,   37,   58,   59,
   12,   33,   42,   43,   37,   45,   40,   47,   40,   42,
   40,   93,   24,   93,   47,   41,   41,   41,   44,   44,
   93,   33,   33,  258,  259,  260,   93,   41,  263,   40,
   44,   41,   59,   93,   44,  257,  151,  258,  259,  260,
  123,  257,  123,   33,   40,  160,   40,   40,  257,  123,
   40,  125,   59,   59,  257,   40,  257,  262,   40,  174,
  125,   59,   41,   41,   33,  272,  181,   59,  125,   44,
  123,   40,  125,   33,   61,   59,   59,   41,   40,  268,
   40,   58,   37,   59,   41,   59,   41,   42,   43,   44,
   45,  123,   47,  125,   59,  263,    0,  125,   59,   44,
   41,   59,  125,   58,   59,   60,   58,   62,   63,   59,
   41,   12,  123,  176,  125,   -1,   -1,   37,   -1,   -1,
   -1,   -1,   42,   43,   37,   45,   -1,   47,   -1,   42,
   43,   -1,   45,  123,   47,   -1,   -1,   -1,   93,   59,
   60,   -1,   62,   63,   -1,   -1,   59,   60,   37,   62,
   63,   -1,   41,   42,   43,   -1,   45,   -1,   47,   37,
   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   -1,   47,
   -1,   60,   -1,   62,   63,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   60,  257,   62,   63,   -1,  261,   -1,  263,
   -1,  265,  266,  267,   -1,  269,  270,  271,  272,  273,
  274,  275,  282,   -1,  257,   -1,  280,   -1,  261,   -1,
  284,  285,  265,  266,  267,   93,  269,  270,  271,  272,
  273,  274,  275,   -1,   -1,  257,   -1,  280,   -1,  261,
   -1,  284,  285,  265,  266,  267,   -1,  269,  270,  271,
  272,  273,  274,  275,   -1,   -1,  257,   -1,  280,   -1,
  261,   -1,  284,  285,  265,  266,  267,   -1,  269,  270,
  271,  272,  273,  274,  275,   -1,   -1,  257,   -1,  280,
   -1,  261,   -1,  284,  285,  265,  266,  267,   -1,  269,
  270,  271,  272,  273,  274,  275,   -1,   -1,  257,   -1,
  280,   -1,  261,   -1,  284,  285,   40,  257,   40,   -1,
  269,  261,   -1,   -1,  273,  274,   -1,   -1,   -1,  269,
   -1,  280,   -1,  273,  274,   -1,   -1,   61,   -1,   61,
  280,  276,  277,  278,  279,   37,  281,  282,   -1,   41,
   42,   43,   -1,   45,   -1,   47,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   60,   91,
   62,   63,   -1,   -1,   -1,   -1,  276,  277,  278,  279,
   -1,  281,  282,  276,  277,  278,  279,   -1,  281,  282,
   -1,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,
   -1,   47,   -1,   -1,   -1,   -1,   -1,  276,  277,  278,
  279,   -1,  281,  282,   60,   -1,   62,   63,  276,  277,
  278,  279,   -1,  281,  282,   -1,   37,   -1,   -1,   -1,
   -1,   42,   43,   37,   45,   -1,   47,   41,   42,   43,
   -1,   45,   -1,   47,   -1,   -1,   -1,   93,   59,   60,
   -1,   62,   63,   -1,   -1,   -1,   60,   37,   62,   63,
   -1,   41,   42,   43,   37,   45,   -1,   47,   -1,   42,
   43,   37,   45,   -1,   47,   -1,   42,   43,   -1,   45,
   60,   47,   62,   63,   -1,   -1,   59,   60,   -1,   62,
   63,   -1,   -1,   59,   60,   37,   62,   63,   -1,   -1,
   42,   43,   37,   45,   -1,   47,   -1,   42,   43,   37,
   45,   -1,   47,   -1,   42,   43,   -1,   45,   60,   47,
   62,   63,   -1,   -1,   -1,   60,   -1,   62,   63,   -1,
   37,   -1,   60,   -1,   62,   42,   43,   -1,   45,   -1,
   47,   -1,   -1,   -1,   37,  269,   -1,  269,   -1,   42,
   43,   -1,   45,   60,   47,   62,  280,   -1,  280,  283,
   41,  283,   43,   44,   45,   -1,   -1,   60,   -1,   62,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,   59,   60,
   -1,   62,   63,   -1,  276,  277,  278,  279,   -1,  281,
  282,   -1,   41,   -1,   43,   44,   45,   -1,   -1,   41,
   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,   58,
   59,   60,   93,   62,   63,   -1,   58,   59,   60,   -1,
   62,   63,   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,
  276,  277,  278,  279,   -1,  281,  282,   41,   -1,   -1,
   44,   58,   59,   60,   93,   62,   63,   -1,   -1,   -1,
   -1,   93,   -1,   -1,   58,   59,   60,   -1,   62,   63,
   -1,   -1,   -1,   -1,   -1,  276,  277,  278,  279,   -1,
  281,  282,  276,  277,  278,  279,   93,  281,  282,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,
   -1,   -1,   -1,   -1,   -1,   -1,  276,  277,  278,  279,
   -1,  281,  282,  276,  277,  278,  279,   -1,  281,  282,
  276,  277,  278,  279,   -1,  281,  282,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  276,  277,  278,  279,   -1,  281,
  282,  276,  277,  278,  279,   -1,  281,  282,  276,  277,
  278,  279,   -1,  281,  282,   -1,   -1,   -1,   41,   -1,
   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,
  277,  278,  279,   -1,  281,   58,   59,   60,   -1,   62,
   63,   -1,   -1,  276,  277,  278,  279,   -1,   -1,   -1,
   41,   -1,   -1,   44,   -1,  276,  277,  278,  279,   41,
  281,  282,   44,   -1,   -1,   -1,   -1,   58,   59,   60,
   93,   62,   63,   -1,   -1,   -1,   58,   59,   60,   -1,
   62,   63,   -1,   -1,   -1,   -1,   -1,  276,  277,  278,
  279,   -1,  281,  282,  276,  277,  278,  279,   -1,  281,
  282,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   41,
   -1,   93,   44,   -1,   -1,   -1,   -1,   -1,   -1,  276,
  277,  278,  279,   -1,  281,  282,   58,   59,   -1,   -1,
   -1,   63,  276,  277,  278,  279,   -1,  281,  282,   54,
   55,   56,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   64,
   65,   66,   67,   -1,   -1,   70,   -1,   -1,   -1,   -1,
   -1,   93,   77,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   85,   86,   87,   88,   89,   90,   -1,   92,   93,   94,
   95,   96,   97,   98,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  109,   -1,   -1,   -1,  113,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  124,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  134,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  147,   -1,   -1,  150,   -1,   -1,  153,  154,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  164,
   -1,   -1,   -1,  276,  277,  278,  279,   -1,  281,  282,
   -1,  176,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  276,  277,  278,  279,   -1,
  281,  282,   -1,   -1,  276,  277,  278,  279,   -1,  281,
  282,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  281,
  282,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=285;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'","'?'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"ID","INT","FLOAT","BOOL","NUM",
"LIT","VOID","MAIN","READ","WRITE","IF","ELSE","INC","BREAK","CONTINUE","WHILE",
"TRUE","FALSE","DO","EQ","LEQ","GEQ","NEQ","DEC","AND","OR","PLUSE","FOR",
"RETURN",
};
final static String yyrule[] = {
"$accept : prog",
"$$1 :",
"prog : $$1 dList blocoFuncoes",
"blocoFuncoes : blocoFuncoes itemFuncao",
"blocoFuncoes : itemFuncao",
"itemFuncao : funcDecl",
"itemFuncao : mainF",
"$$2 :",
"$$3 :",
"mainF : VOID MAIN '(' ')' $$2 '{' lcmd $$3 '}'",
"dList : decl dList",
"dList :",
"fList : fList funcDecl",
"fList :",
"decl : type ID ';'",
"decl : type ID '[' NUM ']' ';'",
"paramList : paramList ',' type ID",
"paramList : type ID",
"paramList :",
"argList : argList ',' exp",
"argList : exp",
"argList :",
"$$4 :",
"$$5 :",
"funcDecl : type ID '(' paramList ')' '{' $$4 dList lcmd $$5 '}'",
"type : INT",
"type : FLOAT",
"type : BOOL",
"lcmd : lcmd cmd",
"lcmd :",
"cmd : '{' lcmd '}'",
"cmd : exp ';'",
"cmd : WRITE '(' LIT ')' ';'",
"$$6 :",
"cmd : WRITE '(' LIT $$6 ',' exp ')' ';'",
"cmd : ID '[' exp ']' '=' exp ';'",
"cmd : READ '(' ID ')' ';'",
"$$7 :",
"$$8 :",
"cmd : WHILE $$7 '(' exp ')' $$8 cmd",
"$$9 :",
"cmd : IF '(' exp $$9 ')' cmd restoIf",
"$$10 :",
"$$11 :",
"cmd : DO $$10 cmd WHILE '(' exp ')' ';' $$11",
"cmd : CONTINUE ';'",
"cmd : BREAK ';'",
"$$12 :",
"$$13 :",
"$$14 :",
"cmd : FOR '(' expOpc ';' $$12 expOpcL ';' $$13 expOpc ')' $$14 cmd",
"cmd : RETURN NUM ';'",
"cmd : RETURN exp ';'",
"expOpc : exp",
"expOpc :",
"expOpcL : exp",
"expOpcL :",
"$$15 :",
"restoIf : ELSE $$15 cmd",
"restoIf :",
"exp : NUM",
"exp : TRUE",
"exp : FALSE",
"exp : ID",
"exp : '(' exp ')'",
"exp : '!' exp",
"exp : exp '+' exp",
"exp : exp '-' exp",
"exp : exp '*' exp",
"exp : exp '/' exp",
"exp : exp '%' exp",
"exp : exp '>' exp",
"exp : exp '<' exp",
"exp : exp EQ exp",
"exp : exp LEQ exp",
"exp : exp GEQ exp",
"exp : exp NEQ exp",
"exp : exp OR exp",
"exp : exp AND exp",
"exp : ID '=' exp",
"exp : ID INC",
"exp : INC ID",
"exp : ID DEC",
"exp : DEC ID",
"exp : ID PLUSE exp",
"$$16 :",
"$$17 :",
"exp : exp '?' $$16 exp $$17 ':' exp",
"exp : ID '[' exp ']'",
"exp : ID '(' argList ')'",
};

//#line 351 "exemploGC.y"

  private Yylex lexer;

  private TabSimb ts = new TabSimb();

  private int strCount = 0;
  private ArrayList<String> strTab = new ArrayList<String>();

  private Stack<Integer> pRot = new Stack<Integer>();

  private Stack<Integer> pRotRep = new Stack<Integer>();
  private int proxRot = 1;

  private int proxDeslocamento = 1;
  private boolean retornou = false;

  public static int ARRAY = 100;

  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error + "  linha: " + lexer.getLine());
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }  

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void listarTS() { ts.listar();}

  public static void main(String args[]) throws IOException {

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
      yyparser.yyparse();
      // yyparser.listarTS();

    }
    else {
      // interactive mode
      System.out.println("\n\tFormato: java Parser entrada.cmm >entrada.s\n");
    }

  }

							
		void gcExpArit(int oparit) {
 				System.out.println("\tPOPL %EBX");
   			System.out.println("\tPOPL %EAX");

   		switch (oparit) {
     		case '+' : System.out.println("\tADDL %EBX, %EAX" ); break;
     		case '-' : System.out.println("\tSUBL %EBX, %EAX" ); break;
     		case '*' : System.out.println("\tIMULL %EBX, %EAX" ); break;

    		case '/': 
           		     System.out.println("\tMOVL $0, %EDX");
           		     System.out.println("\tIDIVL %EBX");
           		     break;
     		case '%': 
           		     System.out.println("\tMOVL $0, %EDX");
           		     System.out.println("\tIDIVL %EBX");
           		     System.out.println("\tMOVL %EDX, %EAX");
           		     break;
    		}
		}

	public void gcExpRel(int oprel) {

    System.out.println("\tPOPL %EAX");
    System.out.println("\tPOPL %EDX");
    System.out.println("\tCMPL %EAX, %EDX");
    System.out.println("\tMOVL $0, %EAX");
    
    switch (oprel) {
       case '<':  			System.out.println("\tSETL  %AL"); break;
       case '>':  			System.out.println("\tSETG  %AL"); break;
       case Parser.EQ:  System.out.println("\tSETE  %AL"); break;
       case Parser.GEQ: System.out.println("\tSETGE %AL"); break;
       case Parser.LEQ: System.out.println("\tSETLE %AL"); break;
       case Parser.NEQ: System.out.println("\tSETNE %AL"); break;
       }
	}


	public void gcExpLog(int oplog) {

	   	System.out.println("\tPOPL %EDX");
 		 	System.out.println("\tPOPL %EAX");

  	 	System.out.println("\tCMPL $0, %EAX");
 		  System.out.println("\tMOVL $0, %EAX");
   		System.out.println("\tSETNE %AL");
   		System.out.println("\tCMPL $0, %EDX");
   		System.out.println("\tMOVL $0, %EDX");
   		System.out.println("\tSETNE %DL");

   		switch (oplog) {
    			case Parser.OR:  System.out.println("\tORL  %EDX, %EAX");  break;
    			case Parser.AND: System.out.println("\tANDL  %EDX, %EAX"); break;
       }
	}

	public void gcExpNot(){

  	 System.out.println("\tPOPL %EAX" );
 	   System.out.println("	\tNEGL %EAX" );
	}

   private void geraInicio() {
			System.out.println(".text\n\n#\t nome COMPLETO e matricula dos componentes do grupo...\n#\n"); 
			System.out.println(".GLOBL _start\n\n");  
   }

   private void geraFinal(){
	
			System.out.println("\n\n");
			System.out.println("#");
			System.out.println("# devolve o controle para o SO (final da main)");
			System.out.println("#");
			System.out.println("\tmov $0, %ebx");
			System.out.println("\tmov $1, %eax");
			System.out.println("\tint $0x80");
	
			System.out.println("\n");
			System.out.println("#");
			System.out.println("# Funcoes da biblioteca (IO)");
			System.out.println("#");
			System.out.println("\n");
			System.out.println("_writeln:");
			System.out.println("\tMOVL $__fim_msg, %ECX");
			System.out.println("\tDECL %ECX");
			System.out.println("\tMOVB $10, (%ECX)");
			System.out.println("\tMOVL $1, %EDX");
			System.out.println("\tJMP _writeLit");
			System.out.println("_write:");
			System.out.println("\tMOVL $__fim_msg, %ECX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tCMPL $0, %EAX");
			System.out.println("\tJGE _write3");
			System.out.println("\tNEGL %EAX");
			System.out.println("\tMOVL $1, %EBX");
			System.out.println("_write3:");
			System.out.println("\tPUSHL %EBX");
			System.out.println("\tMOVL $10, %EBX");
			System.out.println("_divide:");
			System.out.println("\tMOVL $0, %EDX");
			System.out.println("\tIDIVL %EBX");
			System.out.println("\tDECL %ECX");
			System.out.println("\tADD $48, %DL");
			System.out.println("\tMOVB %DL, (%ECX)");
			System.out.println("\tCMPL $0, %EAX");
			System.out.println("\tJNE _divide");
			System.out.println("\tPOPL %EBX");
			System.out.println("\tCMPL $0, %EBX");
			System.out.println("\tJE _print");
			System.out.println("\tDECL %ECX");
			System.out.println("\tMOVB $'-', (%ECX)");
			System.out.println("_print:");
			System.out.println("\tMOVL $__fim_msg, %EDX");
			System.out.println("\tSUBL %ECX, %EDX");
			System.out.println("_writeLit:");
			System.out.println("\tMOVL $1, %EBX");
			System.out.println("\tMOVL $4, %EAX");
			System.out.println("\tint $0x80");
			System.out.println("\tRET");
			System.out.println("_read:");
			System.out.println("\tMOVL $15, %EDX");
			System.out.println("\tMOVL $__msg, %ECX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tMOVL $3, %EAX");
			System.out.println("\tint $0x80");
			System.out.println("\tMOVL $0, %EAX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tMOVL $0, %EDX");
			System.out.println("\tMOVL $__msg, %ECX");
			System.out.println("\tCMPB $'-', (%ECX)");
			System.out.println("\tJNE _reading");
			System.out.println("\tINCL %ECX");
			System.out.println("\tINC %BL");
			System.out.println("_reading:");
			System.out.println("\tMOVB (%ECX), %DL");
			System.out.println("\tCMP $10, %DL");
			System.out.println("\tJE _fimread");
			System.out.println("\tSUB $48, %DL");
			System.out.println("\tIMULL $10, %EAX");
			System.out.println("\tADDL %EDX, %EAX");
			System.out.println("\tINCL %ECX");
			System.out.println("\tJMP _reading");
			System.out.println("_fimread:");
			System.out.println("\tCMPB $1, %BL");
			System.out.println("\tJNE _fimread2");
			System.out.println("\tNEGL %EAX");
			System.out.println("_fimread2:");
			System.out.println("\tRET");
			System.out.println("\n");
     }

     private void geraAreaDados(){
			System.out.println("");		
			System.out.println("#");
			System.out.println("# area de dados");
			System.out.println("#");
			System.out.println(".data");
			System.out.println("#");
			System.out.println("# variaveis globais");
			System.out.println("#");
			ts.geraGlobais();	
			System.out.println("");
	
    }

     private void geraAreaLiterais() { 

         System.out.println("#\n# area de literais\n#");
         System.out.println("__msg:");
	       System.out.println("\t.zero 30");
	       System.out.println("__fim_msg:");
	       System.out.println("\t.byte 0");
	       System.out.println("\n");

         for (int i = 0; i<strTab.size(); i++ ) {
             System.out.println("_str_"+i+":");
             System.out.println("\t .ascii \""+strTab.get(i)+"\""); 
	           System.out.println("_str_"+i+"Len = . - _str_"+i);  
	      }		
   }
   
//#line 818 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 31 "exemploGC.y"
{ geraInicio(); }
break;
case 2:
//#line 31 "exemploGC.y"
{ geraAreaDados(); geraAreaLiterais(); }
break;
case 7:
//#line 37 "exemploGC.y"
{ System.out.println("_start:"); }
break;
case 8:
//#line 38 "exemploGC.y"
{ geraFinal(); }
break;
case 14:
//#line 44 "exemploGC.y"
{  TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    	                if (nodo != null) 
                            yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");
                        else ts.insert(new TS_entry(val_peek(1).sval, val_peek(2).ival)); }
break;
case 15:
//#line 48 "exemploGC.y"
{
          int tamanho = Integer.parseInt(val_peek(2).sval);
          TS_entry nodo = ts.pesquisa(val_peek(4).sval);
          if (nodo != null)
              yyerror("(sem) array >" + val_peek(4).sval + "< jah declarado");
          else {
              ts.insert(new TS_entry(val_peek(4).sval, val_peek(5).ival, tamanho, val_peek(5).ival));
              System.out.println("\t.comm _" + val_peek(4).sval + ", " + (tamanho * 4) + ", 4");
          }
     }
break;
case 16:
//#line 61 "exemploGC.y"
{
        TS_entry p = new TS_entry(val_peek(0).sval, val_peek(1).ival);
        p.setParametro(true);
        p.setDeslocamento(proxDeslocamento++);
        ts.insert(p);
    }
break;
case 17:
//#line 67 "exemploGC.y"
{
        TS_entry p = new TS_entry(val_peek(0).sval, val_peek(1).ival);
        p.setParametro(true);
        p.setDeslocamento(proxDeslocamento++);
        ts.insert(p);
    }
break;
case 22:
//#line 81 "exemploGC.y"
{ 
				proxDeslocamento = 1;
				System.out.println("_" + val_peek(4).sval + ":");
				System.out.println("\tPUSH %EBP");
				System.out.println("\tMOVL %ESP, %EBP");
				ts.beginScope();
              }
break;
case 23:
//#line 89 "exemploGC.y"
{
				if (!retornou) {
				System.out.println("\tPOP %EBP");
				System.out.println("\tRET");
				}
				retornou = false; 
              }
break;
case 25:
//#line 99 "exemploGC.y"
{ yyval.ival = INT; }
break;
case 26:
//#line 100 "exemploGC.y"
{ yyval.ival = FLOAT; }
break;
case 27:
//#line 101 "exemploGC.y"
{ yyval.ival = BOOL; }
break;
case 30:
//#line 108 "exemploGC.y"
{ System.out.println("\t\t# terminou o bloco..."); }
break;
case 31:
//#line 109 "exemploGC.y"
{System.out.println("\tPOPL %EAX");}
break;
case 32:
//#line 110 "exemploGC.y"
{ strTab.add(val_peek(2).sval);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				System.out.println("\tCALL _writeln"); 
                                strCount++;
				}
break;
case 33:
//#line 119 "exemploGC.y"
{ strTab.add(val_peek(0).sval);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				strCount++;
				}
break;
case 34:
//#line 127 "exemploGC.y"
{ 
			 System.out.println("\tPOPL %EAX"); 
			 System.out.println("\tCALL _write");	
			 System.out.println("\tCALL _writeln"); 
                        }
break;
case 35:
//#line 132 "exemploGC.y"
{
			System.out.println("\t# Atribuição a array " + val_peek(6).sval + "[$3] = $6");
			System.out.println("\tPOPL %EAX"); /* valor*/
			System.out.println("\tPOPL %EBX"); /* índice*/
			System.out.println("\tMOVL %EAX, _" + val_peek(6).sval + "(,%EBX,4)");
		}
break;
case 36:
//#line 140 "exemploGC.y"
{
									System.out.println("\tPUSHL $_"+val_peek(2).sval);
									System.out.println("\tCALL _read");
									System.out.println("\tPOPL %EDX");
									System.out.println("\tMOVL %EAX, (%EDX)");
									
								}
break;
case 37:
//#line 148 "exemploGC.y"
{
					pRotRep.push(proxRot);  proxRot += 2;
					System.out.printf("rot_%02d:\n",pRotRep.peek());
				  }
break;
case 38:
//#line 152 "exemploGC.y"
{
			 							System.out.println("\tPOPL %EAX   # desvia se falso...");
											System.out.println("\tCMPL $0, %EAX");
											System.out.printf("\tJE rot_%02d\n", (int)pRotRep.peek()+1);
										}
break;
case 39:
//#line 157 "exemploGC.y"
{
				  		System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRotRep.peek());
							System.out.printf("rot_%02d:\n",(int)pRotRep.peek()+1);
							pRotRep.pop();
							}
break;
case 40:
//#line 163 "exemploGC.y"
{	
											pRot.push(proxRot);  proxRot += 2;
															
											System.out.println("\tPOPL %EAX");
											System.out.println("\tCMPL $0, %EAX");
											System.out.printf("\tJE rot_%02d\n", pRot.peek());
										}
break;
case 41:
//#line 172 "exemploGC.y"
{
											System.out.printf("rot_%02d:\n",pRot.peek()+1);
											pRot.pop();
										}
break;
case 42:
//#line 177 "exemploGC.y"
{
			pRotRep.push(proxRot);  proxRot += 2;
			System.out.printf("rot_%02d:\n",pRotRep.peek());
		}
break;
case 43:
//#line 182 "exemploGC.y"
{
			 		System.out.println("\tPOPL %EAX   # desvia se falso...");
					System.out.println("\tCMPL $0, %EAX");
					System.out.printf("\tJE rot_%02d\n", (int)pRotRep.peek()+1);
					}
break;
case 44:
//#line 187 "exemploGC.y"
{
					System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRotRep.peek());
					System.out.printf("rot_%02d:\n",(int)pRotRep.peek()+1);
					pRotRep.pop();
					}
break;
case 45:
//#line 193 "exemploGC.y"
{	System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRotRep.peek());}
break;
case 46:
//#line 194 "exemploGC.y"
{System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRotRep.peek()+1);}
break;
case 47:
//#line 196 "exemploGC.y"
{
			  		pRotRep.push(proxRot);  proxRot += 4;
						System.out.printf("rot_%02d:\n",pRotRep.peek()+3);
						}
break;
case 48:
//#line 200 "exemploGC.y"
{
						System.out.println("\tPOPL %EAX   # desvia se falso...");
					  System.out.println("\tCMPL $0, %EAX");
					  System.out.printf("\tJE rot_%02d\n", (int)pRotRep.peek()+1);
						System.out.printf("\tJMP rot_%02d \n", pRotRep.peek()+2);
					
						System.out.printf("rot_%02d:\n",pRotRep.peek());
					 }
break;
case 49:
//#line 209 "exemploGC.y"
{	
						System.out.printf("\tJMP rot_%02d \n", pRotRep.peek()+3);		
						System.out.printf("rot_%02d:\n",pRotRep.peek()+2);}
break;
case 50:
//#line 213 "exemploGC.y"
{				
					System.out.printf("\tJMP rot_%02d \n", pRotRep.peek());		
					  System.out.printf("rot_%02d:\n",pRotRep.peek()+1);
				              pRotRep.pop();}
break;
case 51:
//#line 217 "exemploGC.y"
{
					retornou = true;
					System.out.println("\tMOVL $" + val_peek(1).sval + ", %EAX");
					System.out.println("\tPOP %EBP");
					System.out.println("\tRET");
				}
break;
case 52:
//#line 223 "exemploGC.y"
{
					retornou = true;
					/* se a expressão já está em %EAX, não precisa desempilhar*/
					System.out.println("\tPOP %EBP");
					System.out.println("\tRET");
				}
break;
case 56:
//#line 233 "exemploGC.y"
{ System.out.println("\tPUSHL $1"); }
break;
case 57:
//#line 237 "exemploGC.y"
{
											System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
											System.out.printf("rot_%02d:\n",pRot.peek());
								
										}
break;
case 59:
//#line 245 "exemploGC.y"
{
		   System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
				System.out.printf("rot_%02d:\n",pRot.peek());
				}
break;
case 60:
//#line 251 "exemploGC.y"
{ System.out.println("\tPUSHL $"+val_peek(0).sval); }
break;
case 61:
//#line 252 "exemploGC.y"
{ System.out.println("\tPUSHL $1"); }
break;
case 62:
//#line 253 "exemploGC.y"
{ System.out.println("\tPUSHL $0"); }
break;
case 63:
//#line 254 "exemploGC.y"
{
		TS_entry ent = ts.pesquisa(val_peek(0).sval);
		if (ent != null && ent.isParametro()) {
			int offset = 4 + ent.getDeslocamento() * 4;
			System.out.println("\tMOVL " + offset + "(%EBP), %EAX");
			System.out.println("\tPUSHL %EAX");
		} else {
			System.out.println("\tPUSHL _"+val_peek(0).sval);
		}
	}
break;
case 65:
//#line 265 "exemploGC.y"
{ gcExpNot(); }
break;
case 66:
//#line 267 "exemploGC.y"
{ gcExpArit('+'); }
break;
case 67:
//#line 268 "exemploGC.y"
{ gcExpArit('-'); }
break;
case 68:
//#line 269 "exemploGC.y"
{ gcExpArit('*'); }
break;
case 69:
//#line 270 "exemploGC.y"
{ gcExpArit('/'); }
break;
case 70:
//#line 271 "exemploGC.y"
{ gcExpArit('%'); }
break;
case 71:
//#line 273 "exemploGC.y"
{ gcExpRel('>'); }
break;
case 72:
//#line 274 "exemploGC.y"
{ gcExpRel('<'); }
break;
case 73:
//#line 275 "exemploGC.y"
{ gcExpRel(EQ); }
break;
case 74:
//#line 276 "exemploGC.y"
{ gcExpRel(LEQ); }
break;
case 75:
//#line 277 "exemploGC.y"
{ gcExpRel(GEQ); }
break;
case 76:
//#line 278 "exemploGC.y"
{ gcExpRel(NEQ); }
break;
case 77:
//#line 280 "exemploGC.y"
{ gcExpLog(OR); }
break;
case 78:
//#line 281 "exemploGC.y"
{ gcExpLog(AND); }
break;
case 79:
//#line 282 "exemploGC.y"
{  System.out.println("\tPOPL %EDX");
											System.out.println("\tMOVL %EDX, _"+val_peek(2).sval);
											System.out.println("\tPUSHL _"+val_peek(2).sval);
					     }
break;
case 80:
//#line 286 "exemploGC.y"
{ System.out.println("\tPUSHL _"+val_peek(1).sval);
							 System.out.println("\tPUSHL $1");
							 System.out.println("\tPUSHL _"+val_peek(1).sval);
							 gcExpArit('+');
							 System.out.println("\tPOPL %EDX");
  						 System.out.println("\tMOVL %EDX, _"+val_peek(1).sval);							
								
							 }
break;
case 81:
//#line 295 "exemploGC.y"
{ System.out.println("\tPUSHL _"+val_peek(0).sval);
							 System.out.println("\tPUSHL $1");
							 gcExpArit('+');
							 System.out.println("\tPOPL %EDX");
  						 System.out.println("\tMOVL %EDX, _"+val_peek(0).sval);
							 System.out.println("\tPUSHL _"+val_peek(0).sval);
							 }
break;
case 82:
//#line 303 "exemploGC.y"
{ System.out.println("\tPUSHL _"+val_peek(1).sval);
				System.out.println("\tPUSHL $1");
				System.out.println("\tPUSHL _"+val_peek(1).sval);
				gcExpArit('-');
				System.out.println("\tPOPL %EDX");
				System.out.println("\tMOVL %EDX, _"+val_peek(1).sval);
			}
break;
case 83:
//#line 311 "exemploGC.y"
{
				System.out.println("\tPUSHL _"+val_peek(0).sval);
				System.out.println("\tPUSHL $1");
				gcExpArit('-');
				System.out.println("\tPOPL %EDX");
				System.out.println("\tMOVL %EDX, _"+val_peek(0).sval);
				System.out.println("\tPUSHL _"+val_peek(0).sval);
			}
break;
case 84:
//#line 320 "exemploGC.y"
{ System.out.println("\tPUSHL _"+val_peek(2).sval);
								gcExpArit('+');
								System.out.println("\tPOPL %EDX");
								System.out.println("\tMOVL %EDX, _"+val_peek(2).sval);
								}
break;
case 85:
//#line 325 "exemploGC.y"
{	
											pRot.push(proxRot);  proxRot += 2;
											System.out.println("\tPOPL %EAX");
											System.out.println("\tCMPL $0, %EAX");
											System.out.printf("\tJE rot_%02d\n", pRot.peek());
										}
break;
case 86:
//#line 332 "exemploGC.y"
{ System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
											System.out.printf("rot_%02d:\n",pRot.peek());}
break;
case 87:
//#line 334 "exemploGC.y"
{
											System.out.printf("rot_%02d:\n",pRot.peek()+1);
											pRot.pop();
											}
break;
case 88:
//#line 338 "exemploGC.y"
{
				System.out.println("\t# Acesso a array " + val_peek(3).sval);
				System.out.println("\tPOPL %EBX"); /* índice*/
				System.out.println("\tMOVL _" + val_peek(3).sval + "(,%EBX,4), %EAX");
				System.out.println("\tPUSHL %EAX");
				}
break;
case 89:
//#line 344 "exemploGC.y"
{
        		System.out.println("\tCALL _" + val_peek(3).sval);
        		System.out.println("\tPUSHL %EAX");
    			}
break;
//#line 1421 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
