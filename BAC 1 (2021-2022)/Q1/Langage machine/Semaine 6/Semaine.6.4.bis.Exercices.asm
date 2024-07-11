%include "io.inc"
section .bss
entry resb 11

section .text
mdp_incorrect db 'Mot de passe incorrect',0
mdp_correct db 'Mot de passe correct', 0
mdp db 'vinci2018', 0
global CMAIN
CMAIN:
    mov ebp, esp
    PRINT_STRING "Entre votre mot de passe (max. 1O)"
    GET_STRING entry,11
    mov ecx, 0
boucle:
    cmp byte[mdp + ecx], 0
    je fin
    mov al, [mdp + ecx]
    mov bl, [entry + ecx]
    cmp al, bl
    jne flag1
    inc ecx
    jmp boucle
flag1:
    PRINT_STRING [mdp_incorrect]
    NEWLINE
    GET_STRING entry, 11
    jmp boucle
fin: 
    NEWLINE
    PRINT_STRING [mdp_correct]
    xor eax, eax
    ret