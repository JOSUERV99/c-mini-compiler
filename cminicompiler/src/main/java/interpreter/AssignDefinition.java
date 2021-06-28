package interpreter;

import iexpressions.BinaryExpression;
import iexpressions.IExpression;
import iexpressions.IdentifierExpression;
import iexpressions.LiteralDecimalExpression;
import itypes.ITypeToken;
import model.IdentifierToken;

public class AssignDefinition implements ISemanticRegister, Identificable {

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
        String code = "";

        if (expression instanceof LiteralDecimalExpression) {
            Integer v = ((LiteralDecimalExpression) expression).getValue();
            code += "MOV ax, " + v.toString() + "\n";
        } else if (expression instanceof BinaryExpression) {
            BinaryExpression be = ((BinaryExpression) expression);
            code += be.getCode();
        } else if (expression instanceof IdentifierExpression) {
            IdentifierExpression ie = (IdentifierExpression) expression;
            code += "MOV ax, " + ie.getSymbolIdentifier() + "\n";
        }

        code += "MOV " + this.getSymbolIdentifier() + ", ax";
        return code;
    }

    @Override
    public String toString() {
        return type + " " + identifier + "=" + expression;
    }

    @Override
    public String getSymbolIdentifier() {
        return this.getIdentifier().getValue();
    }

    @Override
    public String reportRepeated() {
        return "The variable " + this.getSymbolIdentifier() + " has been declared";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expression == null) ? 0 : expression.hashCode());
        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AssignDefinition other = (AssignDefinition) obj;
        if (expression == null) {
            if (other.expression != null)
                return false;
        } else if (!expression.equals(other.expression))
            return false;
        if (identifier == null) {
            if (other.identifier != null)
                return false;
        } else if (!identifier.getValue().equals(other.identifier.getValue()))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.getType().equals(other.type.getType()))
            return false;
        return true;
    }

}