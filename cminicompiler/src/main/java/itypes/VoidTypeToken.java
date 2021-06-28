package itypes;

import model.Token;

public class VoidTypeToken implements ITypeToken {

    private Token token;

    public VoidTypeToken(Token token) {
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
        return "void";
    }

    @Override
    public String getDefineBytes() {
        return "dw";
    }
}