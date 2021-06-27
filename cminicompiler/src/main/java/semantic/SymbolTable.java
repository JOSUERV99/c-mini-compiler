package semantic;

import java.util.HashMap;
import java.util.LinkedList;

import interpreter.AssignDefinition;
import interpreter.DeclaredAssignDefinition;
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

    public boolean isDefinedGlobal(Identificable id) {
        return this.globalVars.contains(id);
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
        // System.out.println("isGlobalDefined: 1 -> "+ident.getSymbolIdentifier());
        String _adSymbolIdentifier;
        if (ident instanceof DeclaredAssignDefinition) {
            DeclaredAssignDefinition _ad = (DeclaredAssignDefinition) ident;
            _adSymbolIdentifier = _ad.getSymbolIdentifier();

        } else if (ident instanceof AssignDefinition) {
            AssignDefinition _ad = (AssignDefinition) ident;
            _adSymbolIdentifier = _ad.getSymbolIdentifier();

        } else {
            return false;
        }
        // System.out.println("isGlobalDefined: 2 -> "+ident.getSymbolIdentifier());
        for (Identificable id : globalVars) {

            if (id instanceof AssignDefinition) {
                AssignDefinition ad = (AssignDefinition) id;
                // System.out.println("isGlobalDefined: 3 -> "+_ad.getSymbolIdentifier() + " ? =
                // "+ad.getSymbolIdentifier());
                if (ad.getSymbolIdentifier().equals(_adSymbolIdentifier)) {
                    return true;
                }

            } else {
                // System.out.println("isGlobalDefined: 3.5 -> "+id.getClass());
            }
        }
        // System.out.println("isGlobalDefined: 4 -> "+ident.getSymbolIdentifier()+"
        // list: "+globalVars);
        return false;
    }

    public Identificable getFromGlobal(Identificable ident) {
        if (!(ident instanceof DeclaredAssignDefinition)) {
            return null;
        }
        DeclaredAssignDefinition _ad = (DeclaredAssignDefinition) ident;

        for (Identificable id : globalVars) {

            if (id instanceof AssignDefinition) {
                AssignDefinition ad = (AssignDefinition) id;
                if (ad.getSymbolIdentifier().equals(_ad.getSymbolIdentifier())) {
                    return id;
                }
            }
        }
        return null;
    }

    public LinkedList<Identificable> getGlobalVars() {
        return globalVars;
    }

    public void setGlobalVars(LinkedList<Identificable> globalVars) {
        this.globalVars = globalVars;
    }

}
