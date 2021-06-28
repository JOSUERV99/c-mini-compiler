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
;	if
;	x > 4
	MOV ax, x
	PUSH bx
	MOV bx, 4
	call GreaterThan 
	POP bx
	CMP ax, 0
	JNE ifCuerpo1
	JMP ifelse1
		ifCuerpo1:
;	x=x - 2;
	MOV ax, x
	SUB ax, 2
	MOV x, ax
		JMP finalif1
	ifelse1:
;	x--;
	MOV ax, x
	dec ax
	MOV x, ax
;	y=x + 2;
	MOV ax, x
	ADD ax, 2
	MOV y, ax
	finalif1:
;	while
	while1:
	MOV ax, x
	PUSH bx
	MOV bx, 10
	call EqualThan 
	not ax
	POP bx
	CMP ax, 1
	JE whileCuerpo1
	JMP finalWhile1
	whileCuerpo1:
;	x++;
	MOV ax, x
	inc ax
	MOV x, ax
;	y=x + 1;
	MOV ax, x
	ADD ax, 1
	MOV y, ax
	JMP while1
	finalWhile1:
	mov ax, 4C00h
	int 21h
codigo ends
end main
