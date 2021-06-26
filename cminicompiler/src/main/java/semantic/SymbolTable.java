package semantic;

import java.util.HashMap;

import interpreter.AssignDefinition;
import interpreter.FunctionDefinition;
import interpreter.Identificable;

public class SymbolTable extends HashMap<String, Identificable> {

    public SymbolTable() {
        super();
    }

    public boolean isDefined(Identificable id) {
        return this.containsKey(id.getSymbolIdentifier());
    }

    public boolean isRepeated(Identificable id) {
        return id != null && (!(id instanceof FunctionDefinition) || !(id instanceof AssignDefinition)) ? false
                : this.isDefined(id);
    }

    public void add(Identificable ident) {
        this.put(ident.getSymbolIdentifier(), ident);
    }

}
