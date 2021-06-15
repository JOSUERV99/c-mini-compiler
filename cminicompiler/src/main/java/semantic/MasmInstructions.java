package semantic;

public interface MasmInstructions {

//Aritmetthic instructions:
    public final String ADD  = "ADD";
    public final String SUB  = "SUB";
    public final String INC  = "INC";
    public final String DEC  = "DEC";
    public final String IDIV = "IDIV";
    public final String IMUL = "IMUL";
    public final String MUL  = "MUL";
    public final String DIV  = "DIV";

//Stack instructions
    public final String PUSH = "PUSH";
    public final String POP  = "POP";
    public final String PUSHA = "PUSHA";
    public final String PUSHD = "PUSHAD";
    public final String POPA = "POPA";
    public final String POPAD = "POPAD";

//shift instructions
    public final String RCL = "RCL";
    public final String RCR = "RCR";
    public final String ROL = "ROL";
    public final String ROR = "ROR";
    public final String SAL = "SAL";
    public final String SAR = "SAR";
    public final String SHL = "SHL";
    public final String SHR = "SHR";
 //Memory instructions
    public final String MOV  = "MOV";
    public final String LEA  = "LEA";
    public final String XCHG = "XCHG";

//Set and clear instructions
    public final String STC  = "STC";
    public final String CLC  = "CLC";
    public final String SBB  = "SBB";
    public final String CLI = "CLI";
    public final String CLD  = "CLD";

//Logical expressions
    public final String XOR  = "XOR";
    public final String AND  = "AND";
    public final String OR   = "OR";
    public final String NOT  = "NOT";
    public final String NEG  = "NEG";

//Jumps instructions
    public final String JMP = "JMP";
    public final String JC  = "JC";
    public final String JNC = "JNC";
    public final String JE  = "JE";
    public final String JNE = "JNE";
    public final String JZ  = "JZ";
    public final String JNZ = "JNZ";
    public final String JA  = "JA";
    public final String JNA = "JNA";
    public final String JO = "JO";
    public final String JNO = "JNO";
    public final String JS = "JS";
    public final String JNS = "JNS";
    public final String JP = "JP";
    public final String JNP = "JNP";
    public final String JLE = "JLE";
    public final String JNLE = "JNLE";
    public final String JG = "JG";
    public final String JNG = "JNG";
    public final String JL = "JL";
    public final String JNL = "JNL";
    public final String JGE = "JGE";
    public final String JNGE = "JNGE";
    public final String JCXZ = "JCXZ";
    public final String JECXZ = "JECXZ";
    public final String JB = "JB";
    public final String JNB = "JNB";
    public final String JAE = "JAE";
    public final String JNAE = "JNAE";
    public final String JBE = "JBE";
    public final String JNBE = "JNBE"; 

//Convert instructions
    public final String CBW  = "CBW";

//Loop instructions
    public final String LOOPE  = "LOOPE";
    public final String LOOPZ  = "LOOPZ";
    public final String LOOPNE  = "LOOPNE";
    public final String LOOPNZ  = "LOOPnZ";
//Compare instructions
    public final String CMP  = "CMP";
    public final String CMPS  = "CMPS";
    public final String CMPSB  = "CMPSB";
    public final String CMPSW  = "CMPSW";
    public final String CMPSD  = "CMPSD";

//Procedures instructions
    public final String CALL  = "CALL";
    public final String RET = "RET";

//Interruptions instructions
    public final String HLT  = "HLT";
    public final String INTO  = "INTO";
    public final String INT  = "INT";
}