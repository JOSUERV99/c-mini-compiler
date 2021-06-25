package model;

public class IdentifierToken extends Token {

	public IdentifierToken(int line, int column, String value) {
		super(line, column, value);
		this.setType("IDENTIFIER");
	}

	@Override
	public String toString() {
		return super.getValue();
	}

}
