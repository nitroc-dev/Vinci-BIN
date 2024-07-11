# Beta

1) Chercher les sous domaines avec gobuster

```bash
gobuster dns -d rs.io -w hacker-toolkit/wordlists/common_subdomains.txt
```

2) Trouver le sous domaine `beta.rs.io`

3) Scan de port avec nmap sur le sous domaine

```bash
nmap -sV -p- beta.rs.io
```

4) On trouve le port 21 ouvert

5) On se connecte sur le port 21 avec un client ftp sur l'utilisateur `anonymous`

```bash
ftp beta.rs.io
```

5) On trouve un fichier `tests` qui est un programme en C

6) On récupère le fichier `tests`

```bash
get tests
```

7) On ouvre le fichier `tests` avec gdb

```bash
gdb tests
```

8) On regarde quels fonctions sont présentes dans le programme

```bash
info functions
```

9) On trouve la fonction `main` et on regarde son code assembleur

```bash
disassemble main
```

10) On voit que le programme exécute la fonction `no`

11) On regarde le code assembleur de la fonction `no`

```bash
disassemble no
```

12) On exécute le programme avec gdb

```bash
run
```

13) Cette fonction `no` ne nous donne pas le flag, donc on va essayer avec la fonction `test`, pour cela on va modifier le code assembleur de la fonction `main` pour qu'elle exécute la fonction `test` au lieu de la fonction `no`

14) On met un breakpoint sur la fonction `no`

```bash
break no
```

15) On modifie $rip pour qu'il pointe sur la fonction `test` avec l'adresse trouvé lors de l'éxécution de la commande `info functions`

```bash
set $rip = 0x000055555555513d
```

16) On exécute le programme

```bash
continue
```

17) On trouve le flag dans la sortie du programme

### Flag = ${FLAG_Reverso}