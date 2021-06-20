package interpreter;

import itypes.ITypeToken;
import java.util.LinkedList;

public class GlobalVarDefinition {

    private ITypeToken type;
    private LinkedList<AssignDefinition> assignments;

    public GlobalVarDefinition(ITypeToken type, LinkedList<AssignDefinition> assignments) {
        this.type = type;
        this.assignments = assignments;
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
}
