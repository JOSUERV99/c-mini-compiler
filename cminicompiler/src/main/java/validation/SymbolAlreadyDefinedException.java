package validation;

public class SymbolAlreadyDefinedException extends Exception {

    private static final long serialVersionUID = 1L;

    public SymbolAlreadyDefinedException(String message) {
        super(message);
    }
}