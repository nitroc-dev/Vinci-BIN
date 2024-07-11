# Exercice 4

### Consignes
- Téléchargez le fichier loop.c depuis moodle
- Compilez-le avec la commande :
```bash
gcc -g -o loop loop.c
```
- Trouvez un argument qui provoque une boucle infinie
- Essayez de comprendre avec GDB ce comportement

### Marche à suivre

- Compiler le fichier "loop.c"
```bash
gcc -g -o loop loop.c
```
- Ouvrir le fichier "loop" avec GDB
```bash
gdb loop
```
- Lancer le programme
```bash
run test
```
- Breakpoint func
```bash
break func
```
```bash	
Breakpoint 1 at 0x555555555138: file loop.c, line 6.
```
- Lancer le programme
```bash
run test
```
- Afficher les variables locales
```bash
info locals
```
```bash
i = 0
buf = "maine 1/Exercice"
```
- Lancer le programme avec un input de 29 caractères
```bash
run $(python -c 'print "A"*28')
```
- Desassembler func
```bash
disass func
```
```bash
Dump of assembler code for function func:
   0x0000555555555130 <+0>:     push   %rbp
   0x0000555555555131 <+1>:     mov    %rsp,%rbp
   0x0000555555555134 <+4>:     mov    %rdi,-0x8(%rbp)
=> 0x0000555555555138 <+8>:     movl   $0x0,-0xc(%rbp)
   0x000055555555513f <+15>:    cmpl   $0x10,-0xc(%rbp)
   0x0000555555555143 <+19>:    jg     0x55555555516a <func+58>
   0x0000555555555149 <+25>:    mov    -0x8(%rbp),%rax
   0x000055555555514d <+29>:    movslq -0xc(%rbp),%rcx
   0x0000555555555151 <+33>:    mov    (%rax,%rcx,1),%cl
   0x0000555555555154 <+36>:    movslq -0xc(%rbp),%rax
   0x0000555555555158 <+40>:    mov    %cl,-0x20(%rbp,%rax,1)
   0x000055555555515c <+44>:    mov    -0xc(%rbp),%eax
   0x000055555555515f <+47>:    add    $0x1,%eax
   0x0000555555555162 <+50>:    mov    %eax,-0xc(%rbp)
   0x0000555555555165 <+53>:    jmp    0x55555555513f <func+15>
   0x000055555555516a <+58>:    pop    %rbp
   0x000055555555516b <+59>:    ret
End of assembler dump.
```
- Breakpoint adresse de la conditionnelle
```bash
break *0x0000555555555143
```
```bash
Breakpoint 2 at 0x555555555143: file loop.c, line 6.
```
- Continuer l'execution
```bash
c
```
- Afficher les variables locales
```bash
info locals
```
```bash
i = 0
buf = "ts/Semaine 1/Exe"
```
- Afficher la mémoire à l'adresse de &buf
```bash
x/8xw buf
```
```bash
0x7fffffffdd60: 0x532f7374      0x69616d65      0x3120656e      0x6578452f
0x7fffffffdd70: 0x00000000      0x00000000      0x00000000      0x00000000
```
- Continuer l'execution
```bash
c
```
- Afficher les variables locales
```bash
info locals
```
```bash
i = 0
buf = "ts/Semaine 1/Exe"
```
- Afficher la mémoire à l'adresse de &buf
```bash
x/8xw buf
```
```bash
0x7fffffffdd60: 0x532f7374      0x69616d65      0x3120656e      0x6578452f
0x7fffffffdd70: 0x00000000      0x00000000      0x00000000      0x00000000
```

### Résultat
```bash
./loop $(python -c 'print "A"*28')
```
