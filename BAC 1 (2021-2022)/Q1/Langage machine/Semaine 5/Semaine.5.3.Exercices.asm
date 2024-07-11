%include "io.inc"

section .text
global CMAIN
CMAIN:
    mov ebp, esp; for correct debugging
    PRINT_STRING "Entre une premiere valeur hexadecimale :"
    GET_HEX 2,ax
    NEWLINE
    PRINT_STRING "Entre une deuxieme valeur hexadecimale :"
    NEWLINE
    GET_HEX 2,bx
    add ax,bx
    jz zero
    jc carry
    PRINT_STRING "La somme est de :"
    PRINT_HEX 2,ax
    jmp fin
    
zero:    
    jc carry_zero
    PRINT_STRING "La somme de ax et bx est egale a 0"
    jmp fin
    
carry:
    jz carry_zero
    PRINT_STRING "La somme de ax et bx a une taille supérieure a 2 octets"    
    jmp fin
carry_zero:
    PRINT_STRING "La somme de ax et bx a une taille superieure a 2 octets et est egale a 0"
fin:
    ;write your code here
    xor eax, eax
    ret