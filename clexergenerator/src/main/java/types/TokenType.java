package types;

public enum TokenType {

	OPERATOR("operator"),
	KEYWORD("keyword"),
	IDENTIFIER("identifier"),
	LITERAL("literal");
	
	public String value;
	
	TokenType(String value) { 
		this.value = value; 
	}
	
	public String getValue() { 
		return value; 
	}	
	
	@Override public String toString() {
		return this.value;
	}
}
