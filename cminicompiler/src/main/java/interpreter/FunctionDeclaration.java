package interpreter;

import java.util.LinkedList;

import iexpressions.IGramaticInstruction;

public class FunctionDeclaration {

    LinkedList<ParamDefinition> params;
    LinkedList<IGramaticInstruction> body;

    public FunctionDeclaration(LinkedList<ParamDefinition> params, LinkedList<IGramaticInstruction> body) {
        this.params = params;
        this.body = body;
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
        return "FunctionDeclaration: body= " + body + ", params= " + params;
    }

}