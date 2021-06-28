package iexpressions;

import ioperators.BinaryOperator;

public class BinaryExpression extends IExpression {

    private IExpression exp1, exp2;
    private BinaryOperator operator;

    public BinaryExpression(IExpression exp1, BinaryOperator operator, IExpression exp2) {

        this.exp1 = exp1;
        this.operator = operator;
        this.exp2 = exp2;
    }

    public IExpression getExp1() {
        return this.exp1;
    }

    public void setExp1(IExpression exp1) {
        this.exp1 = exp1;
    }

    public IExpression getExp2() {
        return this.exp2;
    }

    public void setExp2(IExpression exp2) {
        this.exp2 = exp2;
    }

    public BinaryOperator getOperator() {
        return this.operator;
    }

    public void setOperator(BinaryOperator operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return exp1.toString() + " " + this.operator.getType().getValue() + " " + this.exp2.toString();
    }

    @Override
    public String getType() {
        return this.exp1.getType().equals(this.exp2.getType()) ? this.exp2.getType() : "undefined";
    }

    @Override
    public String getCode() {

        String op = this.operator.getType().getValue();
        if (IExpression.areLiterals(exp1, exp2)) {

            Integer v1 = ((LiteralDecimalExpression) exp1).getValue();
            Integer v2 = ((LiteralDecimalExpression) exp2).getValue();
            Integer v3 = 0;

            switch (op) {
                case "+":
                    v3 = v1 + v2;
                    break;
                case "-":
                    v3 = v1 - v2;
                    break;
                case "/":
                    v3 = v2 != 0 ? v1 / v2 : 0;
                    break;
                case "*":
                    v3 = v1 * v2;
                    break;
                case "%":
                    v3 = v2 == 0 ? 0 : v1 % v2;
                    break;
                case ">":
                    v3 = v1 > v2 ? 1 : 0;
                    break;
                case "<":
                    v3 = v1 < v2 ? 1 : 0;
                    break;
                case ">=":
                    v3 = v1 >= v2 ? 1 : 0;
                    break;
                case "<=":
                    v3 = v1 <= v2 ? 1 : 0;
                    break;
                case "==":
                    v3 = v1 == v2 ? 1 : 0;
                    break;
                case "!=":
                    v3 = v1 != v2 ? 1 : 0;
                    break;
                case "&":
                    v3 = v1 & v2;
                    break;
                case "&&":
                    v3 = v1 != 0 & v2 != 0 ? 1 : 0;
                    break;
                case "|":
                    v3 = v1 | v2;
                    break;
                case "||":
                    v3 = v1 == 1 | v2 == 1 ? 1 : 0;
                    break;
                case "^":
                    v3 = (v1 & (v2 != 0 ? 1 : 0));
                    break;
            }

            return "\tMOV ax," + v3.toString() + "\n";
        } else {

            String op_Assembler = "";

            String id1 = (exp1 instanceof IdentifierExpression) ? (((IdentifierExpression) exp1).getSymbolIdentifier())
                    : (((LiteralDecimalExpression) exp1).getValue()).toString();

            String id2 = (exp2 instanceof IdentifierExpression) ? (((IdentifierExpression) exp2).getSymbolIdentifier())
                    : (((LiteralDecimalExpression) exp2).getValue()).toString();
            op_Assembler += "\tMOV ax, " + id1 + "\n";
            switch (op) {
                case "+":
                    op_Assembler += "\tADD ax, " + id2 + "\n";
                    break;
                case "-":
                    op_Assembler += "\tSUB ax, " + id2 + "\n";
                    break;
                case "/":
                    op_Assembler += "\tPUSH bx \n";
                    op_Assembler += "\tMOV bx, " + id2 + "\n";
                    op_Assembler += "\tDIV bl\n";
                    op_Assembler += "\tMOV bl, al\n";
                    op_Assembler += "\tXOR ax, ax\n";
                    op_Assembler += "\tOR al, bl\n";
                    op_Assembler += "\tPOP bx \n";
                    break;
                case "*":
                    op_Assembler += "\tPUSH dx \n";
                    op_Assembler += "\tMUL " + id2 + "\n";
                    op_Assembler += "\tPOP dx \n";
                    break;
                case "%":
                    op_Assembler += "\tPUSH bx \n";
                    op_Assembler += "\tMOV bx, " + id2 + "\n";
                    op_Assembler += "\tDIV bl\n";
                    op_Assembler += "\tMOV bl, ah\n";
                    op_Assembler += "\tXOR ax, ax\n";
                    op_Assembler += "\tOR al, bl\n";
                    op_Assembler += "\tPOP bx \n";
                    break;
                case ">":
                    op_Assembler += "\tPUSH bx" + "\n";
                    op_Assembler += "\tMOV bx, " + id2 + "\n";
                    op_Assembler += "\tcall GreaterThan " + "\n";
                    op_Assembler += "\tPOP bx" + "\n";
                    break;
                case "<":
                    op_Assembler += "\tPUSH bx" + "\n";
                    op_Assembler += "\tMOV bx, " + id2 + "\n";
                    op_Assembler += "\tcall LessThan " + "\n";
                    op_Assembler += "\tPOP bx" + "\n";
                    break;
                case ">=":
                    op_Assembler += "\tPUSH bx" + "\n";
                    op_Assembler += "\tMOV bx, " + id2 + "\n";
                    op_Assembler += "\tcall AboveEqualThan " + "\n";
                    op_Assembler += "\tPOP bx" + "\n";
                    break;
                case "<=":
                    op_Assembler += "\tPUSH bx" + "\n";
                    op_Assembler += "\tMOV bx, " + id2 + "\n";
                    op_Assembler += "\tcall BelowEqualThan " + "\n";
                    op_Assembler += "\tPOP bx" + "\n";
                    break;
                case "==":
                    op_Assembler += "\tPUSH bx" + "\n";
                    op_Assembler += "\tMOV bx, " + id2 + "\n";
                    op_Assembler += "\tcall EqualThan " + "\n";
                    op_Assembler += "\tPOP bx" + "\n";
                    break;
                case "!=":
                    op_Assembler += "\tPUSH bx" + "\n";
                    op_Assembler += "\tMOV bx, " + id2 + "\n";
                    op_Assembler += "\tcall EqualThan " + "\n";
                    op_Assembler += "\tnot ax" + "\n";
                    op_Assembler += "\tPOP bx" + "\n";
                    break;
                case "&":
                    op_Assembler += "\tAND ax, " + id2 + "\n";
                    break;
                case "&&":
                    op_Assembler += "\tPUSH bx" + "\n";
                    op_Assembler += "\tPUSH dx" + "\n";

                    op_Assembler += "\tXOR bx, bx" + "\n";
                    op_Assembler += "\tcall GreaterThan" + "\n";
                    op_Assembler += "\tMOV dx, ax" + "\n";

                    op_Assembler += "\tMOV ax, " + id2 + "\n";
                    op_Assembler += "\tXOR bx, bx" + "\n";
                    op_Assembler += "\tcall GreaterThan " + "\n";
                    op_Assembler += "\tAND ax, dx" + "\n";

                    op_Assembler += "\tPOP dx" + "\n";
                    op_Assembler += "\tPOP bx" + "\n";
                    break;
                case "|":
                    op_Assembler += "\tOR ax, " + id2 + "\n";
                    break;
                case "||":
                    // v3 = v1 == 1 | v2 == 1 ? 1 : 0;
                    op_Assembler += "\tPUSH bx" + "\n";
                    op_Assembler += "\tPUSH dx" + "\n";

                    op_Assembler += "\tXOR bx, bx" + "\n";
                    op_Assembler += "\tcall EqualThan" + "\n";
                    op_Assembler += "\tMOV dx, ax" + "\n";

                    op_Assembler += "\tMOV ax, " + id2 + "\n";
                    op_Assembler += "\tXOR bx, bx" + "\n";
                    op_Assembler += "\tcall EqualThan " + "\n";
                    op_Assembler += "\tOR ax, dx" + "\n";

                    op_Assembler += "\tPOP dx" + "\n";
                    op_Assembler += "\tPOP bx" + "\n";
                    break;
                case "^":
                    op_Assembler += "\tXOR ax, " + id2 + "\n";
                    break;
            }
            return op_Assembler;
        }
    }

}