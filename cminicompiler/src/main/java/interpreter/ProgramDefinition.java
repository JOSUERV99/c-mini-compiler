package interpreter;

import java.util.LinkedList;

public class ProgramDefinition implements ISemanticRegister {

    private LinkedList<GlobalVarDefinition> globalVars;
    private LinkedList<FunctionDefinition> functionDefinitions;

    public ProgramDefinition() {
        this.globalVars = new LinkedList<>();
        this.functionDefinitions = new LinkedList<>();
    }

    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        return null;
    }

    public LinkedList<FunctionDefinition> getFunctionDefinitions() {
        return functionDefinitions;
    }

    public void setFunctionDefinitions(LinkedList<FunctionDefinition> functionDefinitions) {
        this.functionDefinitions = functionDefinitions;
    }

    public void addFunction(FunctionDefinition function) {
        this.functionDefinitions.addLast(function);
    }

    public void addGlobalVar(GlobalVarDefinition globalVar) {
        this.globalVars.addLast(globalVar);
    }

    public LinkedList<GlobalVarDefinition> getGlobalVars() {
        return globalVars;
    }

    public void setGlobalVars(LinkedList<GlobalVarDefinition> globalVars) {
        this.globalVars = globalVars;
    }

    @Override
    public String toString() {
        return "ProgramDefinition: functionDefinitions= " + functionDefinitions + ", globalVars= " + globalVars;
    }

}
