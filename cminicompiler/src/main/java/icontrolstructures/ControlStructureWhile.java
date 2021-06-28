package icontrolstructures;

import java.util.Collections;
import java.util.LinkedList;

import iexpressions.BinaryExpression;
import iexpressions.IExpression;
import iexpressions.IGramaticInstruction;
import iexpressions.IdentifierExpression;
import iexpressions.LiteralDecimalExpression;
import interpreter.ISemanticRegister;

public class ControlStructureWhile implements IControlStructure, ISemanticRegister {
    private static int counterWhile = 0;
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
        return "while";
    }

    @Override
    public String getCode() {
        String code = "";

        // while label handle
        int whileCounter = ++ControlStructureWhile.counterWhile;
        String whileStart = "while" + whileCounter;
        String whileCuerpo = "whileCuerpo" + whileCounter;
        String finalWhile = "finalWhile" + whileCounter;

        code += "\t" + whileStart + ":\n";

        IExpression expression = this.expControl;

        if (expression instanceof LiteralDecimalExpression) {
            Integer v = ((LiteralDecimalExpression) expression).getValue();
            code += "\tMOV ax, " + v.toString() + "\n";
        } else if (expression instanceof BinaryExpression) {
            BinaryExpression be = ((BinaryExpression) expression);
            code += be.getCode();
        } else if (expression instanceof IdentifierExpression) {
            IdentifierExpression ie = (IdentifierExpression) expression;
            code += "\tMOV ax, " + ie.getSymbolIdentifier() + "\n";
        }

        code += "\tCMP ax, 1\n";
        code += "\tJE " + whileCuerpo + "\n";
        code += "\tJMP " + finalWhile + "\n";
        code += "\t" + whileCuerpo + ":\n";
        // Todo el codigo
        Collections.reverse(this.gramaticBody);
        for (IGramaticInstruction f : this.gramaticBody) {
            ISemanticRegister rs = (ISemanticRegister) f;
            code += ";\t" + rs.toString() + "\n";
            code += rs.getCode();
        }
        code += "\tJMP " + whileStart + "\n";
        code += "\t" + finalWhile + ":\n";

        return code;
    }
}