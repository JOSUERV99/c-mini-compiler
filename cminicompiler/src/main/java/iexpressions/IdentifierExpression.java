package iexpressions;

import model.IdentifierToken;

public class IdentifierExpression implements IExpression {

    private IdentifierToken token;

    public IdentifierExpression(IdentifierToken token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "IdentifierExpression [" + this.token.getValue() + "]";
    }

}
