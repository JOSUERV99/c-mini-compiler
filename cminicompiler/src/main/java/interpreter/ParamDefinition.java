package interpreter;

import itypes.ITypeToken;
import model.IdentifierToken;

public class ParamDefinition implements ISemanticRegister {

    private IdentifierToken identifier;
    private ITypeToken type;

    public ParamDefinition(ITypeToken type, IdentifierToken identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    public IdentifierToken getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IdentifierToken identifier) {
        this.identifier = identifier;
    }

    public ITypeToken getType() {
        return type;
    }

    public void setType(ITypeToken type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ParamDefinition: identifier= " + identifier + ", type= " + type;
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

}