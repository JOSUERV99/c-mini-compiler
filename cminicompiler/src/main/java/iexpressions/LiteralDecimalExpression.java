package iexpressions;

import model.LiteralToken;
import utils.TypeUtils;

public class LiteralDecimalExpression extends IExpression {

    private Integer value;
    private LiteralToken token;

    public LiteralDecimalExpression(LiteralToken token) {
        this.token = token;
        this.value = TypeUtils.isInteger(token.getValue()) ? (Integer) Integer.parseInt(token.getValue()) : 0;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public LiteralToken getToken() {
        return this.token;
    }

    public void setToken(LiteralToken token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    @Override
    public String getType() {
        return "int";
    }

    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        return null;
    }
}
