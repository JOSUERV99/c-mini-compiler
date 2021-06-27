package semantic;

import java.util.HashMap;
import java.util.LinkedList;

import interpreter.AssignDefinition;
import interpreter.FunctionDefinition;
import interpreter.Identificable;

public class SymbolTable extends HashMap<String, Identificable> {

    private LinkedList<Identificable> globalVars;

    public SymbolTable() {
        super();
        this.globalVars = new LinkedList<>();
    }

    public boolean isDefined(Identificable id) {
        return this.containsKey(id.getSymbolIdentifier());
    }

    public boolean isRepeated(Identificable id) {

        if (id != null)
            return false;

        if ((!(id instanceof FunctionDefinition) || !(id instanceof AssignDefinition))) {
            return false;
        }

        if (this.globalVars.contains(id)) {
            return false;
        }

        return this.isDefined(id);
    }

    public void add(Identificable ident) {
        this.put(ident.getSymbolIdentifier(), ident);
    }

    public void addGlobal(Identificable ident) {
        this.globalVars.add(ident);
    }

    public boolean isGlobalDefined(Identificable ident) {

        if (!(ident instanceof AssignDefinition))
            return false;

        AssignDefinition _ad = (AssignDefinition) ident;

        for (Identificable id : globalVars) {

            if (id instanceof AssignDefinition) {
                AssignDefinition ad = (AssignDefinition) id;

                if (ad.getSymbolIdentifier().equals(_ad.getSymbolIdentifier()))
                    return true;
            }
        }
        return false;
    }

    public LinkedList<Identificable> getGlobalVars() {
        return globalVars;
    }

    public void setGlobalVars(LinkedList<Identificable> globalVars) {
        this.globalVars = globalVars;
    }

}
