%INCLUDE    'io.inc'        ; proc�dures d'input/ouput clavier et �cran, voir l'aide (F1) de l'IDE SASM 

SECTION     .bss            ; section pour d�clarer des donn�es non initialis�es  

    mot         resb 100   

SECTION     .data           ; section pour d�clarer des donn�es initialis�es

SECTION     .text           ; section pour �crire du code en langage assembleur
                            ; cette section .text contiendra des instructions �crites en NASM 32 bits
GLOBAL      CMAIN           ; le libell� de d�but de la programmation dans l'IDE SASM doit �tre CMAIN avec une port�e "publique"      
            
CMAIN:                  
            mov     ebp,esp; pour debugging fonctionnel dans l'IDE SASM               

; le libell� CMAIN: est le point d'entr�e de notre programme
; �crivez votre instructions en langage assembleur NASM 32 bits
; � partir de la ligne ci-dessous

        call initialisation
        call division  
        
boucleMain: 
        
        call deuxiemeMoite
        call premiereMoite
        
        cmp cl,dl
        jne mauvaiseFin
        
        cmp bl,al
        jne boucleMain
        je bonneFin
     
mauvaiseFin:

        NEWLINE
        PRINT_STRING "votre mot n'est pas un palyndrome"
        jmp fin 
        
bonneFin: 

        NEWLINE 
        PRINT_STRING "votre mot est un palyndrome"
        jmp fin      
        
fin:

        xor     eax,eax
        ret
                     
; proc�dure <afficheCharAl> qui affiche le contenu de al en tant que CHAR � l'�cran

compteurletres:         ;methode qui compte le nombre de lettres
                        ;return dans ebx le nombre de lettres
        push eax
        mov ebx,0
            
        bouclecompteur:
     
            mov al,[mot+ebx]
            inc ebx
            
            cmp al, 0
            jne bouclecompteur
                
        dec ebx  
        pop eax
        ret ;return dans ebx le nombres de lettres
        
        
division:               ;methode qui divise le nombre de letres par deux
                        ;return dans eax la valeur du rest (ah) et de la division (al)

                        ;divise pour savoir si le nombre est pair et impair,
                        ;permet aussi je savoir combien de fois l'on doit comparrer les letress   
                        ;kayak est compaser de 5 lettres on obtien donc 2 et un r=1
                        ;le mot a comparer n'est donc pas kayak mais est composer de deux mot "ka" et "ak"

        push ebx
        call compteurletres
                
        mov eax,ebx
        mov bl,2
              
        div bl
        pop ebx
                ;return dans eax la valuer de la division
                ;valeur de la division al
        ret     ;rest ah
         
         
deuxiemeMoite:  ;methode qui permet d'obtenir la letre de "fin" de mot 
            
        
        push ecx
        push ebx
        mov ecx,ebx
        
        call compteurletres
        dec ebx
        sub ebx,ecx
        
        mov dl,[mot+ebx]
        
        pop ebx
        pop ecx
        ret     ;return une lettre dans edx
        
        
premiereMoite:  ;methode qui permet d'obtenir la letre de "debut" de mot 
        
        mov cl,[mot+ebx]
        inc ebx
        ret     ;return une lettre dans ecx
        
        
initialisation:     ;methode qui initialise les registre et qui ecrit les phrasses d'introduction

        mov eax,0
        mov ebx,0
        mov ecx,0
        mov edx,0

        PRINT_STRING    "entrez votre mot : "
        NEWLINE
        GET_STRING      mot,100
        
        PRINT_STRING    mot
        NEWLINE
        NEWLINE
                
        PRINT_STRING    "votre mot est :"
        NEWLINE
        PRINT_STRING    mot
        NEWLINE
        ret
