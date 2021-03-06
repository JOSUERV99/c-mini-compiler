package model;

public class OperatorToken extends Token {

    public OperatorToken(int start, int lineNumber, String value) {
        super(start, lineNumber, value);
        this.setType("OPERATOR");
    }

    @Override
    public String toString() {
        return super.getValue();
    }

}
