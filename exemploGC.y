	
%{
  import java.io.*;
  import java.util.ArrayList;
  import java.util.Stack;
%}
 

%token ID, INT, FLOAT, BOOL, NUM, LIT, VOID, MAIN, READ, WRITE, IF, ELSE, INC, BREAK, CONTINUE
%token WHILE,TRUE, FALSE, DO
%token EQ, LEQ, GEQ, NEQ, DEC 
%token AND, OR , PLUSE, FOR
%token RETURN

%right '=' PLUSE
%left '?' ':'
%left OR
%left AND
%left  '>' '<' EQ LEQ GEQ NEQ
%left '+' '-' 
%left '*' '/' '%'
%left '!' 

%type <sval> ID
%type <sval> LIT
%type <sval> NUM
%type <ival> type

%%

prog : { geraInicio(); } dList blocoFuncoes { geraAreaDados(); geraAreaLiterais(); } ;

blocoFuncoes : blocoFuncoes itemFuncao | itemFuncao ;

itemFuncao : funcDecl | mainF ;

mainF : VOID MAIN '(' ')'   { System.out.println("_start:"); }
        '{' lcmd  { geraFinal(); } '}'
         ; 

dList : decl dList | ;
fList : fList funcDecl | ;

decl : type ID ';' {  TS_entry nodo = ts.pesquisa($2);
    	                if (nodo != null) 
                            yyerror("(sem) variavel >" + $2 + "< jah declarada");
                        else ts.insert(new TS_entry($2, $1)); }
	| type ID '[' NUM ']' ';' {
          int tamanho = Integer.parseInt($4);
          TS_entry nodo = ts.pesquisa($2);
          if (nodo != null)
              yyerror("(sem) array >" + $2 + "< jah declarado");
          else {
              ts.insert(new TS_entry($2, $1, tamanho, $1));
              System.out.println("\t.comm _" + $2 + ", " + (tamanho * 4) + ", 4");
          }
     }
      ;

paramList 
    : paramList ',' type ID {
        TS_entry p = new TS_entry($4, $3);
        p.setParametro(true);
        p.setDeslocamento(proxDeslocamento++);
        ts.insert(p);
    }
    | type ID {
        TS_entry p = new TS_entry($2, $1);
        p.setParametro(true);
        p.setDeslocamento(proxDeslocamento++);
        ts.insert(p);
    }
    | /* vazio */ 

argList : argList ',' exp
        | exp
        | /* vazio */
        ;

funcDecl : type ID '(' paramList ')' '{'
              { 
				proxDeslocamento = 1;
				System.out.println("_" + $2 + ":");
				System.out.println("\tPUSH %EBP");
				System.out.println("\tMOVL %ESP, %EBP");
				ts.beginScope();
              }
              dList lcmd 
              {
				if (!retornou) {
				System.out.println("\tPOP %EBP");
				System.out.println("\tRET");
				}
				retornou = false; 
              }
           '}'
         ;

type : INT    { $$ = INT; }
     | FLOAT  { $$ = FLOAT; }
     | BOOL   { $$ = BOOL; }
     ;

lcmd : lcmd cmd
	   |
	   ;
	   
