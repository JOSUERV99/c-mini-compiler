package handler;

import lexer.CLexer;
import parser.CParser;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import errors.Error;
import errors.LexicalError;
import errors.SyntaxError;
import java_cup.runtime.Symbol;
import utils.FileUtils;

public class Analyzer {

    private CParser parser;
    private CLexer lexer;
    private ArrayList<LexicalError> lexicalErrors = new ArrayList<>();
    private ArrayList<SyntaxError> syntaxErrors = new ArrayList<>();

    private String fileInputName;

    public Analyzer(String inputFilename) {
        this.fileInputName = inputFilename;
    }

    private void init() {

        if (!FileUtils.fileExists(fileInputName))
            return;

        try {

            this.lexer = new CLexer(new BufferedReader(new FileReader(fileInputName)));
            this.parser = new CParser();
            this.parser.bind(this.lexer, this);
            this.lexer.bind(this);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Symbol compile() throws Exception {

        this.init();
        Symbol tree = this.parser.parse();
        this.displaySummary();
        return tree;
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
        System.out.println(
                "Lexical => " + ((this.lexicalErrors.isEmpty()) ? "No one" : this.prettyPrintList(this.lexicalErrors)));
        System.out.println(
                "Syntax  => " + ((this.syntaxErrors.isEmpty()) ? "No one" : this.prettyPrintList(this.syntaxErrors)));

    }

    private String prettyPrintList(ArrayList<? extends Error> errorList) {
        String content = "[\n";
        for (Error o : errorList) {
            content += "\t" + o.toString() + "\n";
        }
        content += "]";
        return content;
    }

    public ArrayList<LexicalError> getLexicalErrors() {
        return lexicalErrors;
    }

    public void setLexicalErrors(ArrayList<LexicalError> lexicalErrors) {
        this.lexicalErrors = lexicalErrors;
    }

    public ArrayList<SyntaxError> getSyntaxErrors() {
        return syntaxErrors;
    }

    public void setSyntaxErrors(ArrayList<SyntaxError> syntaxErrors) {
        this.syntaxErrors = syntaxErrors;
    }

    public String getFileInputName() {
        return fileInputName;
    }

    public void setFileInputName(String fileInputName) {
        this.fileInputName = fileInputName;
    }
}
