package icontrolstructures;

import iexpressions.IGramaticInstruction;
import model.KeywordToken;

public class ContinueInstruction implements IGramaticInstruction {
    private KeywordToken token;

    public ContinueInstruction(KeywordToken token) {
        this.token = token;
    }

    public KeywordToken getToken() {
        return this.token;
    }

    public void setToken(KeywordToken token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Continue Instruction";
    }

}