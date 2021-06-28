printENTER MACRO
    push ax
    push dx

    mov dl, 10
    mov ah, 02h
    int 21h
    mov dl, 13
    int 21h

    pop dx
    pop ax
ENDM

datos segment

  msgAcercaDe   db 20h," ========================================= ",10,13
                     db 20h,"| Acerca De:         Compilador TEC Compi |",10,13
                     db 20h,"| Intstituto Tecnologico de Costa Rica    |",10,13
                     db 20h,"| Compilador de sub lenguaje de C:        |",10,13
					 db 20h,"|                                         |",10,13
                     db 20h,"| Este compilador ha sido desarrollado    |",10,13
                     db 20h,"| por Josue ARV & Andrew JGC              |",10,13
                     db 20h,"| Soporta estructuras de control como el  |",10,13
                     db 20h,"| while, if, if-else                      |",10,13
                     db 20h,"|                                         |",10,13
                     db 20h,"|                                         |",10,13
                     db 20h,"| Semestre I                         2020 |",10,13
                     db 20h," ========================================= $" 
  x dw 0
  y dw 0
  a dw 0
  b dw 0
  c dw 0
  ;int x;
  ;int y;
  ;int a, b,c;
  
datos endS

pila segment stack 'stack'
   dw 512 dup(?)
pila endS

codigo segment
	assume cs:codigo,ds:datos,ss:pila

printAX proc
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

;main
main: MOV ax, pila
	MOV ss, ax
	MOV ax, datos
	MOV ds, ax

	MOV ah, 09h
	lea dx, msgAcercaDe 
	int 21h
	printENTER
	

	;if (x > 4) { 
	;	x= x - 2;
	;}
	;else
	;{
	; x--;
	;}
	
	if1:
	CMP x, 4 
	JA if1Cuerpo
	JMP ifelse1
		if1Cuerpo:
			MOV ax, x
			SUB ax, 2
			MOV x, ax
			JMP finalif1	
	ifelse1:
		dec x
	finalif1:

	MOV ax, x
	call printAX
	printENTER

	;while ( x != 10) {
	;  y= x + 1;
	;  x++; 
	;}

	while1:
	CMP x, 10
	JNE while1cuerpo
	JMP finalWhile
	while1cuerpo:

		MOV ax, x 
		ADD ax, 1
		MOV y, ax
		inc x
		JMP while1
	finalWhile:

	mov ax, 4C00h
	int 21h 

codigo ends

end main