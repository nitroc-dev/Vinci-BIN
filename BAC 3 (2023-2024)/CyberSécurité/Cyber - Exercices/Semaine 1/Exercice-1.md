# Exercice 1

### Consignes

- Télécharger le fichier "goodstudent.c" depuis moodle
- Compiler le fichier "goodstudent.c"
```bash
gcc -g -o goodstudent goodstudent.c
```
- Trouvez l'adresse de la conditionnelle à la ligne 8
- Modifiez la valeur de la variable pour y afficher le texte "Yes you are!"

### Marche à suivre

- Compiler le fichier "goodstudent.c"
```bash
gcc -g -o goodstudent goodstudent.c
```
- Ouvrir le fichier "goodstudent" avec GDB
```bash
gdb goodstudent
```
- Désassembler la fonction main
```bash
disass main
```
```bash
Dump of assembler code for function main:
   0x0000555555555139 <+0>:     push   %rbp
   0x000055555555513a <+1>:     mov    %rsp,%rbp
   0x000055555555513d <+4>:     sub    $0x10,%rsp
   0x0000555555555141 <+8>:     movl   $0x0,-0x4(%rbp)
   0x0000555555555148 <+15>:    lea    0xeb5(%rip),%rax        # 0x555555556004
   0x000055555555514f <+22>:    mov    %rax,%rdi
   0x0000555555555152 <+25>:    call   0x555555555030 <puts@plt>
   0x0000555555555157 <+30>:    cmpl   $0x1,-0x4(%rbp)
   0x000055555555515b <+34>:    jne    0x55555555516e <main+53>
   0x000055555555515d <+36>:    lea    0xeb8(%rip),%rax        # 0x55555555601c
   0x0000555555555164 <+43>:    mov    %rax,%rdi
   0x0000555555555167 <+46>:    call   0x555555555030 <puts@plt>
   0x000055555555516c <+51>:    jmp    0x55555555517d <main+68>
   0x000055555555516e <+53>:    lea    0xeb4(%rip),%rax        # 0x555555556029
   0x0000555555555175 <+60>:    mov    %rax,%rdi
   0x0000555555555178 <+63>:    call   0x555555555030 <puts@plt>
   0x000055555555517d <+68>:    mov    $0x0,%eax
   0x0000555555555182 <+73>:    leave
   0x0000555555555183 <+74>:    ret
End of assembler dump.
```
- Trouvez l'adresse de la conditionnelle à la ligne 8
```bash
0x0000555555555157 <+30>:    cmpl   $0x1,-0x4(%rbp)
0x000055555555515b <+34>:    jne    0x55555555516e <main+53>
```
- Modifiez la valeur de la variable pour y afficher le texte "Yes you are!"

1) Ajouter un breakpoint à la ligne 8
```bash
break 8
```
```bash
Breakpoint 1 at 0x555555555157: file goodstudent.c, line 8.
```
2) Lancer le programme
```bash
run
```
3) Modifier la valeur de la variable
```bash
set value = 1
```
4) Continuer l'exécution du programme
```bash
continue
```
### Résultat

```text
Yes you are!
```