package iexpressions;

import utils.TypeUtils;

public class LiteralDecimalExpression implements IExpression {

    private Integer value;

    public LiteralDecimalExpression(String value) {
        this.value = TypeUtils.isInteger(value) ? (Integer) this.parse(value) : null;
    }

    @Override
    public Object parse(String value) {
        return Integer.parseInt(value);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
