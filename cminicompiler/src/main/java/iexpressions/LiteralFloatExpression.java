package iexpressions;

import model.LiteralToken;
import utils.TypeUtils;

public class LiteralFloatExpression extends IExpression {

    private Float value;
    private LiteralToken token;

    public LiteralFloatExpression(LiteralToken token) {
        this.token = token;
        this.value = TypeUtils.isFloat(token.getValue()) ? Float.parseFloat(token.getValue()) : null;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public LiteralToken getToken() {
        return token;
    }

    public void setToken(LiteralToken token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LiteralFloatExpression [value=" + value + "]";
    }

    @Override
    public String getType() {
        return "float";
    }
}