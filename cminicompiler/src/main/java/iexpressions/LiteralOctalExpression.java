package iexpressions;

import model.LiteralToken;

public class LiteralOctalExpression extends IExpression {

    private LiteralToken token;

    public LiteralOctalExpression(LiteralToken token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LiteralOctalExpression [token=" + token + "]";
    }

    @Override
    public String getType() {
        return "int";
    }

    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        return null;
    }
}