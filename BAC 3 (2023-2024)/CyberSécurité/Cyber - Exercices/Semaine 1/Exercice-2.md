# Exercice 2

### Consignes
- Téléchargez le fichier fizzbuzz.c depuis moodle
- Compilez-le avec la commande :
```bash
gcc -g -o fizzbuzz fizzbuzz.c
```
- Compilez-le encore avec la commande :
```bash	
clang -g -o fizzbuzz2 fizzbuzz.c
```
- Observez les différences entre l’assembleur des deux exécutables

### Marche à suivre

- Compiler le fichier "fizzbuzz.c"
```bash
gcc -g -o fizzbuzz fizzbuzz.c
```
- Compiler le fichier "fizzbuzz.c" avec clang
```bash
clang -g -o fizzbuzz2 fizzbuzz.c
```
- Ouvrir le fichier "fizzbuzz" avec GDB
```bash
gdb fizzbuzz
```

### Résultat
Comparez les deux fichiers en les ouvrant avec GDB
```bash
gdb fizzbuzz
```
```bash
gdb fizzbuzz2
```