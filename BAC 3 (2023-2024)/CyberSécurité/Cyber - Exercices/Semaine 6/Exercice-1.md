# Exercice 1

- Démarrez Metasploit depuis Kali
```bash
msfconsole
```
- Créez un workspace et inventaire avec l’IP X.X.X.X
```bash
workspace -a workspace1
set RHOSTS X.X.X.X
```
- Effectuez une recon active avec un module HTTP approprié pour trouver des fichiers robots.txt
```bash
search robots.txt
use auxiliary/scanner/http/robots_txt
run
back
```
- Effectuez une deuxième recon HTTP pour trouver la version HTTP du serveur web
```bash	
search http_version
use auxiliary/scanner/http/http_version
run
back
```
- Effectuez une recon active de version avec un module SSH approprié
```bash
search ssh_version
use auxiliary/scanner/ssh/ssh_version
run
back
```
- Effectuez une recon active un module FTP approprié pour trouver un accès "anonymous"
```bash
search ftp_login
use auxiliary/scanner/ftp/anonymous
run
back
```