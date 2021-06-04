package model;

import java.util.ArrayList;
import java_cup.runtime.Symbol;

public class Token {

	private String value, type;
	private Symbol symbol;
	private int line, column;
	private ArrayList<TokenPosition> positions = new ArrayList<>();

	public Token(int line, int column, String value) {

		this.line = line;
		this.column = column;
		this.value = value;

		this.addAppearance(line, column);
	}

	public Symbol createSymbol(int id) {
		this.symbol = new Symbol(id, line, column, this);
		return this.symbol;
	}

	public void addAppearance(Token token) {

		int line = token.getPositions().get(0).getLine();
		int column = token.getPositions().get(0).getColumn();

		this.positions.add(new TokenPosition(line, column));
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public void addAppearance(int line, int column) {
		this.positions.add(new TokenPosition(line, column));
	}

	public int getAppearances() {
		return this.positions.size();
	}

	public ArrayList<TokenPosition> getPositions() {
		return positions;
	}

	public void setPositions(ArrayList<TokenPosition> positions) {
		this.positions = positions;
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

	public String reportLocation(String expected) {
		return "at line: " + this.line + 1 + " & column" + this.column + 1 + "(" + expected + ")";
	}

	@Override
	public String toString() {
		return "Token [\nvalue=" + value + ",\ntype=" + type + ",\nappearances=" + this.getAppearances()
				+ ",\npositions=" + positions + "\nsymbol=" + symbol + "]\n";
	}
}
