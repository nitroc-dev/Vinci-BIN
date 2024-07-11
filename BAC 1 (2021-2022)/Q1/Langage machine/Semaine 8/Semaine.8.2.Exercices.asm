%include "io.inc"
section .bss
phrase resb 255
section .text
global CMAIN
CMAIN:
    mov ebp, esp
    
    PRINT_STRING "Entrer une phrase (max.254)"
    GET_STRING phrase,255
    mov eax,phrase

boucle:
    cmp byte [eax],0
    je fin
    cmp byte [eax], 'a'
    jb finboucle
    cmp byte [eax], 'z'
    ja finboucle
    
    and byte [eax],11011111b

finboucle:
    inc eax
    jmp boucle    

fin:
    NEWLINE
    PRINT_STRING phrase
    ;write your code here
    xor eax, eax
    ret