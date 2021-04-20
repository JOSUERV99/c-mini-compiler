package types;

public class Token {

	public int line, column;
	public String value;
	
	 public Token(int line, int column, String value) {
	        this.line = line;
	        this.column = column;
	        this.value = value;
	 }
	
}
