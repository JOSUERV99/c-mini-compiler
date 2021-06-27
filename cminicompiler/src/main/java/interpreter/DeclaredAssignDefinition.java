package interpreter;

import iexpressions.IExpression;
import model.IdentifierToken;

public class DeclaredAssignDefinition implements IInstruction, Identificable {

    private IdentifierToken identifier;
    private IExpression expression;

    public DeclaredAssignDefinition(IdentifierToken identifier, IExpression expression) {
        this.identifier = identifier;
        this.expression = expression;
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
    public String toString() {
        return "DeclaredAssignDefinition: " + identifier + "=" + expression;
    }

    @Override
    public String getSymbolIdentifier() {
        return this.identifier.getValue();
    }

    @Override
    public String reportRepeated() {
        return this.getSymbolIdentifier() + " is not declared";
    }

}
