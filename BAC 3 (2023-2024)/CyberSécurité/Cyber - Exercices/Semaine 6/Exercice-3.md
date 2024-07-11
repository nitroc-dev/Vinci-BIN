# Exercice 3

- Effectuez une recherche pour trouver un service de capture FTP
```bash
search ftp capture
use auxiliary/server/capture/ftp
```
- Renseignez les paramètres corrects pour le module
```bash
set SRVPORT 2121
```
- Lancer le serveur FTP sur le port 2121… et essayez une connexion avec la commande Linux "ftp" depuis un autre terminal
```bash
run
```
- Le format est "ftp ftp://username:secretpassword@localhost:2121"
