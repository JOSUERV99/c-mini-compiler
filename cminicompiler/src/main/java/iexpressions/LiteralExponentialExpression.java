package iexpressions;

import model.Token;
import utils.TypeUtils;

public class LiteralExponentialExpression extends IExpression {

    private Integer value;

    public LiteralExponentialExpression(Token token) {
        super(token);
        this.value = TypeUtils.isInteger(token.getValue()) ? (Integer) this.parse(token.getValue()) : null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public Object parse(String value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        return "LiteralExponentialExpression [value=" + value + "]";
    }

}
