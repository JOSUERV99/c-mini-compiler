package utils;

import iexpressions.IExpression;
import iexpressions.LiteralDecimalExpression;
import iexpressions.LiteralExponentialExpression;
import iexpressions.LiteralHexExpression;

public class TypeUtils {

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isFloat(String value) {
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean areLiterals(IExpression exp1, IExpression exp2) {
        return exp1 instanceof LiteralDecimalExpression || exp1 instanceof LiteralExponentialExpression
                || exp1 instanceof LiteralHexExpression && exp2 instanceof LiteralDecimalExpression
                || exp2 instanceof LiteralExponentialExpression || exp2 instanceof LiteralHexExpression;
    }

}
