package types;

public class LiteralToken extends Token {
	
	public LiteralToken(int line, int column, String value) {
		super(line, column, value);
        this.setType("LITERAL");
	}
	
}
