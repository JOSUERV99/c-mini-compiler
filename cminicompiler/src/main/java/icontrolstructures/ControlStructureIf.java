package icontrolstructures;

import java.util.LinkedList;
import iexpressions.IExpression;
import iexpressions.IGramaticInstruction;

public class ControlStructureIf implements IControlStructure {
    private IExpression expControl;
    private LinkedList<IGramaticInstruction> ifBody;
    private LinkedList<IGramaticInstruction> elseBody;

    public ControlStructureIf(IExpression expControl, LinkedList<IGramaticInstruction> ifBody,
            LinkedList<IGramaticInstruction> elseBody) {
        this.expControl = expControl;
        this.ifBody = ifBody;
        this.elseBody = elseBody;
    }

    public IExpression getExpControl() {
        return this.expControl;
    }

    public void setExpControl(IExpression expControl) {
        this.expControl = expControl;
    }

    public LinkedList<IGramaticInstruction> getIfBody() {
        return ifBody;
    }

    public void setIfBody(LinkedList<IGramaticInstruction> ifBody) {
        this.ifBody = ifBody;
    }

    public LinkedList<IGramaticInstruction> getElseBody() {
        return elseBody;
    }

    public void setElseBody(LinkedList<IGramaticInstruction> elseBody) {
        this.elseBody = elseBody;
    }

    @Override
    public String toString() {
        return "ControlStructureIf: elseBody=" + elseBody + ", expControl=" + expControl + ", ifBody=" + ifBody + "]";
    }
}