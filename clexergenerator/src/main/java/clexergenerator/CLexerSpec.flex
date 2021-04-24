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
	// string generation with STRING state
	StringBuffer stringBuilder = new StringBuffer();
%}

/* Regex patterns definition */
LineEnd    = \r|\n|\r\n
WhiteSpace = {LineEnd} | [ \t\f]
InputCharacter = [^\r\n]

/* comments */

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineEnd}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

/* literal values */
/* numbers */
DecimalInteger = 0 | [1-9][0-9]* /* include octal representation */
HexaValue = 0[x|X]([0-9a-f][0-9a-f]*|[0-9A-F][0-9A-F]*)
FloatValue = {DecimalInteger}.[0-9]*

/* characters */
CharSimpleValue = '[A-Za-z0-9!_$%#@&]'
CharScapeSequence = '\\[r|n|b|t]'
CharValue = {CharSimpleValue} | {CharScapeSequence}

/* string */
StringDefinition = \"[^*]\"

Literal =  
	{HexaValue} |
	{FloatValue} |
	{DecimalInteger} |
	{CharValue} |
	{StringDefinition}

/* operators */
AritmeticOperator 		= "/"  | "+"  | "-"  | "*"  | "%" 
SeparatorOperator 		= ","  
CompareOperator   		= ">=" | "<=" | "<"  | ">"  | "!=" | "||"  | "&&"  | "=="
BinaryOperator    		= "&"  | "^"  | "|"  | 	"!" | "~"  
CrossAsigAritOperator   = "+=" | "-=" | "*=" | "/=" | "--" | "++" | "%="
CrossAsigBinOperator    = "&=" | "^=" | "|=" | "<<="| ">>=" 
TernaryOperator 		= "?"  | ":"
AsigOperator			= "="  
ShiftOperator 			= ">>" | "<<" 
PropOperator			= "."
WrapperOperators        = "("  | ")"  | "["  | "]"  | "{"  | "}"
InstructionEndOperator  = ";"  
PointerOperator			= "->" | "*"

Operator = 
	{AritmeticOperator} | 
	{CompareOperator} | 
	{CompareOperator} | 
	{BinaryOperator} | 
	{CrossAsigAritOperator} | 
	{CrossAsigBinOperator} | 
	{TernaryOperator} | 
	{AsigOperator} | 
	{ShiftOperator} | 
	{PropOperator} | 
	{WrapperOperators} | 
	{SeparatorOperator} |
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

/* identifiers */
Identifier	   = [a-zA-Z_][a-zA-Z0-9_]*

%state STRING
%%
<YYINITIAL> {
  \"  { stringBuilder.setLength(0); yybegin(STRING); }  

  {KeyWord}	
  	{ return new KeywordToken(yyline, yycolumn, yytext());  }
  {Literal}
   	{ return new LiteralToken(yyline, yycolumn, yytext()); }
  {Operator} 
  	{ return new OperatorToken(yyline, yycolumn, yytext()); }
  {Identifier} 
  	{ return new IdentifierToken(yyline, yycolumn, yytext()); }

  {WhiteSpace}                   { /* do nothing */ }
  {Comment}                   { /* do nothing */ }
}

<STRING> {
	\"                             { 
		yybegin(YYINITIAL);
		return new LiteralToken(yyline, yycolumn, stringBuilder.toString()); 
	}
	[^\n\r\"\\]+                   { stringBuilder.append( yytext() ); }
	\\t                            { stringBuilder.append('\t'); }
	\\n                            { stringBuilder.append('\n'); }
	\\r                            { stringBuilder.append('\r'); }
	\\\"                           { stringBuilder.append('\"'); }
	\\                             { stringBuilder.append('\\'); }
}

/* error fallback */
[^]                              { throw new IllegalTokenException("Illegal character <" + yytext() + ">" + "[Line:" + yyline + ",Column:" + yycolumn + "]"); }