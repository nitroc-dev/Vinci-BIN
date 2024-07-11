%include "io.inc"
section .bss
mdp     resb 11
section .data
msg_incorrect dw 'mdp incorrect', 0 ;une chaine de caractère termine par 0
msg_correct dw 'mdp correct', 0 ;une chaine de caractère termine par 0
section .text

global CMAIN
CMAIN:
    mov ebp, esp; for correct debugging
    ;write your code here
    GET_STRING mdp, 11
    mov ecx, 0
    mov ebx, 0
boucle:
      cmp word [mdp+ecx],0
      je flag1
      inc ecx
      inc ebx
      jmp boucle
flag1: 
     PRINT_UDEC 4, ebx
     NEWLINE
     cmp ebx, 10
     ja fin
     je fin
     jb flag2
     
flag2:
    PRINT_STRING [msg_incorrect]
    NEWLINE
    GET_STRING mdp, 11
    jmp boucle

fin: 
    PRINT_STRING [msg_correct]
    xor eax, eax
    ret