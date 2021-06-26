package iexpressions;

import interpreter.IInstruction;

public interface IExpression extends IGramaticInstruction, IInstruction {

    @Override
    public abstract String toString();

}
