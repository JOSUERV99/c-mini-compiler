package model;

public class KeywordToken extends Token {
	
	public KeywordToken(int line, int column, String value) {
		super(line, column, value);
        this.setType("KEYWORD");
	}
	
}
