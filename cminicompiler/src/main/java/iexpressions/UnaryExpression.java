package iexpressions;

import ioperators.UnaryOperator;

public class UnaryExpression extends IExpression {
    private UnaryOperator uOperator;
    private IdentifierExpression identifier;
    private boolean sumDecFirst;

    public UnaryExpression(UnaryOperator uOperator, IdentifierExpression identifier, boolean sumDecFirst) {
        this.uOperator = uOperator;
        this.identifier = identifier;
        this.sumDecFirst = sumDecFirst;
    }

    public UnaryOperator getuOperator() {
        return uOperator;
    }

    public void setuOperator(UnaryOperator uOperator) {
        this.uOperator = uOperator;
    }

    public IdentifierExpression getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IdentifierExpression identifier) {
        this.identifier = identifier;
    }

    public boolean isSumDecFirst() {
        return sumDecFirst;
    }

    public void setSumDecFirst(boolean sumDecFirst) {
        this.sumDecFirst = sumDecFirst;
    }

    @Override
    public String toString() {
        return isSumDecFirst() ? this.uOperator.getOperator().getValue() + this.identifier.getToken().getValue() + ";"
                : this.identifier.getToken().getValue() + this.uOperator.getOperator().getValue() + ";";
    }

    @Override
    public String getCode() {

        String op = this.uOperator.getOperator().getValue();
        String code = "";
        String id = this.identifier.getSymbolIdentifier();

        code += "\tMOV ax, " + id + "\n";
        String asm = "";
        switch (op) {
            case "++":
                asm += "\tinc ax\n";
                break;
            case "--":
                asm += "\tdec ax\n";
                break;
        }

        if (isSumDecFirst()) {
            code = asm + code;
        } else {
            code += asm;
        }

        code += "\tMOV " + id + ", ax \n";

        return code;
    }
}
