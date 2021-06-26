package ioperators;

import iexpressions.IExpression;
import model.OperatorToken;

public class UnaryOperator implements IExpression {
    private OperatorToken operator;

    public UnaryOperator(OperatorToken operator) {
        this.operator = operator;
    }

    public OperatorToken getOperator() {
        return operator;
    }

    public void setOperator(OperatorToken operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "UnaryOperator [operator=" + operator + "]";
    }
}