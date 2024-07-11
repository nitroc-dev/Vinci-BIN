# Exercice 4

### Consignes
- Téléchargez le fichier hackme.c depuis moodle
- Compilez-le avec la commande :
```bash
gcc -g -z execstack -o hackme hackme.c
```
- Exécutez avec gdb le shellcode contenu dans la variable shellcode

### Marche à suivre

- Compiler le fichier "hackme.c"
```bash
gcc -g -z execstack -o hackme hackme.c
```
- Ouvrir le fichier "hackme" avec GDB
```bash
gdb hackme
```
- Lancer le programme
```bash
run
```
- Disassembler hackhere
```bash
disass hackhere
```
```bash
Dump of assembler code for function hackhere:
   0x0000555555555139 <+0>:     push   %rbp
   0x000055555555513a <+1>:     mov    %rsp,%rbp
   0x000055555555513d <+4>:     sub    $0x50,%rsp
   0x0000555555555141 <+8>:     movabs $0xfcd0b05f535b27eb,%rax
   0x000055555555514b <+18>:    movabs $0x8a5e535957fd75ae,%rdx
   0x0000555555555155 <+28>:    mov    %rax,-0x50(%rbp)
   0x0000555555555159 <+32>:    mov    %rdx,-0x48(%rbp)
   0x000055555555515d <+36>:    movabs $0xff48c7ff48073006,%rax
   0x0000555555555167 <+46>:    movabs $0x7748b923f8166c6,%rdx
   0x0000555555555171 <+56>:    mov    %rax,-0x40(%rbp)
   0x0000555555555175 <+60>:    mov    %rdx,-0x38(%rbp)
   0x0000555555555179 <+64>:    movabs $0xffe6ebea75d03e80,%rax
   0x0000555555555183 <+74>:    movabs $0xd001ffffffd4e8e1,%rdx
   0x000055555555518d <+84>:    mov    %rax,-0x30(%rbp)
   0x0000555555555191 <+88>:    mov    %rdx,-0x28(%rbp)
   0x0000555555555195 <+92>:    movabs $0x722e6f68632eb949,%rax
   0x000055555555519f <+102>:   movabs $0x5f535e5551980169,%rdx
   0x00005555555551a9 <+112>:   mov    %rax,-0x20(%rbp)
   0x00005555555551ad <+116>:   mov    %rdx,-0x18(%rbp)
   0x00005555555551b1 <+120>:   movabs $0x8b92040e593a6b,%rax
   0x00005555555551bb <+130>:   mov    %rax,-0x10(%rbp)
   0x00005555555551bf <+134>:   lea    -0x50(%rbp),%rax
   0x00005555555551c3 <+138>:   mov    %rax,%rsi
   0x00005555555551c6 <+141>:   lea    0xe37(%rip),%rax        # 0x555555556004
   0x00005555555551cd <+148>:   mov    %rax,%rdi
   0x00005555555551d0 <+151>:   mov    $0x0,%eax
   0x00005555555551d5 <+156>:   call   0x555555555030 <printf@plt>
   0x00005555555551da <+161>:   nop
   0x00005555555551db <+162>:   leave
   0x00005555555551dc <+163>:   ret
End of assembler dump.
```
- Breakpoint à l'adresse de printf
```bash
break *0x00005555555551d5
```
- Lancer le programme
```bash
run
```
- Afficher les infos de la frame
```bash
info frame
```