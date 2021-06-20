package interpreter;

import itypes.ITypeToken;

public class AssignDefinition implements ISemanticRegister {

    private ITypeToken type;
    private String identifier, value;

    public AssignDefinition(String identifier, String value) {
        this.identifier = identifier;
        this.value = value;
    }

    public ITypeToken getType() {
        return type;
    }

    public void setType(ITypeToken type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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