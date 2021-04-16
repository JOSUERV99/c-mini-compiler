package types;

public class Token {

	public TokenType type;
	public int start, length, lineNumber;
	public String value;
	
	 public Token(TokenType type, int start, int lineNumber, String value) {
	        this.type = type;
	        this.start = start;
	        this.lineNumber = lineNumber;
	        this.value = value;
	 }
	
}
