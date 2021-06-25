package interpreter;

import java.util.LinkedList;

import iexpressions.IGramaticInstruction;
import itypes.ITypeToken;
import model.IdentifierToken;

public class FunctionDefinition {

    private ITypeToken type;
    private IdentifierToken identifier;
    private LinkedList<ParamDefinition> params;
    private LinkedList<IGramaticInstruction> body;

    public FunctionDefinition(ITypeToken type, IdentifierToken identifier, LinkedList<ParamDefinition> params,
            LinkedList<IGramaticInstruction> body) {
        this.type = type;
        this.identifier = identifier;
        this.params = params;
        this.body = body;
    }

    public ITypeToken getType() {
        return type;
    }

    public void setType(ITypeToken type) {
        this.type = type;
    }

    public IdentifierToken getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IdentifierToken identifier) {
        this.identifier = identifier;
    }

    public LinkedList<ParamDefinition> getParams() {
        return params;
    }

    public void setParams(LinkedList<ParamDefinition> params) {
        this.params = params;
    }

    public LinkedList<IGramaticInstruction> getBody() {
        return body;
    }

    public void setBody(LinkedList<IGramaticInstruction> body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "FunctionDefinition: body: " + body + ", identifier: " + identifier + ", params: " + params + ", type: "
                + type;
    }

}