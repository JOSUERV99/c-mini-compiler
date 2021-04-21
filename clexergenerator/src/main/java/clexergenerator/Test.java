package clexergenerator;

import java.io.IOException;
import types.IllegalTokenException;

public class Test {

	public static void main(String[] args) {
		
		if (args.length > 0) 
		{
			Test.tokenizerTest(args[0]);
			System.exit(0);
		}
		
		System.out.println("Input file must be specified");
	}
	
	public static void tokenizerTest(String filePath) {
		
		try 
		{	
			Tokenizer tokenizer = new Tokenizer(filePath);
			tokenizer.generateTokens();
			System.out.println(tokenizer);	
		} 
		catch (IOException | IllegalTokenException ex) 
		{
			System.out.println(ex.getMessage());
		}
		
	}
	
}
