package compiler;

import java.util.LinkedList;

import code.CodeGenerator;
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
    private CodeGenerator codeGenerator;

    private final String outDir = "src/main/java/out/";
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

                this.symbolTable.add(assign);
                return;
            }

            if (this.symbolTable.isDefined(assign)) {
                this.semanticErrors.add(new SemanticError("SemanticError: " + assign.reportRepeated()));
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
            this.reportError(new SemanticError("SemanticError: " + funct.reportRepeated()));

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
        boolean isDefinedAsGlobal = this.symbolTable.isGlobalDefined(dad);
        boolean isDefinedAsLocal = this.symbolTable.isDefined(dad);
        boolean isDefined = !isDefinedAsLocal || !isDefinedAsGlobal;

        if (!isDefined) {
            this.reportError(new SemanticError(dad.reportRepeated()));
        } else {
            Object fromTable = this.symbolTable.get(dad.getIdentifier().getValue());
            Object fromGlobalVar = this.symbolTable.getFromGlobal(dad);
            Object varObj = isDefinedAsGlobal ? fromGlobalVar : fromTable;

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

        if (exp1.getType().equals(exp2.getType())) {
            this.reportError(new SemanticError("SemanticError: binary expression types mismatch"));
        }

    }

    public void checkArg(ParamDefinition arg, LinkedList<ParamDefinition> definedArgs) {

        for (ParamDefinition pd : definedArgs) {
            if (arg.equals(pd) || this.symbolTable.isDefined(arg)) {
                this.reportError(new SemanticError(
                        "SemanticError: param " + arg.getIdentifier().getValue() + " is already used"));
                return;
            }
        }
    }

    public void putParameters(LinkedList<ParamDefinition> args) {

        for (ParamDefinition pd : args) {
            if (this.symbolTable.isDefined(pd)) {
                this.reportError(new SemanticError(
                        "SemanticError: param " + pd.getIdentifier().getValue() + " is already defined"));
                return;
            } else {
                AssignDefinition ad = new AssignDefinition(pd.getIdentifier(), null);
                ad.setType(pd.get_Type());
                this.symbolTable.add(ad);
            }
        }
    }

    public void checkIdentificableExpression(IdentifierExpression iexp) {
        if (!this.symbolTable.isDefined(iexp) && !this.symbolTable.isGlobalDefined(iexp)) {
            this.reportError(new SemanticError("SemanticError: " + iexp.reportNoDefinition()));
        }
    }

    public void checkCalls() {
        this.callsMemo.forEach(e -> this.checkFunctionCall(e));
    }

    public void checkFunctionCall(FunctionCallExpression fce) {

        if (!this.symbolTable.isDefined(fce)) {
            this.reportError(new SemanticError("SemanticError: " + fce.reportRepeated()));
            return;
        }

        FunctionDefinition fd = (FunctionDefinition) this.symbolTable.get(fce.getSymbolIdentifier());
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

            this.codeGenerator.generate();
            System.out.println("Generated code: ");
            // System.out.println(this.codeGenerator.getCode());

            this.saveOut();
        }

    }

    public void saveOut() {
        FileUtils.savePlainTextFile(this.outDir + FileUtils.getCleanedProgramName(this.inputFilename) + ".asm",
                this.codeGenerator.getCode());
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
            this.codeGenerator = new CodeGenerator(this.programDefinition, this.inputFilename, this.symbolTable);

            this.errorFlag = this.semanticErrors.isEmpty() && analyzer.getLexicalErrors().isEmpty()
                    && analyzer.getSyntaxErrors().isEmpty();

            for (SemanticError semanticError : this.semanticErrors) {
                System.out.println(semanticError.toString());
            }
            System.out.println("Symbol Table:");
            System.out.println(this.symbolTable);
            this.generateCode();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void reportError(Error err) {
        if (err instanceof SemanticError)
            this.semanticErrors.add((SemanticError) err);
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