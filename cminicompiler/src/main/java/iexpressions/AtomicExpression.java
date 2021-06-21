package iexpressions;

import model.Token;

public class AtomicExpression extends IExpression {

    private String value;

    public AtomicExpression(Token token) {
        super(token);
        this.value = token.getValue();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public Object parse(String value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        return "AtomicExpression [value=" + value + "]";
    }

}
