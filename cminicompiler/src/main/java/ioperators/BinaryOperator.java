package ioperators;

import model.OperatorToken;

public class BinaryOperator implements IOperator {

    private OperatorToken type;

    public BinaryOperator(OperatorToken type) {
        this.type = type;
    }

    public OperatorToken getType() {
        return this.type;
    }

    public void setType(OperatorToken type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BinaryOperator [type=" + type + "]";
    }

}