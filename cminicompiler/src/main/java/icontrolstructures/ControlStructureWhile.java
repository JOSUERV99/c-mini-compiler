package icontrolstructures;

import java.util.LinkedList;
import iexpressions.IExpression;
import iexpressions.IGramaticInstruction;

public class ControlStructureWhile implements IControlStructure {
    private IExpression expControl;
    private LinkedList<IGramaticInstruction> gramaticBody;

    public ControlStructureWhile(IExpression expControl, LinkedList<IGramaticInstruction> gramaticBody) {
        this.expControl = expControl;
        this.gramaticBody = gramaticBody;
    }

    public IExpression getExpControl() {
        return this.expControl;
    }

    public void setExpControl(IExpression expControl) {
        this.expControl = expControl;
    }

    public LinkedList<IGramaticInstruction> getGramaticBody() {
        return this.gramaticBody;
    }

    public void setGramaticBody(LinkedList<IGramaticInstruction> gramaticBody) {
        this.gramaticBody = gramaticBody;
    }

    @Override
    public String toString() {
        return "While: " + " expControl= " + getExpControl() + ", gramaticBody= " + getGramaticBody();
    }
}