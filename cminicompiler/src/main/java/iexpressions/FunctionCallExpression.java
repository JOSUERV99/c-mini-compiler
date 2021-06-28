package iexpressions;

import java.util.LinkedList;

import interpreter.Identificable;
import itypes.ITypeToken;
import model.IdentifierToken;

public class FunctionCallExpression extends IExpression implements Identificable {

    private ITypeToken type;
    private IdentifierToken identifier;
    private LinkedList<IExpression> params;

    public FunctionCallExpression(IdentifierToken identifier, LinkedList<IExpression> params) {
        this.identifier = identifier;
        this.params = params;
    }

    public IdentifierToken getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IdentifierToken identifier) {
        this.identifier = identifier;
    }

    public LinkedList<IExpression> getParams() {
        return params;
    }

    public void setParams(LinkedList<IExpression> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return identifier + "(" + params + ")";
    }

    @Override
    public String getSymbolIdentifier() {
        return this.getIdentifier().getValue();
    }

    @Override
    public String reportRepeated() {
        return "";
    }

    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        return null;
    }

}
