package iexpressions;

import interpreter.IInstruction;

public abstract class IExpression implements IInstruction {
    private String expressionType;

    @Override
    public abstract String toString();

    public String getType() {
        return this.expressionType;
    }

    public void setType(String pType) {
        this.expressionType = pType;
    }
}
