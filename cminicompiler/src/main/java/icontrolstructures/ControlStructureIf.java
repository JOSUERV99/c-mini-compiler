package icontrolstructures;

import java.util.Collections;
import java.util.LinkedList;

import iexpressions.BinaryExpression;
import iexpressions.IExpression;
import iexpressions.IGramaticInstruction;
import iexpressions.IdentifierExpression;
import iexpressions.LiteralDecimalExpression;
import interpreter.ISemanticRegister;

public class ControlStructureIf implements IControlStructure, ISemanticRegister {

    private static int counterIf = 0;
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
        return "if";
    }

    @Override
    public String getCode() {
        String code = "";

        // if label handle
        int ifcounter = ++ControlStructureIf.counterIf;
        String ifCuerpo = "ifCuerpo" + ifcounter;
        String ifelse = "ifelse" + ifcounter;
        String finalif = "finalif" + ifcounter;

        IExpression expression = this.expControl;

        if (expression instanceof LiteralDecimalExpression) {
            Integer v = ((LiteralDecimalExpression) expression).getValue();
            code += "\tMOV ax, " + v.toString() + "\n";
            code += ";\t" + v.toString() + "\n";
        } else if (expression instanceof BinaryExpression) {
            BinaryExpression be = ((BinaryExpression) expression);
            code += ";\t" + be.toString() + "\n";
            code += be.getCode();
        } else if (expression instanceof IdentifierExpression) {
            IdentifierExpression ie = (IdentifierExpression) expression;
            code += ";\t" + ie.toString() + "\n";
            code += "\tMOV ax, " + ie.getSymbolIdentifier() + "\n";
        }

        code += "\tCMP ax, 0\n";
        code += "\tJNE " + ifCuerpo + "\n";
        code += "\tJMP " + ifelse + "\n";
        code += "\t\t" + ifCuerpo + ":\n";
        // Todo el codigo
        Collections.reverse(this.ifBody);
        for (IGramaticInstruction f : this.ifBody) {
            ISemanticRegister rs = (ISemanticRegister) f;
            code += ";\t" + rs.toString() + "\n";
            code += rs.getCode();
        }

        code += "\t\tJMP " + finalif + "\n";
        code += "\t" + ifelse + ":\n";
        // Todo el codigo de else
        Collections.reverse(this.elseBody);
        for (IGramaticInstruction f : this.elseBody) {
            ISemanticRegister rs = (ISemanticRegister) f;
            code += ";\t" + rs.toString() + "\n";
            code += rs.getCode();
        }
        code += "\t" + finalif + ":\n";

        return code;
    }
}