package iexpressions;

import model.LiteralToken;

public class LiteralStringExpression extends IExpression {

    private LiteralToken token;

    public LiteralStringExpression(LiteralToken token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LiteralStringExpression [token=" + token + "]";
    }

    @Override
    public String getType() {
        return "string";
    }

}