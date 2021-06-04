package errors;

import model.Token;

public class Error {

    private String errorMessage;
    private Token token;

    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Error(String errorMessage, Token token) {
        this.errorMessage = errorMessage;
        this.token = token;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return token == null ? errorMessage
                : errorMessage + ", with " + token.getValue() + " at line:" + (token.getLine()) + ", column:"
                        + (token.getColumn());
    }
}
