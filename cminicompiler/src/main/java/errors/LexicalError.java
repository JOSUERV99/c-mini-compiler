package errors;

import model.Token;

public class LexicalError extends Error {

    public LexicalError(String errorMessage, Token token) {
        super(errorMessage, token);
    }

    public LexicalError(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "LexicalError: " + super.toString();
    }
}
