package iexpressions;

import model.Token;
import utils.TypeUtils;

public class LiteralDecimalExpression extends IExpression {

    private Integer value;

    public LiteralDecimalExpression(Token token) {
        super(token);
        this.value = TypeUtils.isInteger(token.getValue()) ? (Integer) this.parse(token.getValue()) : null;
    }

    @Override
    public Object parse(String value) {
        return Integer.parseInt(value);
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LiteralDecimalExpression [value=" + value + "]";
    }

}
