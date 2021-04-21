package generated;

import types.Token;
import types.OperatorToken;
import types.KeywordToken;
import types.LiteralToken;
import types.IdentifierToken;
import types.IllegalTokenException;

%%
%public
%class CLexer
%type Token
%scanerror IllegalTokenException
%unicode
%line
%column
%{
	/*class content*/
%}

/* Regex patterns definition */
LineEnd    = \r|\n|\r\n
WhiteSpace = {LineEnd} | [ \t\f]

/* numbers */
LDecimalInteger = [0-9]
LHexaInteger = 0[x|X]([0-9a-f][0-9a-f]*|[0-9A-F][0-9A-F]*)

/* operators */
AritmeticOperator 		= "/"  | "+"  | "-"  | "*"  | "%" 
SeparatorOperator 		= ","  
CompareOperator   		= ">=" | "<=" | "<"  | ">"  | "!=" | "||"  | "&&"  | "=="
BinaryOperator    		= "&"  | "^"  | "|"  | 	"!" | "~"  
CrossAsigAritOperator   = "+=" | "-=" | "*=" | "/=" | "--" | "++" | "%="
CrossAsiBinOperator     = "&=" | "^=" | "|=" | "<<="| ">>=" 
TernaryOperator 		= "?"  | ":"
AsigOperator			= "="  
ShiftOperator 			= ">>" | "<<" 
PropOperator			= "."
WrapperOperators        = "("  | ")"  | "["  | "]"  | "{"  | "}"
InstructionEndOperator  = ";"  
PointerOperator			= "->" | "*"  /* WARNING */

Test = "+" | "-" | ">="	

Operator = 
	{AritmeticOperator} | 
	{CompareOperator} | 
	{CompareOperator} | 
	{BinaryOperator} | 
	{CrossAsigAritOperator} | 
	{CrossAsiBinOperator} | 
	{TernaryOperator} | 
	{AsigOperator} | 
	{ShiftOperator} | 
	{PropOperator} | 
	{WrapperOperators} | 
	{InstructionEndOperator} | 
	{PointerOperator}

/* token types */
KeyWord = 
	  "auto"
	| "break"
	| "case"
	| "char"
	| "const"
	| "continue"
	| "default"
	| "do"
	| "double"
	| "else"
	| "enum"
	| "extern"
	| "float"
	| "for"
	| "goto"
	| "if"
	| "int"
	| "long"
	| "register"
	| "return"
	| "short"
	| "signed"
	| "sizeof"
	| "static"
	| "struct"
	| "switch"
	| "typedef"
	| "union"
	| "unsigned"
	| "void"
	| "volatile"
	| "while"

Identifier	   = [a-zA-Z_][a-zA-Z0-9_]*

%%
<YYINITIAL> {
	
  {KeyWord}	{ return new KeywordToken(yyline, yycolumn, yytext());  }
  {Operator} { return new OperatorToken(yyline, yycolumn, yytext()); }  
  {Identifier} { return new IdentifierToken(yyline, yycolumn, yytext()); }

  {WhiteSpace}                   { /* do nothing */ }
}



/* error fallback */
[^]                              { throw new IllegalTokenException("Illegal character <" + yytext() + ">" + "[" + yyline + "," + yycolumn + "]"); }