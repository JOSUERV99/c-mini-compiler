package interpreter;

/**
 * declaracion de funciones llamados a funciones DONE identificador como
 * expresion asignaciones parametros de funciones variables
 */

public interface Identificable {
    public String getSymbolIdentifier();

    public String reportRepeated();
}
