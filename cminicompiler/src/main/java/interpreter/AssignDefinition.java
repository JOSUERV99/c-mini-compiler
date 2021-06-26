package interpreter;

import iexpressions.IExpression;
import itypes.ITypeToken;
import model.IdentifierToken;

public class AssignDefinition implements ISemanticRegister {

    private ITypeToken type;
    private IdentifierToken identifier;
    private IExpression expression;

    public AssignDefinition(IdentifierToken identifier, IExpression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public ITypeToken getType() {
        return type;
    }

    public void setType(ITypeToken type) {
        this.type = type;
    }

    public IExpression getExpression() {
        return expression;
    }

    public void setExpression(IExpression expression) {
        this.expression = expression;
    }

    public IdentifierToken getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IdentifierToken identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getCode() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        return type + " " + identifier + "=" + expression;
    }

}