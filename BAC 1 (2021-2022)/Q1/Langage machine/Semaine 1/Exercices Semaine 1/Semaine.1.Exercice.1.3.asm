%INCLUDE    "io.inc"        ; procédures d'input/ouput clavier et écran, voir l'aide (F1) de l'IDE SASM 

SECTION     .bss            ; section pour déclarer des données non initialisées

SECTION     .data           ; section pour déclarer des données initialisées

SECTION     .text           ; section pour écrire du code en langage assembleur
                            ; cette section .text contiendra des instructions écrites en NASM 32 bits
GLOBAL      CMAIN           ; le libellé de début de la programmation dans l'IDE SASM doit être CMAIN avec une portée "publique"
    
CMAIN:                  
            mov     ebp,esp; pour debugging fonctionnel dans l'IDE SASM               
            mov     eax,11001000b;
            mov     ebx,11111111b;
            mov     ecx,10b;
            mov     edx,10101010101010101010101010101010b;
            
; le libellé CMAIN: est le point d'entrée de notre programme
; écrivez votre instructions en langage assembleur NASM 32 bits
; à partir de la ligne ci-dessous
            
            
            
; fin correcte de CMAIN dans l'IDE SASM
            xor     eax,eax
            ret