cmd :  '{' lcmd '}' { System.out.println("\t\t# terminou o bloco..."); }
			| exp ';'	{System.out.println("\tPOPL %EAX");}	     	       
      | WRITE '(' LIT ')' ';' { strTab.add($3);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				System.out.println("\tCALL _writeln"); 
                                strCount++;
				}
      
	  | WRITE '(' LIT 
                              { strTab.add($3);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				strCount++;
				}

                    ',' exp ')' ';' 
			{ 
			 System.out.println("\tPOPL %EAX"); 
			 System.out.println("\tCALL _write");	
			 System.out.println("\tCALL _writeln"); 
                        }
	 | ID '[' exp ']' '=' exp ';' {
			System.out.println("\t# Atribuição a array " + $1 + "[$3] = $6");
			System.out.println("\tPOPL %EAX"); // valor
			System.out.println("\tPOPL %EBX"); // índice
			System.out.println("\tMOVL %EAX, _" + $1 + "(,%EBX,4)");
		}
         
     | READ '(' ID ')' ';'								
								{
									System.out.println("\tPUSHL $_"+$3);
									System.out.println("\tCALL _read");
									System.out.println("\tPOPL %EDX");
									System.out.println("\tMOVL %EAX, (%EDX)");
									
								}
         
    | WHILE {
					pRotRep.push(proxRot);  proxRot += 2;
					System.out.printf("rot_%02d:\n",pRotRep.peek());
				  } 
			 '(' exp ')' {
			 							System.out.println("\tPOPL %EAX   # desvia se falso...");
											System.out.println("\tCMPL $0, %EAX");
											System.out.printf("\tJE rot_%02d\n", (int)pRotRep.peek()+1);
										} 
				cmd		{
				  		System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRotRep.peek());
							System.out.printf("rot_%02d:\n",(int)pRotRep.peek()+1);
							pRotRep.pop();
							}  
							
			| IF '(' exp {	
											pRot.push(proxRot);  proxRot += 2;
															
											System.out.println("\tPOPL %EAX");
											System.out.println("\tCMPL $0, %EAX");
											System.out.printf("\tJE rot_%02d\n", pRot.peek());
										}
								')' cmd 

             restoIf {
											System.out.printf("rot_%02d:\n",pRot.peek()+1);
											pRot.pop();
										}
		|
		DO {
			pRotRep.push(proxRot);  proxRot += 2;
			System.out.printf("rot_%02d:\n",pRotRep.peek());
		}
		cmd WHILE '(' exp ')' ';' 
					{
			 		System.out.println("\tPOPL %EAX   # desvia se falso...");
					System.out.println("\tCMPL $0, %EAX");
					System.out.printf("\tJE rot_%02d\n", (int)pRotRep.peek()+1);
					} 
					{
					System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRotRep.peek());
					System.out.printf("rot_%02d:\n",(int)pRotRep.peek()+1);
					pRotRep.pop();
					}

			| CONTINUE ';' {	System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRotRep.peek());}
			| BREAK ';' {System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRotRep.peek()+1);}
			| FOR '(' expOpc ';'
					{
			  		pRotRep.push(proxRot);  proxRot += 4;
						System.out.printf("rot_%02d:\n",pRotRep.peek()+3);
						}
					 expOpcL ';' {
						System.out.println("\tPOPL %EAX   # desvia se falso...");
					  System.out.println("\tCMPL $0, %EAX");
					  System.out.printf("\tJE rot_%02d\n", (int)pRotRep.peek()+1);
						System.out.printf("\tJMP rot_%02d \n", pRotRep.peek()+2);
					
						System.out.printf("rot_%02d:\n",pRotRep.peek());
					 }
	       expOpc ')'
				  {	
						System.out.printf("\tJMP rot_%02d \n", pRotRep.peek()+3);		
						System.out.printf("rot_%02d:\n",pRotRep.peek()+2);} 
				 cmd
				 {				
					System.out.printf("\tJMP rot_%02d \n", pRotRep.peek());		
					  System.out.printf("rot_%02d:\n",pRotRep.peek()+1);
				              pRotRep.pop();}
				| RETURN NUM ';' {
					retornou = true;
					System.out.println("\tMOVL $" + $2 + ", %EAX");
					System.out.println("\tPOP %EBP");
					System.out.println("\tRET");
				}
				| RETURN exp ';' {
					retornou = true;
					// se a expressão já está em %EAX, não precisa desempilhar
					System.out.println("\tPOP %EBP");
					System.out.println("\tRET");
				}
     ;
		
		expOpc : exp | ;
		expOpcL : exp 
					| { System.out.println("\tPUSHL $1"); } 
					;  
     
     
restoIf : ELSE  {
											System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
											System.out.printf("rot_%02d:\n",pRot.peek());
								
										} 							
							cmd  
							
							
		| {
		   System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
				System.out.printf("rot_%02d:\n",pRot.peek());
				} 
		;						

