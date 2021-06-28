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
; imprime a la salida estándar un número que supone estar en el AX
; supone que es un número positivo y natural en 16 bits.
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