%INCLUDE    'io.inc'        ; proc�dures d'input/ouput clavier et �cran, voir l'aide (F1) de l'IDE SASM 

SECTION     .bss            ; section pour d�clarer des donn�es non initialis�es  

SECTION     .data           ; section pour d�clarer des donn�es initialis�es

SECTION     .text           ; section pour �crire du code en langage assembleur
                            ; cette section .text contiendra des instructions �crites en NASM 32 bits
GLOBAL      CMAIN           ; le libell� de d�but de la programmation dans l'IDE SASM doit �tre CMAIN avec une port�e "publique"      
            
CMAIN:                  
            mov     ebp,esp; pour debugging fonctionnel dans l'IDE SASM               
      
            mov     bl,0
                      
boucle1:
            
            inc bl
            
            mov     dl,1
            mov     al,bl
            mov     ecx,10
boucle2:
            call    afficheTableMultiplication
            jnz     boucle2
            
            NEWLINE
            
            cmp     bl,10
            jne     boucle1
            
fin:    
                    
; fin correcte de CMAIN dans l'IDE SASM
            xor     eax,eax
            ret
                     
; proc�dure <afficheCharAl> qui affiche le contenu de al en tant que CHAR � l'�cran
afficheTableMultiplication:

; On appelle la méthode pour faire le calcul
            call afficheCalcul
            inc dl
            dec ecx
            
            ret
            
afficheCalcul:
            push eax
            push edx
            
; On afffiche le calcul à l'utilisateur
            PRINT_UDEC 4,al
            PRINT_STRING " x "
            PRINT_UDEC 4,dl
            PRINT_STRING " = "

; On effectue le calcul
            mul dl
            
; On affiche le résultat du calcul à l'utilisateur
            PRINT_UDEC 4,al
            NEWLINE
            
; On retire les deux valeurs de la pile
            pop edx
            pop eax
            ret            