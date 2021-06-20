package itypes;

import java_cup.runtime.Symbol;
import model.Token;

public class IntTypeToken implements ITypeToken {

    private Symbol symbol;
    private Token token;

    public IntTypeToken(Symbol symbol) {
        this.symbol = symbol;
        this.token = (Token) symbol.value;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return IntTypeToken.class.getName() + " : " + this.token.getValue();
    }
}