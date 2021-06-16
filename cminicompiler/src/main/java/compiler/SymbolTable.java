package compiler;

import java.util.HashMap;

import java_cup.runtime.Symbol;
import model.Token;
import validation.NullSymbolException;
import validation.SymbolAlreadyDefinedException;

public class SymbolTable extends HashMap<String, Symbol> {

    public SymbolTable() {
        super();
    }

    public void registerSymbol(String identifier, Symbol symbol)
            throws NullSymbolException, SymbolAlreadyDefinedException {

        if (symbol == null)
            throw new NullSymbolException("Symbol value associated with " + identifier + " was received as null");

        if (this.containsKey(identifier))
            throw new SymbolAlreadyDefinedException("Symbol " + symbol.value + " already defined");

        this.put(identifier, symbol);
    }

    public boolean isRegistered(String identifier) {
        return this.containsKey(identifier);
    }

    public boolean isRepeated(Symbol symbol) {

        if (symbol == null || symbol.value == null)
            return false;

        Token token = (Token) symbol.value;

        if (token.getType() == "IDENTIFIER")
            return false;

        return this.values().stream().anyMatch(t -> token.getSymbol().equals(t));
    }

    public int getSymbolsAmount() {
        return this.size();
    }

    @Override
    public String toString() {

        StringBuilder strTable = new StringBuilder("#########################\n");
        strTable.append("Symbol Table\n");
        strTable.append("#########################\n");

        if (this.getSymbolsAmount() == 0) {
            strTable.append("There's no symbols to show");
            return strTable.toString();
        }

        for (String key : this.keySet())
            strTable.append(" | " + key + " | " + this.get(key) + " | ");

        return strTable.toString();
    }
}
