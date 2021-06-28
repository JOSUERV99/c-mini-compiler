package itypes;

import iexpressions.AtomicExpression;
import iexpressions.IExpression;
import model.KeywordToken;

public class ConstTypeToken implements ITypeToken {
    private KeywordToken constType;
    private ITypeToken _type;
    private IExpression expresion;

    public ConstTypeToken(KeywordToken constType, ITypeToken type, IExpression expresion) {
        this.constType = constType;
        this._type = type;
        this.expresion = expresion;
    }

    public KeywordToken getConstType() {
        return constType;
    }

    public void setConstType(KeywordToken constType) {
        this.constType = constType;
    }

    public ITypeToken get_Type() {
        return _type;
    }

    public void set_Type(ITypeToken type) {
        this._type = type;
    }

    public IExpression getExpresion() {
        return expresion;
    }

    public void setExpresion(AtomicExpression expresion) {
        this.expresion = expresion;
    }

    @Override
    public String toString() {
        return "ConstTypeToken [_type=" + _type + ", constType=" + constType + ", expresion=" + expresion + "]";
    }

    @Override
    public String getType() {
        return this.get_Type().toString();
    }

    @Override
    public String getDefineBytes() {
        return this._type.getDefineBytes();
    }

}