package iexpressions;

import model.LiteralToken;

public class LiteralHexExpression implements IExpression {

    private LiteralToken token;

    public LiteralHexExpression(LiteralToken token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LiteralHexExpression [token=" + token + "]";
    }

}
