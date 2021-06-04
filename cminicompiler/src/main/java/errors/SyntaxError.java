package errors;

import model.Token;

public class SyntaxError extends Error {

    public SyntaxError(String errorMessage, Token token) {
        super(errorMessage, token);
    }

    public SyntaxError(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "SyntaxError: " + super.toString();
    }
}
