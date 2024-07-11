# Exercice 1

### Consignes
- Téléchargez le fichier goodstudent2.c depuis moodle
- Compilez-le avec la commande :
```bash
gcc -g -o goodstudent2 goodstudent2.c
```
- Trouvez un moyen d’exécuter la fonction yes dans gdb

### Marche à suivre

- Compiler le fichier "goodstudent2.c"
```bash
gcc -g -o goodstudent2 goodstudent2.c
```
- Ouvrir le fichier "goodstudent2" avec GDB
```bash
gdb goodstudent2
```
- Lancer le programme
```bash
run
```
- Disassembler main
```bash
disass main
```
```bash
Dump of assembler code for function main:
   0x0000555555555165 <+0>:     push   %rbp
   0x0000555555555166 <+1>:     mov    %rsp,%rbp
   0x0000555555555169 <+4>:     lea    0xeac(%rip),%rax        # 0x55555555601c
   0x0000555555555170 <+11>:    mov    %rax,%rdi
   0x0000555555555173 <+14>:    call   0x555555555030 <puts@plt>
   0x0000555555555178 <+19>:    mov    $0x0,%eax
   0x000055555555517d <+24>:    call   0x55555555514f <no>
   0x0000555555555182 <+29>:    mov    $0x0,%eax
   0x0000555555555187 <+34>:    pop    %rbp
   0x0000555555555188 <+35>:    ret
End of assembler dump.
```
- Breakpoint à l'adresse de la fonction no
```bash
break *0x000055555555517d
```
- Disassembler yes
```bash
disass yes
```
```bash
Dump of assembler code for function yes:
   0x0000555555555139 <+0>:     push   %rbp
   0x000055555555513a <+1>:     mov    %rsp,%rbp
   0x000055555555513d <+4>:     lea    0xec0(%rip),%rax        # 0x555555556004
   0x0000555555555144 <+11>:    mov    %rax,%rdi
   0x0000555555555147 <+14>:    call   0x555555555030 <puts@plt>
   0x000055555555514c <+19>:    nop
   0x000055555555514d <+20>:    pop    %rbp
   0x000055555555514e <+21>:    ret
End of assembler dump.
```
- Lancer le programme
```bash
run
```
- Modifier rip pour qu'il pointe vers l'adresse de yes
```bash
set $rip = 0x0000555555555139
```
- Continuer l'execution
```bash
c
```

### Résultat
```text
Yes you are!
```