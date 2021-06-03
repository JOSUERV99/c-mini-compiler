package handler;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {

        String fileInputName = "src/main/java/test/program2.c";
        String fileOutputName = "src/main/java/out/Ofactorial.xml";

        File c = new File(".");
        System.out.println(c.getAbsolutePath());

        Analyzer analyzer = new Analyzer();
        analyzer.compile(fileInputName, fileOutputName);
    }
}
