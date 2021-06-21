package iexpressions;

import model.Token;

public class IdentifierExpression extends IExpression {

    public IdentifierExpression(Token token) {
        super(token);
    }

    @Override
    public Object parse(String value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        return "IdentifierExpression []";
    }

}
