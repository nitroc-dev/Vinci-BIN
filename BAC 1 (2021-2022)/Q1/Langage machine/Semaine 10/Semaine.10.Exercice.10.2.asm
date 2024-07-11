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

            mov     al,'a'
            mov     ecx,26            

boucle:
            ; appel de la proc�dure <afficheCharAl>
            call afficheCharAl
            inc al
            dec ecx
            jnz boucle

fin:                        
; fin correcte de CMAIN dans l'IDE SASM
            xor     eax,eax
            ret
                     
; proc�dure <afficheCharAl> qui affiche le contenu de al en tant que CHAR � l'�cran
afficheCharAl:
            push eax ; sauvegarde de eax car al est utilis� dans la proc�dure
            
            PRINT_CHAR al
            
            pop eax  ; restauration de eax
            ret            