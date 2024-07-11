%include "io.inc"

section .text
global CMAIN
CMAIN:
    mov ebp, esp; for correct debugging
    mov ecx,1;

boucle:
    PRINT_DEC 4,ecx
    cmp ecx,10
    je fin
    inc ecx
    
    jmp boucle;
fin:    
    ;write your code here
    xor eax, eax
²    ret