package interpreter;

import java.util.LinkedList;

public class ProgramDefinition implements ISemanticRegister {

    private LinkedList<GlobalVarDefinition> globalVars;

    public ProgramDefinition() {
        this.globalVars = new LinkedList<>();
    }

    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return null;
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
        return "ProgramDefinition [globalVars=" + globalVars + "]";
    }

}
