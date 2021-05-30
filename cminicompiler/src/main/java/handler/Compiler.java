package handler;

import lexer.CLexer;
import parser.CParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java_cup.runtime.ScannerBuffer;
import java_cup.runtime.XMLElement;
import utils.FileUtils;

public class Compiler {

    private CParser parser;
    private CLexer lexer;

    public Compiler() {
    }

    private void init(String fileInputName) {

        if (!FileUtils.fileExists(fileInputName))
            return;

        try {

            this.lexer = new CLexer(new BufferedReader(new FileReader(fileInputName)));
            this.parser = new CParser();
            this.parser.setLexer(this.lexer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parse(String fileInputName, String fileOutputName) throws Exception {

        XMLElement e = (XMLElement) this.parser.parse().value;
        XMLOutputFactory outFactory = XMLOutputFactory.newInstance();
        ScannerBuffer lexer = new ScannerBuffer(new CLexer(new BufferedReader(new FileReader(fileInputName))));
        XMLStreamWriter sw = outFactory.createXMLStreamWriter(new FileOutputStream(fileOutputName));

        // dump XML output to the file
        XMLElement.dump(lexer, sw, e); // ,"expr","stmt");

        // transform the parse tree into an AST and a rendered HTML version
        Transformer transformer = TransformerFactory.newInstance()
                .newTransformer(new StreamSource(new File("tree.xsl")));
        Source text = new StreamSource(new File(fileOutputName));
        transformer.transform(text, new StreamResult(new File("output.html")));

        System.out.println("Parsing finished!");
    }

    public void compile(String fileInputName, String fileOutputName) throws Exception {

        this.init(fileInputName);
        this.parser.debug_parse();

        // this.parse(fileInputName, fileOutputName);
    }
}
