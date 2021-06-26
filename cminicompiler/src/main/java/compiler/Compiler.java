package compiler;

import java.util.LinkedList;

import handler.Analyzer;
import iexpressions.IdentifierExpression;
import interpreter.AssignDefinition;
import interpreter.FunctionDefinition;
import interpreter.ISemanticRegister;
import interpreter.ProgramDefinition;
import java_cup.runtime.Symbol;
import semantic.SemanticStack;
import semantic.SymbolTable;
import utils.FileUtils;

public class Compiler {

    private SymbolTable symbolTable;
    private SemanticStack<ISemanticRegister> semanticStack;
    private LinkedList<String> semanticErrors;
    private ProgramDefinition programDefinition;
    private String inputFilename;

    public Compiler(String inputFilename) {
        this.symbolTable = new SymbolTable();
        this.semanticStack = new SemanticStack<>();
        this.semanticErrors = new LinkedList<>();

        if (!FileUtils.fileExists(inputFilename))
            System.exit(1);

        this.inputFilename = inputFilename;
    }

    public boolean canGenerateCode() {
        return this.semanticErrors.isEmpty();
    }

    public void putVariable(AssignDefinition assign) {
        if (this.symbolTable.isDefined(assign)) {
            this.semanticErrors.add(assign.reportRepeated());
        } else {
            this.symbolTable.add(assign);
        }
    }

    public void putFunction(FunctionDefinition funct) {
        if (this.symbolTable.isDefined(funct)) {
            this.semanticErrors.add(funct.reportRepeated());
        } else {
            this.symbolTable.add(funct);
        }
    }

    public void checkIdentificableExpression(IdentifierExpression iexp) {
        if (!this.symbolTable.isDefined(iexp)) {
            this.semanticErrors.add(iexp.reportNoDefinition());
        }
    }

    public void execProcess() {
        try {
            final Analyzer analyzer = new Analyzer(this.inputFilename);
            final Symbol tree = analyzer.compile();

            if (tree.value == null) {
                throw new NullPointerException("Program definition cannot be loaded");
            }

            this.programDefinition = (ProgramDefinition) tree.value;

            // TODO show lexical, syntactic, semantic errors report
            System.out.println("Exploto.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public SemanticStack<ISemanticRegister> getSemanticStack() {
        return semanticStack;
    }

    public void setSemanticStack(SemanticStack<ISemanticRegister> semanticStack) {
        this.semanticStack = semanticStack;
    }

    public LinkedList<String> getSemanticErrors() {
        return semanticErrors;
    }

    public void setSemanticErrors(LinkedList<String> semanticErrors) {
        this.semanticErrors = semanticErrors;
    }

    public ProgramDefinition getProgramDefinition() {
        return programDefinition;
    }

    public void setProgramDefinition(ProgramDefinition programDefinition) {
        this.programDefinition = programDefinition;
    }

    public String getInputFilename() {
        return inputFilename;
    }

    public void setInputFilename(String inputFilename) {
        this.inputFilename = inputFilename;
    }

}