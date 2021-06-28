package interpreter;

import itypes.ITypeToken;
import model.IdentifierToken;

public class ParamDefinition implements ISemanticRegister, Identificable {

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

    public ITypeToken get_Type() {
        return type;
    }

    public String getType() {
        return this.type.toString();
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
    public String getSymbolIdentifier() {
        return this.getIdentifier().getValue();
    }

    @Override
    public String reportRepeated() {
        return "The param " + this.getSymbolIdentifier() + " is already in used";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ParamDefinition other = (ParamDefinition) obj;
        if (identifier == null) {
            if (other.identifier != null)
                return false;
        } else if (!identifier.equals(other.identifier))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

}