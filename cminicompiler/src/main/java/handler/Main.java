package handler;

public class Main {

    public static void main(String[] args) throws Exception {

        String fileInputName = "0Pruebas/PruebaEstructura.c";
        String fileOutputName = "src/main/java/out/Ofactorial.xml";

        Analyzer analyzer = new Analyzer();
        analyzer.compile(fileInputName, fileOutputName);
    }
}
