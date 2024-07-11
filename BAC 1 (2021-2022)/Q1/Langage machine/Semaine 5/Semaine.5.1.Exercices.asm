%include "io.inc"

section .text
global CMAIN
CMAIN:
    mov ebp, esp; for correct debugging
    PRINT_STRING "Entre une valeur en hexadécimal"
    GET_HEX 2,ax
    NEWLINE
    PRINT_STRING "Entre une deuxieme valeur hexadécimale"
    GET_HEX 2,bx
    sub ax,bx
    jz zero
    jc carry
    PRINT_HEX 2,ax
    jmp fin
    
zero:    
    PRINT_STRING "La somme de ax et bx est égale à 0"
    jmp fin
    
carry:
    PRINT_STRING "La somme de ax et bx a une taille supérieure à 2 octets"    

fin:
    ;write your code here
    xor eax, eax
    ret