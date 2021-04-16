import types.TokenType;
import types.Token;

%class GeneratedLexer
%type Token
%scanerror IllegalTokenException
%unicode

%{
	/*class content*/
%}

/* Regex patterns definition */

// basic rules
LineEnd    = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

// literal definition
LDecimalInteger = [0-9]
LHexaInteger = 0[x|X]{[0-9a-f]|[0-9A-F]}

// operators
AritmeticOperator 		= "/"  | "+"  | "-"  | "*"  | "%" 
SeparatorOperator 		= ","  
CompareOperator   		= ">=" | "<=" | "<"  | ">"  | "!=" | "||"  | "&&"  | "=="
BinaryOperator    		= "&"  | "^"  | "|"  | 	"!" | "~"  
CrossAsigAritOperator   = "+=" | "-=" | "*=" | "/=" | "--" | "++" | "%="
CrossAsiBinOperator     = "&=" | "^=" | "|=" | "<<="| ">>=" 
TernaryOperator 		= "?"  | ":"
AsigOperator			= "="  |
ShiftOperator 			= ">>" | "<<" 
PropOperator			= "."
WrapperOperators        = "("  | ")"  | "["  | "]"  | "{"  | "}"
InstructionEndOperator  = ";"  
PointerOperator			= "->" | "*"  /* WARNING */

Operator = 
	AritmeticOperator | SeparatorOperator | CompareOperator | BinaryOperator | CrossAsigAritOperator | CrossAsiBinOperator |
	TernaryOperator | AsigOperator | ShiftOperator | PropOperator | WrapperOperators | InstructionEndOperator | PointerOperator
	
// comments
OneLineComment = //[^*]{LineEnd}
BlockComment   = 

// token types
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

Identifier	   = [a-bA-B][a-zA-Z0-9_]*

%state STRING

%%
<YYINITIAL> {
	
  {KeyWord}						 { return new Token(TokenType.KEYWORD);  }
  {Operator}					 { return new Token(TokenType.OPERATOR); }
  
  {WhiteSpace}                   { /* do nothing */ }
  
  
}

/* error fallback */
[^]                              { throw new IllegalTokenException("Illegal character <" + yytext() + ">"); }