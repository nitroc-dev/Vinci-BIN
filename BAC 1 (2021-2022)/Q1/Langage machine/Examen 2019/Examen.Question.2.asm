%INCLUDE    'io.inc'        ; proc�dures d'input/ouput clavier et �cran, voir l'aide (F1) de l'IDE SASM 

SECTION     .bss            ; section pour d�clarer des donn�es non initialis�es  

SECTION     .data           ; section pour d�clarer des donn�es initialis�es

SECTION     .text           ; section pour �crire du code en langage assembleur
                            ; cette section .text contiendra des instructions �crites en NASM 32 bits
GLOBAL      CMAIN           ; le libell� de d�but de la programmation dans l'IDE SASM doit �tre CMAIN avec une port�e "publique"      
            
CMAIN:                  
            mov     ebp,esp; pour debugging fonctionnel dans l'IDE SASM               

; le libell� CMAIN: est le point d'entr�e de notre programme
; �crivez votre instructions en langage assembleur NASM 32 bits
; � partir de la ligne ci-dessous

            xor     edx,edx
            mov     ecx,19            

yoyo:
            inc     edx
            call    op
            dec     ecx
            jnz     yoyo

fin:                        
; fin correcte de CMAIN dans l'IDE SASM
            xor     eax,eax
            ret  
            
                    
op:
            PRINT_UDEC 4,edx
            ret                                    