package clexergenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import generated.CLexer;
import types.IllegalTokenException;
import types.Token;

public class Tokenizer {

	private BufferedReader fileBuffer;
	private HashMap<String, Token> tokenMap;
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
				System.out.println(ex.getMessage()); 
			}	
		}
		
		this.lexer.yyclose();
	}

	public HashMap<String, Token> getTokenMap() {
		return tokenMap;
	}

	public void setTokenMap(HashMap<String, Token> tokenMap) {
		this.tokenMap = tokenMap;
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
