package handler;

import java_cup.runtime.Symbol;

public class Main {

    public static void main(String[] args) throws Exception {

        String fileInputName = "src/main/java/test/wkg.c";
        String fileOutputName = "src/main/java/out/Ofactorial.xml";

        Analyzer analyzer = new Analyzer();
        Symbol tree = analyzer.compile(fileInputName, fileOutputName);

        System.out.println(tree.value);
    }
}
