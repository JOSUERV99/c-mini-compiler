package validation;

public class TokenError {

	private int line, column;
	private String value;
	
	public TokenError(int line, int column, String value) {
		this.line = line;
		this.column = column;
		this.value = value;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "TokenError [\nline=" + line + ",\ncolumn=" + column + ",\nvalue=" + value + "]\n";
	}
}
