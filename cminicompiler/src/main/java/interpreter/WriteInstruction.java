package interpreter;

import model.IdentifierToken;

public class WriteInstruction implements ISemanticRegister {

    private IdentifierToken identifier;

    public WriteInstruction(IdentifierToken identifier) {
        this.identifier = identifier;
    }

    public IdentifierToken getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IdentifierToken identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "write(" + this.getIdentifier().getValue() + ");";
    }

    @Override
    public String getCode() {
        String code = "";
        code += "\tPUSH AX\n";
        code += "\tMOV ax, " + identifier.getValue() + "\n";
        code += "\tcall printAX\n";
        code += "\tcall printEnter\n";
        code += "\tPOP AX\n";
        return code;
    }

}
