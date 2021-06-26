package iexpressions;

import model.LiteralToken;

public class LiteralOctalExpression implements IExpression {

    private LiteralToken token;

    public LiteralOctalExpression(LiteralToken token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LiteralOctalExpression [token=" + token + "]";
    }
}