package lexer;

import model.Token;
import model.OperatorToken;
import model.KeywordToken;
import model.LiteralToken;
import model.IdentifierToken;

import validation.TokenError;
import validation.IllegalTokenException;

import java.util.ArrayList;
import java_cup.runtime.*;
import handler.Analyzer;
import parser.sym;
import errors.LexicalError;

%%
%public
%class CLexer
%type Symbol
%implements sym
%scanerror IllegalTokenException
%unicode
%cup
%line
%column
%{
	// string generation with STRING state
	private StringBuffer stringBuilder = new StringBuffer();

	// analyzer ref
	private Analyzer analyzer;

	public void bind(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    public void generateLexicalError(String message) {
        this.analyzer.addLexicalError(
			new LexicalError(
				message, 
				new Token(yyline, yycolumn, yytext())
			)
		);
    }


	// error lists
	ArrayList<TokenError> errorList = new ArrayList<>();

	public ArrayList<TokenError> getErrorList() {
		ArrayList<TokenError> errorsList = (ArrayList<TokenError>) this.errorList.clone();
		this.errorList.clear();
		return errorsList;
	}

	public int getCurrentLine() {
		return yyline;
	}

%}



/* Regex patterns definition */
LineEnd    = \r|\n|\r\n
WhiteSpace = {LineEnd} | [ \t\f]
InputCharacter = [^\r\n]

/* helpers */
XIdentifierWithWrongStart = [0-9][a-zA-Z0-9_]*
XLiteralHexadecimalWithNotAllowedDigits = 0[x|X]([G-Z]+|[g-z]+)
XLiteralOctalWithNotAllowedDigits  = 0+[0-7]*[8-9]+[0-7]*
XLiteralIntegerWithNotAllowedDigits = [1-9]+[A-Za-z]+
XNotAllowedChars = [^a-zA-Z0-9!\"#%&\'\(\)\*\+,\-\./:;<=>\?\[\]\^_\{\|\}\~]

/* comments */
TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineEnd}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

/* literal values */
/* numbers */
NumberSign = [+\-]?
DecimalInteger = 0|([1-9][0-9]*) 
OctalValue = 0+[0-7]+
HexaValue = 0[x|X]([0-9a-f][0-9a-f]*|[0-9A-F][0-9A-F]*)
FloatValue = {NumberSign}([0-9]+\.|\.[0-9]+|{DecimalInteger}\.([0-9])*)
ExponentialNotation = {NumberSign}{DecimalInteger}.[0-9]+([eE][+\-]?[0-9]+)

/* characters */
CharScapeSequence = '\\[0|r|f|a|e|n|v|b|t|\\|']'
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


/* SYMBOLS def */

/*kw*/
KW_AUTO = "auto"
KW_BREAK = "break"
KW_CASE = "case"
KW_CHAR = "char"
KW_CONST = "const"
KW_CONTINUE = "continue"
KW_DEFAULT = "default"
KW_DO = "do"
KW_DOUBLE = "double"
KW_ELSE = "else"
KW_ENUM = "enum"
KW_EXTERN = "extern"
KW_FLOAT = "float"
KW_FOR = "for"
KW_GOTO = "goto"
KW_IF = "if"
KW_INT = "int"
KW_LONG = "long"
KW_REGISTER = "register"
KW_RETURN = "return"
KW_SHORT = "short"
KW_SIGNED = "signed"
KW_SIZEOF = "sizeof"
KW_STATIC = "static"
KW_STRUCT = "struct"
KW_SWITCH = "switch"
KW_TYPEDEF = "typedef"
KW_UNION = "union"
KW_UNSIGNED = "unsigned"
KW_VOID = "void"
KW_VOLATILE = "volatile"
KW_WHILE = "while"
KW_WRITE = "write"

/*op*/
OP_DIV = "/"
OP_PLUS = "+"
OP_MINUS = "-"
OP_MULT = "*"
OP_MOD = "%"
OP_COMMA = ","
OP_GTE = ">="
OP_LTE = "<="
OP_LT = "<"
OP_GT = ">"
OP_NOTEQUALCOMP = "!="
OP_ORCOMP = "||"
OP_ANDCOMP = "&&"
OP_EQUALCOMP = "=="
OP_AND = "&"
OP_XOR = "^"
OP_OR = "|"
OP_NOT = "~"
OP_NEGATION = "!"
OP_PLUSASSIGN = "+="
OP_MINUSASSIGN = "-="
OP_MULTASSIGN = "*="
OP_DIVASSIGN = "/="
OP_MINUSMINUSASSIGN = "--"
OP_PLUSPLUSASSIGN = "++"
OP_MODASSIGN = "%="
OP_ANDASSIGN = "&="
OP_NOTASSIGN = "^="
OP_ORASSIGN = "|="
OP_SHIFTLEFTASSIGN = "<<="
OP_SHIFTRIGHTASSIGN = ">>="
OP_TERNARYOPERATORQUESTIONMARK = "?"
OP_TERNARYOPERATIONDOUBLEDOT = ":"
OP_ASIG = "="
OP_SHIFTOPRIGHT = ">>"
OP_SHIFTOPLEFT = "<<"
OP_PROPOPERATOR = "."
OP_WRAPPERPARENTHESISLEFT = "("
OP_WRAPPERPARENTHESISRIGHT = ")"
OP_WRAPPERSQUAREPARENTHESISLEFT = "["
OP_WRAPPERSQUAREPARENTHESISRIGHT = "]"
OP_WRAPPERKEYPARENTHESISLEFT = "{"
OP_WRAPPERKEYPARENTHESISRIGHT = "}"
OP_INSTRUCTIONENDOPERATOR = ";"
OP_POINTEROPERATOR = "->"
OP_POINTEROPERATORASTERISC = "*"

