package model;

public class KeywordToken extends Token {

	public KeywordToken(int line, int column, String value) {
		super(line, column, value);
		this.setType("KEYWORD");
	}

	@Override
	public String toString() {
		return super.getValue();
	}

}
