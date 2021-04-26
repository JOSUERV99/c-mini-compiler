import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import handler.Tokenizer;
import validation.IllegalTokenException;

public class Test {

	private static String defaultTestFile = "clexergenerator/src/main/java/test/errors.c";
	private static Scanner scanner = new Scanner(System.in);
	
	private static String welcomeMessage = "  ___                            ___  \r\n"
			+ " (o o)                          (o o) \r\n"
			+ "(  V  ) JFlex CLexer Generator (  V  )\r\n"
			+ "--m-m----------------------------m-m--\n"
			+ "Authors: Josue Rojas & Andrew Gutierrez";
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Test.tokenizerTest();
		
		System.out.println("Press Any Key To Exit...");
        scanner.nextLine();
	}
	
	public static void tokenizerTest() {
			
		String filePath = Test.requestFilename();
	
		while (true)
		{	
			if (filePath.equals("exit")) return;
		
			if (!new File(filePath).exists())
			{
				System.out.println("El archivo ["+filePath+"] no existe o su direccion no es valida");
				
			}
			else break;
			
			filePath = Test.requestFilename();
		}
		
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
	    System.out.print("\n=> Enter the input filename or 'exit': ");
	    return scanner.nextLine();
	}

}
