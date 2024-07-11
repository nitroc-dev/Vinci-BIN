%include "io.inc"
section .bss
nombre1 resb 1
nombre2 resb 1
operation resb 5
section .text
global CMAIN
CMAIN:
    mov ebp, esp
    PRINT_STRING "Entre un nombre :"
    GET_UDEC 1,nombre1
    PRINT_UDEC 1,nombre1
    NEWLINE
    PRINT_STRING "Entre un nombre :"
    GET_UDEC 1,nombre2
    PRINT_UDEC 1,nombre2
    NEWLINE
    PRINT_STRING "Entre une opération (+ ou -) :"
    GET_CHAR operation
    PRINT_CHAR operation

    ;write your code here
    xor eax, eax
    ret