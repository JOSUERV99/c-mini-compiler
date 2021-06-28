package iexpressions;

import ioperators.UnaryOperator;

public class BooleanUnaryExpression extends IExpression {
    private UnaryOperator op;
    private IExpression exp;

    public BooleanUnaryExpression(UnaryOperator op, IExpression exp) {
        this.op = op;
        this.exp = exp;
    }

    public UnaryOperator getOp() {
        return this.op;
    }

    public void setOp(UnaryOperator op) {
        this.op = op;
    }

    public IExpression getExp() {
        return this.exp;
    }

    public void setExp(IExpression exp) {
        this.exp = exp;
    }

    @Override
    public String getCode() {

        String op = this.op.getType();
        String code = "";

        if (exp instanceof LiteralDecimalExpression) {
            Integer v = ((LiteralDecimalExpression) exp).getValue();
            code += "MOV ax, " + v.toString() + "\n";
        } else if (exp instanceof BinaryExpression) {
            BinaryExpression be = ((BinaryExpression) exp);
            code += be.getCode();
        } else if (exp instanceof IdentifierExpression) {
            IdentifierExpression ie = (IdentifierExpression) exp;
            code += "MOV ax, " + ie.getSymbolIdentifier() + "\n";
        }

        switch (op) {
            case "~":
                code += "NOT ax";
                break;
            case "!":
                code += "PUSH bx" + "\n";

                code += "XOR bx, bx" + "\n";
                code += "call GreaterThan" + "\n";

                code += "POP bx" + "\n";
                break;
        }

        return code;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

}