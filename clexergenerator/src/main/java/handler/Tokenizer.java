package handler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import lexer.CLexer;
import validation.IllegalTokenException;
import model.Token;
import validation.TokenError;

public class Tokenizer {

	private BufferedReader fileBuffer;
	private HashMap<String, Token> tokenMap;
	private ArrayList<TokenError> errorList;
	private CLexer lexer;
	
	public Tokenizer(String filePath) throws FileNotFoundException {
		this.fileBuffer = new BufferedReader(new FileReader(filePath));
		this.tokenMap = new HashMap<>();
		this.lexer = new CLexer(this.fileBuffer);
	}

	public void generateTokens() throws IOException, IllegalTokenException {
		
		while(!this.lexer.yyatEOF()) 
		{
			try 
			{	
				Token token = this.lexer.yylex();
				
				if (token == null) continue;
				
				if (!this.tokenMap.containsKey(token.getValue())) 
				{
					this.tokenMap.put(token.getValue(), token);
				}
				else 
				{
					this.tokenMap.get(
						token.getValue()
					).setAppearances(
						this.tokenMap.get(token.getValue()).getAppearances() + 1
					);
				}
				
			}
			catch (IllegalTokenException ex) 
			{
				
			}	
		}
		
		this.lexer.yyclose();
		
		// get error list
		this.errorList = this.lexer.getErrorList();
	}
	
	public HashMap<String, Token> getTokenMap() {
		return tokenMap;
	}

	public void setTokenMap(HashMap<String, Token> tokenMap) {
		this.tokenMap = tokenMap;
	}
	
	public ArrayList<TokenError> getErrorList() {
		return errorList;
	}

	public void setErrorList(ArrayList<TokenError> errorList) {
		this.errorList = errorList;
	}
	
	public String log(String filePath) {
		return "\nGenerated tokens: "+this.getTokenMap().size() + 
				"\nGenerated errors: "+this.getErrorList().size() +
				"\nFrom ["+filePath+"]\n";
	}

	@Override
	public String toString() {
		
		String content = "";
		
		for ( String key : this.tokenMap.keySet() ) 
		{
			content += this.tokenMap.get(key).toString() + "\n";
		}
		
		return content;
	}
}
