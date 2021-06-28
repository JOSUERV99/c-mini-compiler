package handler;

import compiler.Compiler;

public class Main {

    public static void main(final String[] args) throws Exception {
        String inputFilename = "src/main/java/test/TablaSimbolos.c";
        Compiler compiler = new Compiler(inputFilename);
        compiler.execProcess();
    }

}
