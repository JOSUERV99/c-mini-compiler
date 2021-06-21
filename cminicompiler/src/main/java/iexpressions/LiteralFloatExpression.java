package iexpressions;

import model.Token;

public class LiteralFloatExpression extends IExpression {

    private Float value;

    public LiteralFloatExpression(Token token) {
        super(token);
        // this.value = TypeUtils.isInteger(token.getValue()) ? (Integer)
        // this.parse(token.getValue()) : null;
    }

    @Override
    public Object parse(String value) {
        // TODO Auto-generated method stub
        return null;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LiteralFloatExpression [value=" + value + "]";
    }

}