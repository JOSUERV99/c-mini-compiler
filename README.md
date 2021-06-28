# C-MiniCompiler
A mini compiler with lexical, syntactical and semantic analyzer to compile a C subset using JFlex & Cup to generate TASM code.
As part of the Compilers TEC course

Example input:
```c
int x;
int y;
int a, b, c;

int main()
{
    x = 0;
    y = 1;

    while (x < 5)
    {
        x++;
        y = y * x;

        write(x);
        write(y);
    }

    write(x);
    write(y);
}
```

Output:
```asm
datos segment
x dw 0
y dw 0
c dw 0
b dw 0
a dw 0
datos endS
pila segment stack 'stack'
	 dw 512 dup(?)
pila endS
codigo segment
	assume cs:codigo,ds:datos,ss:pila
AboveEqualThan proc near
        CMP ax, bx
        JAE isAboveEqualThan
        XOR ax, ax
        JMP AboveEqualThanFinal
        isAboveEqualThan:
                MOV ax, 1
        AboveEqualThanFinal:
        ret
AboveEqualThan endP
BelowEqualThan proc near
        CMP ax, bx
        JBE isBelowEqualThan
        XOR ax, ax
        JMP BelowEqualThanFinal
        isBelowEqualThan:
                MOV ax, 1
        BelowEqualThanFinal:
        ret
BelowEqualThan endP
GreaterThan proc near
        CMP ax, bx
        JA isGreater
        XOR ax, ax
        JMP GreaterThanFinal
        isGreater:
                MOV ax, 1
        GreaterThanFinal:
        ret
GreaterThan endP
LessThan proc near
        CMP ax, bx
        JB isLess
        XOR ax, ax
        JMP LessThanFinal
        isLess:
                MOV ax, 1
        LessThanFinal:
        ret
LessThan endP
EqualThan proc near
        CMP ax, bx
        JE isEqualThan
        XOR ax, ax
        JMP EqualThanFinal
        isEqualThan:
                MOV ax, 1
        EqualThanFinal:
        ret
EqualThan endP
printAX proc near
; imprime a la salida est?ndar un n?mero que supone estar en el AX
; supone que es un n?mero positivo y natural en 16 bits.
; lo imprime en decimal.  
    
    push AX
    push BX
    push CX
    push DX

    xor cx, cx
    mov bx, 10
	ciclo1PAX: xor dx, dx
	    div bx
	    push dx
	    inc cx
	    cmp ax, 0
	    jne ciclo1PAX
	    mov ah, 02h
	ciclo2PAX: pop DX
	    add dl, 30h
	    int 21h
	    loop ciclo2PAX

    pop DX
    pop CX
    pop BX
    pop AX
    ret
printAX endP
printEnter proc near
    push ax
    push dx

    mov dl, 10
    mov ah, 02h
    int 21h
    mov dl, 13
    int 21h

    pop dx
    pop ax
    ret
printENTER endP
main: MOV ax, pila
	MOV ss, ax
	MOV ax, datos
	MOV ds, ax
;	x=0;
	MOV ax, 0
	MOV x, ax
;	y=1;
	MOV ax, 1
	MOV y, ax
;	while
	while1:
	MOV ax, x
	PUSH bx
	MOV bx, 5
	call LessThan 
	POP bx
	CMP ax, 1
	JE whileCuerpo1
	JMP finalWhile1
	whileCuerpo1:
;	x++;
	MOV ax, x
	inc ax
	MOV x, ax
;	y=y * x;
	MOV ax, y
	PUSH dx 
	MUL x
	POP dx 
	MOV y, ax
;	write(x);
	PUSH AX
	MOV ax, x
	call printAX
	call printEnter
	POP AX
;	write(y);
	PUSH AX
	MOV ax, y
	call printAX
	call printEnter
	POP AX
	JMP while1
	finalWhile1:
;	write(x);
	PUSH AX
	MOV ax, x
	call printAX
	call printEnter
	POP AX
;	write(y);
	PUSH AX
	MOV ax, y
	call printAX
	call printEnter
	POP AX
	mov ax, 4C00h
	int 21h
codigo ends
end main
```
DosBox example execution
![DosBox example program execution](https://github.com/JOSUERV99/C-MiniCompiler/blob/main/image.png?raw=true)

