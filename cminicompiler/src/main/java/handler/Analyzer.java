package handler;

import lexer.CLexer;
import parser.CParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import errors.LexicalError;
import errors.SyntaxError;
import java_cup.runtime.ScannerBuffer;
import java_cup.runtime.XMLElement;
import utils.FileUtils;

public class Analyzer {

    private CParser parser;
    private CLexer lexer;
    private ArrayList<LexicalError> lexicalErrors = new ArrayList<>();
    private ArrayList<SyntaxError> syntaxErrors = new ArrayList<>();

    public Analyzer() {
    }

    private void init(String fileInputName) {

        if (!FileUtils.fileExists(fileInputName))
            return;

        try {

            this.lexer = new CLexer(new BufferedReader(new FileReader(fileInputName)));
            this.parser = new CParser();
            this.parser.bind(this.lexer, this);

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
        this.displaySummary();
        // this.parse(fileInputName, fileOutputName);
    }

    public void addSyntaxError(SyntaxError syntaxError) {
        this.syntaxErrors.add(syntaxError);
    }

    public void addLexicalError(LexicalError lexicalError) {
        this.lexicalErrors.add(lexicalError);
    }

    public void displaySummary() {

        int foundedErrors = this.lexicalErrors.size() + this.syntaxErrors.size();
        System.out.println("Errors: " + foundedErrors + "");
        System.out.println("Lexical => " + ((this.lexicalErrors.isEmpty()) ? "No one" : this.lexicalErrors.toString()));
        System.out.println("Syntax  => " + ((this.syntaxErrors.isEmpty()) ? "No one" : this.syntaxErrors.toString()));

    }
}
