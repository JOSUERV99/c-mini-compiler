package itypes;

import iexpressions.AtomicExpression;
import iexpressions.IExpression;
import model.KeywordToken;

public class ConstTypeToken implements ITypeToken {
    private KeywordToken constType;
    private ITypeToken type;
    private IExpression expresion;

    public ConstTypeToken(KeywordToken constType, ITypeToken type, IExpression expresion) {
        this.constType = constType;
        this.type = type;
        this.expresion = expresion;
    }

    public KeywordToken getConstType() {
        return constType;
    }

    public void setConstType(KeywordToken constType) {
        this.constType = constType;
    }

    public ITypeToken getType() {
        return type;
    }

    public void setType(ITypeToken type) {
        this.type = type;
    }

    public IExpression getExpresion() {
        return expresion;
    }

    public void setExpresion(AtomicExpression expresion) {
        this.expresion = expresion;
    }

    @Override
    public String toString() {
        return "ConstTypeToken: constType: " + constType + ", expresion: " + expresion + ", type:" + type;
    }

}