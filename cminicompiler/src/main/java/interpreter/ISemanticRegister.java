package interpreter;

import iexpressions.IGramaticInstruction;

public interface ISemanticRegister extends IGramaticInstruction {

    public abstract String getCode();
}
