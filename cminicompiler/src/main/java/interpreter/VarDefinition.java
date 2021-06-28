package interpreter;

import itypes.ITypeToken;
import java.util.LinkedList;

import iexpressions.IGramaticInstruction;

public class VarDefinition implements ISemanticRegister, Initializable, IGramaticInstruction, IInstruction {

    private ITypeToken type;
    private LinkedList<AssignDefinition> assignments;

    public VarDefinition(VarDefinition vd) {
        this.setType(vd.getType());
        this.setAssignments(vd.getAssignments());
    }

    public VarDefinition(ITypeToken type, LinkedList<AssignDefinition> assignments) {

        this.type = type;
        this.assignments = assignments;

        this.initialize();
    }

    @Override
    public void initialize() {
        this.assignType();
    }

    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        return null;
    }

    public void assignType() {

        if (this.type == null || this.assignments == null || this.assignments.size() == 0)
            return;

        for (AssignDefinition ad : assignments) {
            ad.setType(this.type);
        }
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
        return "VarDefinition: assignments= " + assignments + ", type= " + type;
    }

}
