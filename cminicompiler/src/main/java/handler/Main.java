package handler;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {

        String fileInputName = "cminicompiler/src/main/java/test/program2.c";
        String fileOutputName = "cminicompiler/src/main/java/out/Ofactorial.xml";

        File c = new File(".");
        System.out.println(c.getAbsolutePath());

        Compiler compiler = new Compiler();
        compiler.compile(fileInputName, fileOutputName);
    }
}
