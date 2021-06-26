package ioperators;

import iexpressions.IExpression;
import model.KeywordToken;

public class UnaryOperator implements IExpression {
    private KeywordToken operator;

    public UnaryOperator(KeywordToken operator) {
        this.operator = operator;
    }

    public KeywordToken getOperator() {
        return operator;
    }

    public void setOperator(KeywordToken operator) {
        this.operator = operator;
    }

}
