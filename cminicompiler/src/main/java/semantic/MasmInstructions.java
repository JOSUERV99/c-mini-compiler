package semantic;

public interface MasmInstructions {

    public final String PUSH = "PUSH";
    public final String ADD  = "ADD";
    public final String INC  = "INC";
    public final String DEC  = "DEC";
    public final String MOV  = "MOV";

    public final String IDIV = "IDIV";
    public final String IMUL = "IMUL";
    public final String MUL  = "MUL";
    public final String SUB  = "SUB";
    public final String STC  = "STC";
    public final String SBB  = "SBB";

    public final String XOR  = "XOR";
    public final String AND  = "AND";
    public final String OR   = "OR";
    public final String NOT  = "NOT";
    public final String NEG  = "NEG";
    public final String DIV  = "DIV";

    public final String JC  = "JC";
    public final String JE  = "JE";
    public final String JZ  = "JZ";
    public final String JA  = "JA";
    public final String JNA = "JNA";
    public final String JNO = "JNO";
    public final String JMP = "JMP";
    public final String JNC = "JNC";
    public final String POP  = "POP";

    public final String CLC  = "CLC";
    public final String CBW  = "CBW";
    public final String CLD  = "CLD";
    public final String LEA  = "LEA";
    public final String LOOPNE  = "LOOPNE";
    public final String LOOPZ  = "LOOPZ";

    public final String CMP  = "CMP";
    public final String CMPS  = "CMPS";
    public final String CMPSB  = "CMPSB";
    public final String CMPSW  = "CMPSW";
    public final String CMPSD  = "CMPSD";
    public final String CLI = "CLI";

    public final String HLT  = "HLT";
    public final String INTO  = "INTO";
    public final String INT  = "INT";
    public final String CALL  = "CALL";
}