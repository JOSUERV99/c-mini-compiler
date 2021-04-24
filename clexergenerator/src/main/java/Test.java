import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import handler.Tokenizer;
import validation.IllegalTokenException;

public class Test {

	private static String defaultTestFile = "src/main/java/test/operators.c";
	
	private static String welcomeMessage = "  ___                            ___  \r\n"
			+ " (o o)                          (o o) \r\n"
			+ "(  V  ) JFlex CLexer Generator (  V  )\r\n"
			+ "--m-m----------------------------m-m--\n"
			+ "Authors: Josue Rojas & Andrew Gutierrez";
	
	public static void main(String[] args) {
		
		Test.tokenizerTest(
			Test.requestFilename()
		);
	}
	
	public static void tokenizerTest(String filePath) {
		
		try 
		{	
			System.out.println("\n#Running lexical scanner with "+filePath+" ...");
			Tokenizer tokenizer = new Tokenizer(filePath);
			
			// tokenizer process
			tokenizer.generateTokens();
			System.out.println("\n########################################\n");
			System.out.println("[Tokens] => \n");
			System.out.println(tokenizer);
			
			// error logging
			System.out.println("\n########################################\n");
			System.out.println("[Errors] => \n");
			System.out.println(tokenizer.getErrorList());
			
			// stats
			System.out.println(tokenizer.log(filePath));
			System.exit(0);
		} 
		catch (IOException | IllegalTokenException ex) 
		{
			System.out.println(ex.getMessage());
		}
	}
	
	private static String requestFilename() {
		
		System.out.println(welcomeMessage);
		Scanner inputScanner = new Scanner(System.in);
		
	    System.out.print("\n=> Enter the input filename: ");
	    String filename = inputScanner.nextLine();
	    
	    inputScanner.close();	

	    // check if file exists
		try {
			File temp = new File(filename);
			if (!temp.exists())
			{
				filename = defaultTestFile;
			}
		} catch (Exception e) {
			filename = defaultTestFile;
		}
	    
	    return filename;
	}
}
