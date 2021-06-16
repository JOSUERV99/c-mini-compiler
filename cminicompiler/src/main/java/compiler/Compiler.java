package compiler;

import handler.Analyzer;

public class Compiler {

    private SymbolTable symbolTable;
    private Analyzer analyzer;

    private boolean errorsFlag = false;

    public Compiler() {
        this.symbolTable = new SymbolTable();
        this.analyzer = new Analyzer();
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public Analyzer getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    public boolean isErrorsFlag() {
        return errorsFlag;
    }

    public void setErrorsFlag(boolean errorsFlag) {
        this.errorsFlag = errorsFlag;
    }
}