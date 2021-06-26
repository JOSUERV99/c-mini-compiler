package iexpressions;

import model.LiteralToken;

public class LiteralStringExpression implements IExpression {

    private LiteralToken token;

    public LiteralStringExpression(LiteralToken token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LiteralStringExpression [token=" + token + "]";
    }

}