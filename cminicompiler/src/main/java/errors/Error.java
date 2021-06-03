package errors;

import java_cup.runtime.Symbol;

public class Error {

    private String errorMessage;
    private Symbol symbol;

    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Error(String errorMessage, Symbol symbol) {
        this.errorMessage = errorMessage;
        this.symbol = symbol;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol == null ? errorMessage
                : errorMessage + ", with " + symbol + " at line:" + (symbol.right + 1) + ", column:"
                        + (symbol.left + 1);
    }
}
