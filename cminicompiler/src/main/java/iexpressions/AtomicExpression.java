package iexpressions;

import model.LiteralToken;

public class AtomicExpression extends IExpression {

    private LiteralToken token;

    public AtomicExpression(LiteralToken token) {
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
        return "AtomicExpression [token=" + token + "]";
    }

}
