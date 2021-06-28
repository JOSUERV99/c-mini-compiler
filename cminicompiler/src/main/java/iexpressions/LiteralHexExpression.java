package iexpressions;

import model.LiteralToken;

public class LiteralHexExpression extends IExpression {

    private LiteralToken token;

    public LiteralHexExpression(LiteralToken token) {
        this.token = token;
    }

    public LiteralToken getToken() {
        return token;
    }

    public void setToken(LiteralToken token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LiteralHexExpression [token=" + token + "]";
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
