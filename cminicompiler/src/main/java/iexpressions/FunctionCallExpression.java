package iexpressions;

import java.util.LinkedList;

import model.IdentifierToken;

public class FunctionCallExpression implements IExpression {

    private IdentifierToken identifier;
    private LinkedList<IExpression> params;

    public FunctionCallExpression(IdentifierToken identifier, LinkedList<IExpression> params) {
        this.identifier = identifier;
        this.params = params;
    }

    public IdentifierToken getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IdentifierToken identifier) {
        this.identifier = identifier;
    }

    public LinkedList<IExpression> getParams() {
        return params;
    }

    public void setParams(LinkedList<IExpression> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return identifier + "(" + params + ")";
    }

}
