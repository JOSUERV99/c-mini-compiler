package ioperators;

import model.KeywordToken;

public class BinaryOperator implements IOperator {

    private KeywordToken type;

    public BinaryOperator(KeywordToken type) {
        this.type = type;
    }

    public KeywordToken getType() {
        return type;
    }

    public void setType(KeywordToken type) {
        this.type = type;
    }

}