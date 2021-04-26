package model;

import java.util.ArrayList;

public class Token {

	private String value, type;
	private ArrayList<TokenPosition> positions;
	
	public Token(int line, int column, String value) {
        this.positions = new ArrayList<>();
        this.addAppearance(line, column);
        this.value = value;
	}
	
	public void addAppearance(Token token) {
		
		int line   = token.getPositions().get(0).getLine();
		int column = token.getPositions().get(0).getColumn();
		
		this.positions.add(new TokenPosition(line, column));
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

	@Override
	public String toString() {
		return "Token [\nvalue=" + value + ",\ntype=" + type + ",\nappearances=" + this.getAppearances() + ",\npositions=" + positions + "\n]\n";
	}
}
