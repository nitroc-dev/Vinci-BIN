%include "io.inc"

section .text
global CMAIN
CMAIN:
    mov ebp, esp; for correct debugging
    PRINT_STRING "Entre ton age"
    GET_DEC 4,eax
    cmp eax,18
    NEWLINE
    ja majeur
    jb mineur
    
majeur:
    PRINT_STRING "Tu es majeur"
    PRINT_STRING "Ton age hexadecimal est :"
    NEWLINE
    PRINT_HEX 4,eax
    jmp fin
    
mineur:        
    PRINT_STRING "Tu es mineur"
    
fin:                    
    ;write your code here
    xor eax, eax
    ret