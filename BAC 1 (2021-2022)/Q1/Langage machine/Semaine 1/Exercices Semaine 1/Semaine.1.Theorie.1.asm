%INCLUDE    "io.inc";

SECTION     .bss;
SECTION     .data;
SECTION     .text;
GLOBAL      CMAIN;
    
CMAIN:                  
            mov     ebp,esp; pour debugging
            mov     eax,0x4A;
            mov     ecx,eax;
            add     ecx,eax;       

            xor     eax,eax
            ret