%INCLUDE    'io.inc'        ; proc�dures d'input/ouput clavier et �cran, voir l'aide (F1) de l'IDE SASM 
                           ; cette section .text contiendra des instructions �crites en NASM 32 bits
GLOBAL      CMAIN           ; le libell� de d�but de la programmation dans l'IDE SASM doit �tre CMAIN avec une port�e "publique"      
            
CMAIN:                  
            mov     ebp,esp; pour debugging fonctionnel dans l'IDE SASM               

            mov     al,'z'
            mov     ecx,26            

boucle:
            call afficheCharAl
            dec al
            dec ecx
            jnz boucle

fin:                        
            xor     eax,eax
            ret
                     
afficheCharAl:
            push eax

            PRINT_CHAR al

            pop eax
            ret