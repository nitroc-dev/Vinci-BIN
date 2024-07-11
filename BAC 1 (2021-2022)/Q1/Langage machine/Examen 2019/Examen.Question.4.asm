    %INCLUDE    'io.inc'        ; proc�dures d'input/ouput clavier et �cran, voir l'aide (F1) de l'IDE SASM 

SECTION     .bss            ; section pour d�clarer des donn�es non initialis�es  
tableaux resd 40
phrase resb 255

SECTION     .data           ; section pour d�clarer des donn�es initialis�es

SECTION     .text           ; section pour �crire du code en langage assembleur
                                              ; cette section .text contiendra des instructions �crites en NASM 32 bits
GLOBAL      CMAIN           ; le libell� de d�but de la programmation dans l'IDE SASM doit �tre CMAIN avec une port�e "publique"      
            
CMAIN:                  
       mov     ebp,esp; pour debugging fonctionnel dans l'IDE SASM               

; le libell� CMAIN: est le point d'entr�e de notre programme
; �crivez votre instructions en langage assembleur NASM 32 bits
; � partir de la ligne ci-dessous

       mov eax,0
       mov ebx,0
        
       GET_STRING phrase,255
       PRINT_STRING phrase
       
boucleMain:

       mov cl,[phrase + eax]
       cmp cl,'o'
       je compteur
       
       inc eax
       
       cmp cl,0
       jne boucleMain
       je fin
  
compteur:

       inc ebx
       inc eax
       jmp boucleMain 
       
        
fin:
       NEWLINE
       PRINT_UDEC 2,ebx
; fin correcte de CMAIN dans l'IDE SASM
       xor     eax,eax
       ret  
            
                    
                             