exp :  NUM  { System.out.println("\tPUSHL $"+$1); } 
    |  TRUE  { System.out.println("\tPUSHL $1"); } 
    |  FALSE  { System.out.println("\tPUSHL $0"); }      
 	| ID {
		TS_entry ent = ts.pesquisa($1);
		if (ent != null && ent.isParametro()) {
			int offset = 4 + ent.getDeslocamento() * 4;
			System.out.println("\tMOVL " + offset + "(%EBP), %EAX");
			System.out.println("\tPUSHL %EAX");
		} else {
			System.out.println("\tPUSHL _"+$1);
		}
	}
    | '(' exp	')' 
    | '!' exp       { gcExpNot(); }
     
		| exp '+' exp		{ gcExpArit('+'); }
		| exp '-' exp		{ gcExpArit('-'); }
		| exp '*' exp		{ gcExpArit('*'); }
		| exp '/' exp		{ gcExpArit('/'); }
		| exp '%' exp		{ gcExpArit('%'); }
																			
		| exp '>' exp		{ gcExpRel('>'); }
		| exp '<' exp		{ gcExpRel('<'); }											
		| exp EQ exp		{ gcExpRel(EQ); }											
		| exp LEQ exp		{ gcExpRel(LEQ); }											
		| exp GEQ exp		{ gcExpRel(GEQ); }											
		| exp NEQ exp		{ gcExpRel(NEQ); }											
												
		| exp OR exp		{ gcExpLog(OR); }											
		| exp AND exp		{ gcExpLog(AND); }											
		| ID '=' exp    {  System.out.println("\tPOPL %EDX");
											System.out.println("\tMOVL %EDX, _"+$1);
											System.out.println("\tPUSHL _"+$1);
					     }
		| ID INC { System.out.println("\tPUSHL _"+$1);
							 System.out.println("\tPUSHL $1");
							 System.out.println("\tPUSHL _"+$1);
							 gcExpArit('+');
							 System.out.println("\tPOPL %EDX");
  						 System.out.println("\tMOVL %EDX, _"+$1);							
								
							 }
							 
		| INC ID { System.out.println("\tPUSHL _"+$2);
							 System.out.println("\tPUSHL $1");
							 gcExpArit('+');
							 System.out.println("\tPOPL %EDX");
  						 System.out.println("\tMOVL %EDX, _"+$2);
							 System.out.println("\tPUSHL _"+$2);
							 }

		| ID DEC { System.out.println("\tPUSHL _"+$1);
				System.out.println("\tPUSHL $1");
				System.out.println("\tPUSHL _"+$1);
				gcExpArit('-');
				System.out.println("\tPOPL %EDX");
				System.out.println("\tMOVL %EDX, _"+$1);
			}

		| DEC ID {
				System.out.println("\tPUSHL _"+$2);
				System.out.println("\tPUSHL $1");
				gcExpArit('-');
				System.out.println("\tPOPL %EDX");
				System.out.println("\tMOVL %EDX, _"+$2);
				System.out.println("\tPUSHL _"+$2);
			}

		| ID PLUSE exp { System.out.println("\tPUSHL _"+$1);
								gcExpArit('+');
								System.out.println("\tPOPL %EDX");
								System.out.println("\tMOVL %EDX, _"+$1);
								}
		| exp '?' {	
											pRot.push(proxRot);  proxRot += 2;
											System.out.println("\tPOPL %EAX");
											System.out.println("\tCMPL $0, %EAX");
											System.out.printf("\tJE rot_%02d\n", pRot.peek());
										}
			 exp 
			 { System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);
											System.out.printf("rot_%02d:\n",pRot.peek());} 
			':'	exp {
											System.out.printf("rot_%02d:\n",pRot.peek()+1);
											pRot.pop();
											}
		| ID '[' exp ']' {
				System.out.println("\t# Acesso a array " + $1);
				System.out.println("\tPOPL %EBX"); // índice
				System.out.println("\tMOVL _" + $1 + "(,%EBX,4), %EAX");
				System.out.println("\tPUSHL %EAX");
				}
		| ID '(' argList ')' {
        		System.out.println("\tCALL _" + $1);
        		System.out.println("\tPUSHL %EAX");
    			}				
		;							

%%

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
   
