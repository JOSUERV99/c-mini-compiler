package itypes;

import model.Token;

public class IntTypeToken implements ITypeToken {

    private Token token;

    public IntTypeToken(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return this.token.getValue();
    }
}