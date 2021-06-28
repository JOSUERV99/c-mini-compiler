package code;

public enum AssemblerStatic {

    AcercaDe("msgAcercaDe     db 43,\" ========================================= \",10,13\n"
            + "db 32,\"| Acerca De:         Compilador TEC Compi |\",10,13\n"
            + "db 32,\"| Instituto Tecnologico de Costa Rica     |\",10,13\n"
            + "db 32,\"| Compilador de sub lenguaje de C:        |\",10,13\n"
            + "db 32,\"|                                         |\",10,13\n"
            + "db 32,\"| Este compilador ha sido desarrollado    |\",10,13\n"
            + "db 32,\"| por Josue ARV & Andrew JGC              |\",10,13\n"
            + "db 32,\"| Soporta estructuras de control como el  |\",10,13\n"
            + "db 32,\"| while, if, if-else                      |\",10,13\n"
            + "db 32,\"|                                         |\",10,13\n"
            + "db 32,\"|                                         |\",10,13\n"
            + "db 32,\"| Semestre I                         2020 |\",10,13\n"
            + "db 32,\" ========================================= $\" \n"),
    DefPilaSegment("pila segment stack 'stack'\n" + "\t dw 512 dup(?)\n" + "pila endS\n"),
    DefCodeSegment("codigo segment\n" + "\tassume cs:codigo,ds:datos,ss:pila\n"),
    DefMainCode("main: MOV ax, pila\n" + "\tMOV ss, ax\n" + "\tMOV ax, datos\n" + "\tMOV ds, ax\n"),
    DefFinalMainCode("\tmov ax, 4C00h\n" + "\tint 21h\n" + "codigo ends\n" + "end main\n");

    public String value;

    AssemblerStatic(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
