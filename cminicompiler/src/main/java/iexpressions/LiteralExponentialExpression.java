package iexpressions;

import model.LiteralToken;
import utils.TypeUtils;

public class LiteralExponentialExpression extends IExpression {

    private Integer value;
    private LiteralToken token;

    public LiteralExponentialExpression(LiteralToken token) {
        this.token = token;
        this.value = TypeUtils.isInteger(token.getValue()) ? (Integer) Integer.parseInt(token.getValue()) : null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
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
        return "LiteralExponentialExpression [token= " + token + ", value= " + value + "]";
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
