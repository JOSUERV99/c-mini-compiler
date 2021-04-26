package model;

public class TokenPosition {

	private int line, column;
	
	public TokenPosition(int line, int column) 
	{
		this.line = line;
		this.column = column;
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

	@Override
	public String toString() {
		return "\n\t{line=" + line + ", column=" + column + "}";
	}
}
