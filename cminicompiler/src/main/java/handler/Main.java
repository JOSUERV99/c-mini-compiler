package handler;

public class Main {

    public static void main(String[] args) throws Exception {

        String fileInputName = "C:/Users/Josue/Documents/git/CLexerGenerator/cminicompiler/src/main/java/test/factorial.c";
        String fileOutputName = "C:/Users/Josue/Documents/git/CLexerGenerator/cminicompiler/src/main/java/out/Ofactorial.xml";

        Compiler compiler = new Compiler();
        compiler.compile(fileInputName, fileOutputName);
    }
}
