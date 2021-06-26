package iexpressions;

import ioperators.BinaryOperator;

public class BinaryExpression implements IExpression {

    private IExpression exp1, exp2;
    private BinaryOperator operator;

    public BinaryExpression(IExpression exp1, BinaryOperator operator, IExpression exp2) {

        this.exp1 = exp1;
        this.operator = operator;
        this.exp2 = exp2;
    }

    public IExpression getExp1() {
        return this.exp1;
    }

    public void setExp1(IExpression exp1) {
        this.exp1 = exp1;
    }

    public IExpression getExp2() {
        return this.exp2;
    }

    public void setExp2(IExpression exp2) {
        this.exp2 = exp2;
    }

    public BinaryOperator getOperator() {
        return this.operator;
    }

    public void setOperator(BinaryOperator operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "BinaryExpression [exp1=" + exp1 + ", exp2=" + exp2 + ", operator=" + operator + "]";
    }

}