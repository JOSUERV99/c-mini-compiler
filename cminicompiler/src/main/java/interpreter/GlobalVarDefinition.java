package interpreter;

import itypes.ITypeToken;
import java.util.LinkedList;

public class GlobalVarDefinition implements Initializable {

    private ITypeToken type;
    private LinkedList<AssignDefinition> assignments;

    public GlobalVarDefinition(VarDefinition var) {
        this.type = var.getType();
        this.assignments = var.getAssignments();
    }

    public GlobalVarDefinition(ITypeToken type, LinkedList<AssignDefinition> assignments) {
        this.type = type;
        this.assignments = assignments;
    }

    public void setTypeForEachAssign() {
        if (this.type == null)
            return;

        for (AssignDefinition ad : assignments)
            ad.setType(this.type);
    }

    public ITypeToken getType() {
        return type;
    }

    public void setType(ITypeToken type) {
        this.type = type;
    }

    public LinkedList<AssignDefinition> getAssignments() {
        return assignments;
    }

    public void setAssignments(LinkedList<AssignDefinition> assignments) {
        this.assignments = assignments;
    }

    @Override
    public String toString() {
        return "GlobalVarDefinition [assignments=" + assignments + ", type=" + type + "]";
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub

    }

}
