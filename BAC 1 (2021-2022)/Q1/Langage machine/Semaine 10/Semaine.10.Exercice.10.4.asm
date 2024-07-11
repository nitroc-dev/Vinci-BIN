%include "io.inc"

section .text
global CMAIN
CMAIN:
    mov ebp, esp; for correct debugging
    mov eax,1
    
; On fait appel à notre méthode permettant de faire l'addition
boucle:
    call TableAddition 
    inc eax
    NEWLINE
    cmp eax,6
    jne boucle
    xor eax, eax
    ret

; On ajoute nos deux opérantes à la pile
TableAddition:
    push ebx
    push ecx

; On initialise ebx à 1 pour les calculs
    mov ebx, 1
boucleproc:
    cmp ebx, 10
    je finproc ; si ebx est égal à dix, la boucle est finie et on va à la fin de la procédure

; On affiche le calcul pour l'utilisateur
    PRINT_DEC 4,eax
    PRINT_CHAR '+'
    PRINT_DEC 4,ebx
    PRINT_CHAR '='

; On effectue ce calcul en mémoire
    mov ecx, eax
    add ecx, ebx

; On affiche le résultat du calcul à l'utilisateur
    PRINT_DEC 4,ecx
    NEWLINE

; On ajoute 1 à ebx pour ensuite recommencer la boucle
    inc ebx
    jmp boucleproc

; Fin de la procédure
finproc:
    pop ecx
    pop ebx
    ret