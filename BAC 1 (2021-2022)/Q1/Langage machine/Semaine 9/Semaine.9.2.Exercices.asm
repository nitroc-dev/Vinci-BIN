%INCLUDE    'io.inc'        ; procédures d'input/ouput clavier et écran, voir l'aide (F1) de l'IDE SASM 

SECTION     .bss            ; section pour déclarer des données non initialisées  

SECTION     .data           ; section pour déclarer des données initialisées
tableau     dd 1,2,3,4,5,6,7,8,9,10

SECTION     .text           ; section pour écrire du code en langage assembleur
                            ; cette section .text contiendra des instructions écrites en NASM 32 bits
GLOBAL      CMAIN           ; le libellé de début de la programmation dans l'IDE SASM doit être CMAIN avec une portée "publique"
    
CMAIN:                  
            mov     ebp,esp; pour debugging fonctionnel dans l'IDE SASM               

; le libellé CMAIN: est le point d'entrée de notre programme
; écrivez votre instructions en langage assembleur NASM 32 bits
; à partir de la ligne ci-dessous
            
            mov ecx,10
            mov ebx,tableau
            
afficheetempile:
            PUSH dword [ebx]
            PRINT_UDEC 4,[ebx]             
            PRINT_CHAR ' '
            add ebx,4
            dec ecx
            jnz afficheetempile

            NEWLINE

            mov ecx,10
            mov ebx,tableau 
                       
depileretaffiche:                        
            POP dword [ebx]                                    
            PRINT_UDEC 4,[ebx] 
            PRINT_CHAR ' '
            add ebx,4
            dec ecx
            jnz depileretaffiche         
            
fin:                        
; fin correcte de CMAIN dans l'IDE SASM
            xor     eax,eax
            ret