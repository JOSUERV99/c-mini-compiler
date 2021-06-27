package compiler;

import java.util.LinkedList;

import org.graalvm.compiler.graph.spi.Canonicalizable.Binary;

import errors.Error;
import errors.SemanticError;
import handler.Analyzer;
import iexpressions.BinaryExpression;
import iexpressions.FunctionCallExpression;
import iexpressions.IExpression;
import iexpressions.IdentifierExpression;
import interpreter.AssignDefinition;
import interpreter.DeclaredAssignDefinition;
import interpreter.FunctionDefinition;
import interpreter.GlobalVarDefinition;
import interpreter.ISemanticRegister;
import interpreter.ParamDefinition;
import interpreter.ProgramDefinition;
import java_cup.runtime.Symbol;
import semantic.SemanticStack;
import semantic.SymbolTable;
import utils.FileUtils;

public class Compiler {

    private SymbolTable symbolTable;
    private SemanticStack<ISemanticRegister> semanticStack;
    private LinkedList<SemanticError> semanticErrors;
    private ProgramDefinition programDefinition;
    private String inputFilename;

    private LinkedList<FunctionCallExpression> callsMemo;
    private LinkedList<IExpression> expMemo;

    private boolean errorFlag = false;

    public Compiler(String inputFilename) {
        this.symbolTable = new SymbolTable();
        this.semanticStack = new SemanticStack<>();
        this.semanticErrors = new LinkedList<>();
        this.callsMemo = new LinkedList<>();
        this.expMemo = new LinkedList<>();

        if (!FileUtils.fileExists(inputFilename))
            System.exit(1);

        this.inputFilename = inputFilename;
    }

    public boolean canGenerateCode() {
        return this.semanticErrors.isEmpty();
    }

    public void putVariable(AssignDefinition assign, boolean globalMode) {

        if (!globalMode) {

            if (this.symbolTable.isGlobalDefined(assign)) {

                System.out.println("aasd");
                System.out.println(assign);

                this.symbolTable.add(assign);
                return;
            } else {
                System.out.println("02020202020");
                System.out.println(assign);
                System.out.println(this.symbolTable.getGlobalVars());
            }

            if (this.symbolTable.isDefined(assign)) {
                this.semanticErrors.add(new SemanticError(assign.reportRepeated()));
            } else {
                this.symbolTable.add(assign);
            }

            return;
        }

        if (this.symbolTable.isGlobalDefined(assign)) {
            this.semanticErrors.add(new SemanticError(
                    "SemanticError: global variable " + assign.getSymbolIdentifier() + " is already declared"));
        } else {
            this.symbolTable.addGlobal(assign);
        }
    }

    public void putFunction(FunctionDefinition funct) {
        if (this.symbolTable.isDefined(funct)) {
            this.reportError(new SemanticError(funct.reportRepeated()));

        } else {
            this.symbolTable.add(funct);
        }
    }

    public void storeCall(FunctionCallExpression call) {
        this.callsMemo.add(call);
    }

    public void storeExpression(IExpression exp) {
        this.expMemo.add(exp);
    }

    public void checkSimpleAssignment(DeclaredAssignDefinition dad) {
        if (!this.symbolTable.isDefined(dad)) {

            this.reportError(new SemanticError(dad.reportRepeated()));
        } else {

            Object varObj = this.symbolTable.get(dad.getIdentifier().getValue());

            if (varObj instanceof AssignDefinition) {
                AssignDefinition ad = (AssignDefinition) varObj;
                dad.setType(ad.getType());
            } else {
                this.reportError(new SemanticError(
                        "SemanticError: is not possible to assign not variable, parameter o global type"));
            }
        }
    }

    public void checkBinaryExpression(BinaryExpression bexp) {

        IExpression exp1 = bexp.getExp1();
        IExpression exp2 = bexp.getExp2();
        System.out.println("Aaja aja Binary expresion opresora");
        System.out.println(exp1.getType());
        System.out.println(exp2.getType());
        System.out.println("Terminada Binary expresion opresora");

    }

    public void checkArg(ParamDefinition arg, LinkedList<ParamDefinition> definedArgs) {

        for (ParamDefinition pd : definedArgs) {
            if (arg.equals(pd)) {
                this.reportError(new SemanticError(
                        "SemanticError: param " + arg.getIdentifier().getValue() + " is already used"));
                return;
            }
        }
    }

    public void checkIdentificableExpression(IdentifierExpression iexp) {
        if (!this.symbolTable.isDefined(iexp)) {
            this.reportError(new SemanticError(iexp.reportNoDefinition()));
        }
    }

    public void checkCalls() {
        this.callsMemo.forEach(e -> this.checkFunctionCall(e));
    }

    public void checkFunctionCall(FunctionCallExpression fce) {

        if (!this.symbolTable.isDefined(fce)) {
            this.reportError(new SemanticError(fce.reportRepeated()));
            return;
        }

        FunctionDefinition fd = (FunctionDefinition) this.symbolTable.get(fce.getSymbolIdentifier());
        System.out.println(fd.getParams());
        System.out.println(fce.getParams());
        if (fd.getParams().size() != fce.getParams().size()) {
            this.reportError(new SemanticError("SemanticError: parameters amount are not equal"));
            return;
        }

        int typeIndex = 0;

        for (ParamDefinition pd : fd.getParams()) {

            IExpression exp = fce.getParams().get(typeIndex);

            if (!exp.getType().equals(pd.getType())) {
                this.reportError(new SemanticError("SemanticError: parameters types mismatch"));
            }

            typeIndex++;
        }

    }

    public void generateCode() {

        if (errorFlag) {
            System.out.println("The code cannot be generated");
            return;
        } else {
            System.out.println("PUSH AX");
        }

    }

    public void execProcess() {
        try {

            final Analyzer analyzer = new Analyzer(this.inputFilename);
            analyzer.bindCompiler(this);
            final Symbol tree = analyzer.compile();

            if (tree.value == null) {
                throw new NullPointerException("Program definition cannot be loaded");
            }

            this.programDefinition = (ProgramDefinition) tree.value;

            // TODO show lexical, syntactic, semantic errors report
            this.errorFlag = !this.semanticErrors.isEmpty();

            System.out.println("Exploto.");
            System.out.println(this.semanticErrors);

            this.generateCode();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void reportError(Error err) {
        if (err instanceof SemanticError)
            this.semanticErrors.add((SemanticError) err);

        // TODO add other errors
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

    public LinkedList<SemanticError> getSemanticErrors() {
        return semanticErrors;
    }

    public void setSemanticErrors(LinkedList<SemanticError> semanticErrors) {
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