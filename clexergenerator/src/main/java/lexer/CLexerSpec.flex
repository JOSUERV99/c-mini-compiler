package lexer;

import model.Token;
import model.OperatorToken;
import model.KeywordToken;
import model.LiteralToken;
import model.IdentifierToken;

import validation.TokenError;
import validation.IllegalTokenException;

import java.util.ArrayList;

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
	private StringBuffer stringBuilder = new StringBuffer();

	// error lists
	ArrayList<TokenError> errorList = new ArrayList<>();

	public ArrayList<TokenError> getErrorList() {
		ArrayList<TokenError> errorsList = (ArrayList<TokenError>) this.errorList.clone();
		this.errorList.clear();
		return errorsList;
	}
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
DecimalInteger = 0|[1-9][0-9]* 
OctalValue = 0+[0-7]*
HexaValue = 0[x|X]([0-9a-f][0-9a-f]*|[0-9A-F][0-9A-F]*)
FloatValue = [0-9]*\.|\.[0-9]*|{DecimalInteger}.([0-9])*
ExponentialNotation = {DecimalInteger}.[0-9]+([eE][+\-]?[0-9]+)

/* characters */
CharScapeSequence = '\\[r|f|a|e|n|v|b|t|\\|']'
CharSingleValue = '([a-zA-Z0-9]|[!\"#%&\'\(\)\*\+,\-./:;<=>\?\[\]\^_\{\|\}~])'
CharValue = {CharScapeSequence} | {CharSingleValue}

/* string */
StringDefinition = \"[^*]\"

Literal =  
	{HexaValue} |
	{DecimalInteger} |
	{FloatValue} |
	{CharValue} |
	{OctalValue} |
	{StringDefinition} | 
	{ExponentialNotation}

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
[^]                              
{ 
	this.errorList.add(new TokenError(yyline, yycolumn, yytext()));
	throw new IllegalTokenException("Illegal character <" + yytext() + ">" + "[Line:" + yyline + ",Column:" + yycolumn + "]"); 
}