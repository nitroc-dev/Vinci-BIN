%include "io.inc"

section .text
global CMAIN
CMAIN:
    mov ebp, esp; for correct debugging
    mov ecx,26;
    mov ebx,'a'

boucle1:
    PRINT_CHAR ebx
    inc ebx
    
    loop boucle1;
    NEWLINE
    mov ebx,'z'
boucle2:
    PRINT_CHAR ebx
    cmp ebx,'a'
    je fin;
    dec ebx
    loop boucle2;
fin:
    ;write your code here
    xor eax, eax
    ret