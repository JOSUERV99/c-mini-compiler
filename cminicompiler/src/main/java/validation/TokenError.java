package validation;

public class TokenError {

	private int line, column;
	private String value, message;
	
	public TokenError(int line, int column, String value, String message) {
		this.line = line;
		this.column = column;
		this.value = value;
		this.message = message;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TokenError [\n\tline=" + line + ",\n\tcolumn=" + column + ",\n\tvalue=" + value + ",\n\tmessage=" + message +"\n]\n";
	}
}
