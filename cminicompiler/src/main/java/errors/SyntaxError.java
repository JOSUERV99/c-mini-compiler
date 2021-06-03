package errors;

import java_cup.runtime.Symbol;

public class SyntaxError extends Error {

    public SyntaxError(String errorMessage, Symbol symbol) {
        super(errorMessage, symbol);
    }

    public SyntaxError(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "SyntaxError: " + super.toString();
    }
}
