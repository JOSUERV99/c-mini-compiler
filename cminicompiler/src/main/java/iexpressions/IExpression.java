package iexpressions;

import model.Token;

public abstract class IExpression {

    public IExpression(Token token) {
        this.token = token;
    }

    public Token token;

    public abstract Object parse(String value);

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public abstract String toString();

}
