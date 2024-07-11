%include "io.inc"
section .bss
nombreEntrer resb 1
section .data
entry dw "Entrer un nombre : "
trop_petit dw "Trop petit, essaie encore !"
trop_grand dw "Trop grand, essaie encore !"
equal dw "Bravo, tu as trouve le nombre "
nbr_essais dw "Nombre d'essai(s) : "
section .text
global CMAIN
CMAIN:
    mov ebp, esp
    mov ebx,0
    
boucle:
    GET_UDEC 1,nombreEntrer
    mov eax,45
    mov ecx,nombreEntrer
    NEWLINE
    PRINT_STRING entry
    PRINT_UDEC 1,nombreEntrer
    inc ebx
    cmp [nombreEntrer],eax
    je cas2
    jb cas1
    ja cas3

cas1:
    NEWLINE
    PRINT_STRING trop_petit
    jmp boucle
    
cas2:
    NEWLINE
    PRINT_STRING equal
    jmp fin
    
cas3:
    NEWLINE
    PRINT_STRING trop_grand
    jmp boucle
    
fin:
    NEWLINE
    PRINT_STRING nbr_essais
    PRINT_UDEC 1,ebx
    
    ;write your code here
    xor eax, eax
    ret