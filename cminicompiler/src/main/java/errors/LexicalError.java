package errors;

import java_cup.runtime.Symbol;

public class LexicalError extends Error {

    public LexicalError(String errorMessage, Symbol symbol) {
        super(errorMessage, symbol);
    }

    public LexicalError(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "LexicalError: " + super.toString();
    }
}
