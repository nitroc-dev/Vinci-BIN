# Alpha

1) Chercher les sous domaines avec gobuster

```bash
gobuster dns -d rs.io -w hacker-toolkit/wordlists/common_subdomains.txt
```

2) Trouver le sous domaine `alpha.rs.io`

3) Scan de port avec nmap sur le sous domaine

```bash
nmap -sV -p- alpha.rs.io
```

4) Trouver le port 6656 avec un serveur nginx qui tourne

5) On ouvre le site dans le navigateur et on arrive sur une page présentant des données personnelles (nom, prénom, nom_société) 
- Nom: John Ellerbee
- Société: Liberty Wealth Planners

6) On fait une recherche sur le nom de la personne, on trouve un compte linkedin d'une personne travaillant dans la société `Liberty Wealth Planners`

7) Ce compte linkedin contient un lien vers un compte github (https://github.com/DiamondHunter153)

8) On y trouve un repo contenant un fichier `alpha.crt` qui contient le flag

### Flag = ${FLAG_OSINTcertified}