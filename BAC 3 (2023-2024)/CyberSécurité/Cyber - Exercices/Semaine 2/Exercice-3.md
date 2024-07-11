# Exercice 3

### Consignes
- Téléchargez le fichier password.c depuis moodle (ou reprenez-le depuis la semaine passée)
- Compilez-le avec les commandes :
```bash
gcc -g -fno-stack-protector -o password1 password.c
```
```bash	
gcc -g -fstack-protector -o password2 password.c
```
```bash	
gcc -g -o password3 password.c
```
- Comparez le code assembleur et le contenu des stacks frames de ces trois exécutables. Qu’est-ce qui change ?
- Essayez de provoquer un buffer overflow avec ces exécutables. Qu’est-ce qui se passe ?

### Marche à suivre

- Compiler le fichier "password.c"
```bash
gcc -g -fno-stack-protector -o password1 password.c
```
```bash
gcc -g -fstack-protector -o password2 password.c
```
```bash
gcc -g -o password3 password.c
```
- Ouvrir le fichier "password1" avec GDB
```bash
gdb password1
```
- Ouvrir le fichier "password2" avec GDB
```bash
gdb password2
```
- Ouvrir le fichier "password3" avec GDB
```bash
gdb password3
```

### Observations
- Avec le premier fichier, on peut modifier la valeur de rip
- Avec le deuxième fichier, on ne peut pas modifier la valeur de rip
- Avec le troisième fichier, on peut modifier la valeur de rip

### Explications
- Le premier fichier a été compilé avec l'option "-fno-stack-protector" qui désactive la protection du stack
- Le deuxième fichier a été compilé avec l'option "-fstack-protector" qui active la protection du stack
- Le troisième fichier a été compilé sans option de protection du stack