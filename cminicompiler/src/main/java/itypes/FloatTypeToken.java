package itypes;

import model.Token;

public class FloatTypeToken implements ITypeToken {

    private Token token;

    public FloatTypeToken(Token token) {
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

    @Override
    public String getType() {
        return "float";
    }

    @Override
    public String getDefineBytes() {
        return "dw";
    }
}