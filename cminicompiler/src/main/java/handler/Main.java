package handler;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {

        String fileInputName = "src/main/java/test/program2.c";
        String fileOutputName = "src/main/java/out/Ofactorial.xml";

        Analyzer analyzer = new Analyzer();
        analyzer.compile(fileInputName, fileOutputName);
    }
}
