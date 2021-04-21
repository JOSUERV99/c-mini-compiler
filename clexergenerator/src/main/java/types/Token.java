package types;

public class Token {

	private int line, column, appearances;
	private String value, type;
	
	 public Token(int line, int column, String value) {
        this.line = line;
        this.column = column;
        this.value = value;
        this.appearances = 1; 
	 }
	 
	public int getAppearances() {
		return appearances;
	}
	
	public void setAppearances(int appearances) {
		this.appearances = appearances;
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
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Token [type=" + type +"," + " line=" + line + ", column=" + column + ", appearances=" + appearances + ", value=" + value + "]";
	}

	
}
