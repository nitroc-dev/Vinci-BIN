%INCLUDE    'io.inc'        ; procédures d'input/ouput clavier et écran, voir l'aide (F1) de l'IDE SASM 

SECTION     .bss        ; section pour déclarer des données non initialisées
pseudo      resb 10     ; 10 bytes réservés pour lire au clavier

SECTION     .data           ; section pour déclarer des données initialisées
message     db 'Entre ton pseudo (max. 9 car.) : ',0
bonjour     db 'Bonjour ',0
msgtaille   db 'Nombre de lettres de ton pseudo : ',0

SECTION     .text           ; section pour écrire du code en langage assembleur
                            ; cette section .text contiendra des instructions écrites en NASM 32 bits
GLOBAL      CMAIN           ; le libellé de début de la programmation dans l'IDE SASM doit être CMAIN avec une portée "publique"
    
CMAIN:                  
            mov     ebp,esp; pour debugging fonctionnel dans l'IDE SASM               

; le libellé CMAIN: est le point d'entrée de notre programme
; écrivez votre instructions en langage assembleur NASM 32 bits
; à partir de la ligne ci-dessous

            PRINT_STRING message
            GET_STRING pseudo,10 ; remplissage de la suite d'octets en mémoire en commençant à l'adresse pseudo 
                                  
            mov cl,0          ; pour compter la longueur du pseudo
            mov ebx,pseudo    ; l'adresse pseudo est copiée dans ebx    
                     
boucle:   
            cmp byte [ebx],10 ; fin de chaîne qd on entre 'Enter', il y a 10 en mémoire
            je  affichage10 
            cmp byte [ebx],0  ; fin de chaîne classique, il y a 0 en mémoire
            je  affichage0
            inc cl            ; longueur++ du pseudo
            inc ebx           ; adresse++ (on passe ainsi à l'octet suivant en mémoire)
            jmp boucle        ; retour au début de la boucle "while"                          

affichage10:
            mov byte [ebx],0  ; remplace le 10 par la fin de chaîne classique 0

affichage0:    
            PRINT_STRING bonjour
            PRINT_STRING pseudo
            NEWLINE       
            PRINT_STRING msgtaille
            PRINT_UDEC 1,cl
            
; fin correcte de CMAIN dans l'IDE SASM
            xor     eax,eax
            ret