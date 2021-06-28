package interpreter;

import iexpressions.BinaryExpression;
import iexpressions.IExpression;
import iexpressions.IdentifierExpression;
import iexpressions.LiteralDecimalExpression;
import itypes.ITypeToken;
import model.IdentifierToken;

public class DeclaredAssignDefinition implements IInstruction, Identificable, ISemanticRegister {

    private IdentifierToken identifier;
    private IExpression expression;
    private ITypeToken type;

    public DeclaredAssignDefinition(IdentifierToken identifier, IExpression expression) {
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
    public String toString() {
        return identifier.getValue() + "=" + expression.toString() + ";";
    }

    @Override
    public String getSymbolIdentifier() {
        return this.identifier.getValue();
    }

    @Override
    public String reportRepeated() {
        return this.getSymbolIdentifier() + " is not declared";
    }

    @Override
    public String getCode() {
        String code = "";

        if (expression instanceof LiteralDecimalExpression) {
            Integer v = ((LiteralDecimalExpression) expression).getValue();
            code += "\tMOV ax, " + v.toString() + "\n";
        } else if (expression instanceof BinaryExpression) {
            BinaryExpression be = ((BinaryExpression) expression);
            code += be.getCode();
        } else if (expression instanceof IdentifierExpression) {
            IdentifierExpression ie = (IdentifierExpression) expression;
            code += "\tMOV ax, " + ie.getSymbolIdentifier() + "\n";
        }

        code += "\tMOV " + this.getSymbolIdentifier() + ", ax\n";
        return code;
    }

}