/*literals*/
LIT_HEXVALUE = {HexaValue}
LIT_DECIMALVALUE = {DecimalInteger}
LIT_FLOATVALUE = {FloatValue}
LIT_CHARVALUE = {CharValue}
LIT_OCTALVALUE = {OctalValue}
LIT_STRINGDEF = {StringDefinition}
LIT_EXPONENTIALDEF = {ExponentialNotation}

/*identifier*/
IDENT = {Identifier}
//<<EOF>> { return token( sym.EOF ); }

%state STRING
%%
<YYINITIAL> {
	
  \"  { stringBuilder.setLength(0); yybegin(STRING); }  
 
	{KW_AUTO} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_AUTO);}
	{KW_BREAK} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_BREAK);}
	{KW_CASE} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_CASE);}
	{KW_CHAR} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_CHAR);}
	{KW_CONST} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_CONST);}
	{KW_CONTINUE} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_CONTINUE);}
	{KW_DEFAULT} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_DEFAULT);}
	{KW_DO} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_DO);}
	{KW_DOUBLE} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_DOUBLE);}
	{KW_ELSE} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_ELSE);}
	{KW_ENUM} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_ENUM);}
	{KW_EXTERN} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_EXTERN);}
	{KW_FLOAT} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_FLOAT);}
	{KW_FOR} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_FOR);}
	{KW_GOTO} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_GOTO);}
	{KW_IF} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_IF);}
	{KW_INT} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_INT);}
	{KW_LONG} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_LONG);}
	{KW_REGISTER} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_REGISTER);}
	{KW_RETURN} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_RETURN);}
	{KW_SHORT} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_SHORT);}
	{KW_SIGNED} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_SIGNED);}
	{KW_SIZEOF} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_SIZEOF);}
	{KW_STATIC} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_STATIC);}
	{KW_STRUCT} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_STRUCT);}
	{KW_SWITCH} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_SWITCH);}
	{KW_TYPEDEF} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_TYPEDEF);}
	{KW_UNION} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_UNION);}
	{KW_UNSIGNED} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_UNSIGNED);}
	{KW_VOID} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_VOID);}
	{KW_VOLATILE} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_VOLATILE);}
	{KW_WHILE} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_WHILE);}
	{KW_WRITE} { return new KeywordToken(yyline, yycolumn, yytext()).createSymbol(sym.KW_WRITE);}	

	{LIT_HEXVALUE} { return new LiteralToken(yyline, yycolumn, yytext()).createSymbol(sym.LIT_HEXVALUE);}
	{LIT_DECIMALVALUE} { return new LiteralToken(yyline, yycolumn, yytext()).createSymbol(sym.LIT_DECIMALVALUE);}
	{LIT_FLOATVALUE} { return new LiteralToken(yyline, yycolumn, yytext()).createSymbol(sym.LIT_FLOATVALUE);}
	{LIT_CHARVALUE} { return new LiteralToken(yyline, yycolumn, yytext()).createSymbol(sym.LIT_CHARVALUE);}
	{LIT_OCTALVALUE} { return new LiteralToken(yyline, yycolumn, yytext()).createSymbol(sym.LIT_OCTALVALUE);}
	{LIT_STRINGDEF} { return new LiteralToken(yyline, yycolumn, yytext()).createSymbol(sym.LIT_STRINGDEF);}
	{LIT_EXPONENTIALDEF} { return new LiteralToken(yyline, yycolumn, yytext()).createSymbol(sym.LIT_EXPONENTIALDEF);}

	{OP_DIV} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_DIV);}
	{OP_PLUS} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_PLUS);}
	{OP_MINUS} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_MINUS);}
	{OP_MULT} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_MULT);}
	{OP_MOD} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_MOD);}
	{OP_COMMA} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_COMMA);}
	{OP_GTE} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_GTE);}
	{OP_LTE} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_LTE);}
	{OP_LT} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_LT);}
	{OP_GT} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_GT);}
	{OP_NOTEQUALCOMP} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_NOTEQUALCOMP);}
	{OP_ORCOMP} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_ORCOMP);}
	{OP_ANDCOMP} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_ANDCOMP);}
	{OP_EQUALCOMP} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_EQUALCOMP);}      
	{OP_AND} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_AND);}
	{OP_XOR} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_XOR);}
	{OP_OR} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_OR);}
	{OP_NOT} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_NOT);}
	{OP_NEGATION} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_NEGATION);}
	{OP_PLUSASSIGN} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_PLUSASSIGN);}
	{OP_MINUSASSIGN} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_MINUSASSIGN);}
	{OP_MULTASSIGN} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_MULTASSIGN);}
	{OP_DIVASSIGN} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_DIVASSIGN);}
	{OP_MINUSMINUSASSIGN} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_MINUSMINUSASSIGN);}
	{OP_PLUSPLUSASSIGN} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_PLUSPLUSASSIGN);}
	{OP_MODASSIGN} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_MODASSIGN);}
	{OP_ANDASSIGN} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_ANDASSIGN);}
	{OP_NOTASSIGN} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_NOTASSIGN);}
	{OP_ORASSIGN} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_ORASSIGN);}
	{OP_SHIFTLEFTASSIGN} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_SHIFTLEFTASSIGN);}
	{OP_SHIFTRIGHTASSIGN} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_SHIFTRIGHTASSIGN);}
	{OP_TERNARYOPERATORQUESTIONMARK} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_TERNARYOPERATORQUESTIONMARK);}
	{OP_TERNARYOPERATIONDOUBLEDOT} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_TERNARYOPERATIONDOUBLEDOT);}
	{OP_ASIG} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_ASIG);}
	{OP_SHIFTOPRIGHT} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_SHIFTOPRIGHT);}
	{OP_SHIFTOPLEFT} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_SHIFTOPLEFT);}
	{OP_PROPOPERATOR} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_PROPOPERATOR);}
	{OP_WRAPPERPARENTHESISLEFT} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_WRAPPERPARENTHESISLEFT);}
	{OP_WRAPPERPARENTHESISRIGHT} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_WRAPPERPARENTHESISRIGHT);}
	{OP_WRAPPERSQUAREPARENTHESISLEFT} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_WRAPPERSQUAREPARENTHESISLEFT);}
	{OP_WRAPPERSQUAREPARENTHESISRIGHT} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_WRAPPERSQUAREPARENTHESISRIGHT);}
	{OP_WRAPPERKEYPARENTHESISLEFT} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_WRAPPERKEYPARENTHESISLEFT);}
	{OP_WRAPPERKEYPARENTHESISRIGHT} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_WRAPPERKEYPARENTHESISRIGHT);}
	{OP_INSTRUCTIONENDOPERATOR} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_INSTRUCTIONENDOPERATOR);}
	{OP_POINTEROPERATOR} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_POINTEROPERATOR);}
	{OP_POINTEROPERATORASTERISC} { return new OperatorToken(yyline, yycolumn, yytext()).createSymbol(sym.OP_POINTEROPERATORASTERISC);}

    {IDENT} { return new IdentifierToken(yyline, yycolumn, yytext()).createSymbol(sym.IDENT); }
	// \\n    { return new Symbol(sym.NEWLINE); }
	
  {XLiteralHexadecimalWithNotAllowedDigits} 
  	{ 
		this.errorList.add(new TokenError(yyline, yycolumn, yytext(), "Literal hexadecimal value must contain 0-9 or (a-f|A-F) "));  
		this.generateLexicalError("Literal hexadecimal value must contain 0-9 or (a-f|A-F) ");
	}
  
  {XLiteralOctalWithNotAllowedDigits} 
  	{ 
		this.errorList.add(new TokenError(yyline, yycolumn, yytext(), "Literal octal value must contain [0-7] digits"));  
		this.generateLexicalError("Literal octal value must contain [0-7] digits");
	}
  {XLiteralIntegerWithNotAllowedDigits} 
  	{ 
		this.errorList.add(new TokenError(yyline, yycolumn, yytext(), "Literal integer value must contain [0-9] digits"));
		this.generateLexicalError("Literal integer value must contain [0-9] digits");	
	}
  {XIdentifierWithWrongStart} 
  	{ 
		this.errorList.add(new TokenError(yyline, yycolumn, yytext(), "Identifier cannot start with number(s)"));  
		this.generateLexicalError("Identifier cannot start with number(s)");
	}

  {WhiteSpace} | {Comment}
  	{ /* do nothing */ }
}

<STRING> {
	\"                             { 
		yybegin(YYINITIAL);
		return new LiteralToken(yyline, yycolumn, stringBuilder.toString()).createSymbol(sym.LIT_STRINGDEF); 
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
	this.errorList.add(new TokenError(yyline, yycolumn, yytext(), "Illegal character"));
	throw new IllegalTokenException("Illegal character <" + yytext() + ">" + "[Line:" + yyline + ",Column:" + yycolumn + "]"); 
}