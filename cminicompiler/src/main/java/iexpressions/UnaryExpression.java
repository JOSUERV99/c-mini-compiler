package iexpressions;

import ioperators.UnaryOperator;

public class UnaryExpression implements IExpression {
    private UnaryOperator uOperator;
    private IdentifierExpression identifier;
    private boolean sumDecFirst;

    public UnaryExpression(UnaryOperator uOperator, IdentifierExpression identifier, boolean sumDecFirst) {
        this.uOperator = uOperator;
        this.identifier = identifier;
        this.sumDecFirst = sumDecFirst;
    }
    public UnaryOperator getuOperator() {
        return uOperator;
    }
    public void setuOperator(UnaryOperator uOperator) {
        this.uOperator = uOperator;
    }
    public IdentifierExpression getIdentifier() {
        return identifier;
    }
    public void setIdentifier(IdentifierExpression identifier) {
        this.identifier = identifier;
    }
    public boolean isSumDecFirst() {
        return sumDecFirst;
    }
    public void setSumDecFirst(boolean sumDecFirst) {
        this.sumDecFirst = sumDecFirst;
    }
    @Override
    public String toString() {
        return "UnaryExpression: identifier= " + identifier + ", sumDecFirst= " + sumDecFirst + ", uOperator= " + uOperator;
    }
    


    
}
