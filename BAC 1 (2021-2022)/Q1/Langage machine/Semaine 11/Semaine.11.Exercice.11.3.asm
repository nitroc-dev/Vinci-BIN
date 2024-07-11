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

            
            mov     bl,0
                      
boucle1:
            
            inc bl
            
            mov     al,1
            mov     dl,bl
            mov     ecx,10
boucle2:
            call    afficheTableDaddition
            jnz     boucle2
            
            NEWLINE
            
            cmp     bl,10
            jne     boucle1
            
fin:    
                    
; fin correcte de CMAIN dans l'IDE SASM
            xor     eax,eax
            ret
                     
; proc�dure <afficheCharAl> qui affiche le contenu de al en tant que CHAR � l'�cran
afficheTableDaddition:
            
            
            call afficheCalcule
            inc al
            dec ecx
            
            ret
            
afficheCalcule:
            push eax ; sauvegarde de eax car al est utilis� dans la proc�dure
            push edx
            
            PRINT_UDEC 4,dl
            PRINT_STRING " / "
            PRINT_UDEC 4,al
            PRINT_STRING " = "
            
            div dl
            
            PRINT_UDEC 4,al
            PRINT_STRING " R = "
            PRINT_UDEC 4,ah
            
            NEWLINE
            
            pop edx
            pop eax  ; restauration de eax
            ret