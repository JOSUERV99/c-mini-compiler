package code;

import iexpressions.LiteralDecimalExpression;
import interpreter.AssignDefinition;
import interpreter.Identificable;
import interpreter.ProgramDefinition;
import semantic.SymbolTable;
import utils.FileUtils;
import code.AssemblerStatic;

public class CodeGenerator {

    private SymbolTable symbolTable;
    private ProgramDefinition pd;
    private StringBuilder code;

    public CodeGenerator(ProgramDefinition pd, String filename, SymbolTable symbolTable) {
        this.pd = pd;
        this.code = new StringBuilder();
        this.symbolTable = symbolTable;
    }

    public void generate() {
        this.generateDataSegment();
        this.generateStackSegment();
        this.generateCodeSegment();
    }

    public void generateDataSegment() {

        code.append("datos segment\n");

        if (this.pd.getFunctionDefinitions().size() != 1) {
            System.out.println("No function was founded");
        }

        String uniqueFunctionName = this.pd.getFunctionDefinitions().getLast().getSymbolIdentifier();

        this.symbolTable.remove(uniqueFunctionName); // delete function name

        for (Identificable id : symbolTable.getGlobalVars()) {
            // detecting globals, params, vars
            if (id instanceof AssignDefinition) {
                AssignDefinition ad = (AssignDefinition) id;
                code.append(ad.getType().getDefineBytes());
                code.append(" ");
                code.append(id.getSymbolIdentifier());
                code.append(" ");

                if (ad.getExpression() instanceof LiteralDecimalExpression) {
                    LiteralDecimalExpression exp = (LiteralDecimalExpression) ad.getExpression();
                    code.append(exp.getValue().toString());
                } else {
                    code.append("0");
                }
                code.append("\n");
            }

        }

        code.append("datos endS\n");

    }

    public void generateStackSegment() {
        code.append(AssemblerStatic.DefPilaSegment);
    }

    public void generateCodeSegment() {
        code.append(AssemblerStatic.DefCodeSegment);
        code.append(AssemblerStatic.DefMainCode);

        code.append(AssemblerStatic.DefFinalMainCode);
    }

    public String getCode() {
        return this.code.toString();
    }

    public ProgramDefinition getPd() {
        return pd;
    }

    public void setPd(ProgramDefinition pd) {
        this.pd = pd;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

}