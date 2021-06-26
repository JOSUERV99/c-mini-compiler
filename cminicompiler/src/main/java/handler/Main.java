package handler;

import java_cup.runtime.Symbol;

public class Main {

    public static void main(final String[] args) throws Exception {

        final String fileInputName = "src/main/java/test/wkg.c";
        final String fileOutputName = "src/maisn/java/out/Ofactorial.xml";

        final Analyzer analyzer = new Analyzer();
        final Symbol tree = analyzer.compile(fileInputName, fileOutputName);

        System.out.println(tree.value);

    }

}
