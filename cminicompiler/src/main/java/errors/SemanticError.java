package errors;

import model.Token;

public class SemanticError extends Error {

    public SemanticError(String errorMessage, Token token) {
        super(errorMessage, token);
    }

    public SemanticError(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "SemanticError: " + super.toString();
    }
}
