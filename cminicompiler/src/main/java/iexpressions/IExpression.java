package iexpressions;

import interpreter.IInstruction;
import interpreter.ISemanticRegister;

public abstract class IExpression implements IInstruction, ISemanticRegister {
    private String expressionType;

    @Override
    public abstract String toString();

    public String getType() {
        return this.expressionType;
    }

    public void setType(String pType) {
        this.expressionType = pType;
    }

    public static boolean areLiterals(IExpression exp1, IExpression exp2) {
        return (exp1 instanceof LiteralDecimalExpression) && (exp2 instanceof LiteralDecimalExpression);
    }

    @Override
    public String getCode() {
        return "";
    }
}
