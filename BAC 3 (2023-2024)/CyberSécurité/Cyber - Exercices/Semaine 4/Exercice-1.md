# Exercice 1

### Votre point de départ est l’IP X.X.X.X – des accès SSH ont été trouvés lors d’une reconnaissance passive. Trouvez les informations et notez les commandes Linux utilisées lors de votre inventaire.
- Utilisateur actuel & groupes d’appartenance
```bash	
whoami
groups
```
- Interfaces réseau & « hostname »
```bash
ip a
hostname
```
- Services « systemd » qui tournent sur la machine
```bash
systemctl --type service
```
- Autres utilisateurs & groupes
```bash
cat /etc/passwd
cat /etc/group
```
- Variables d’environnement sur Linux
```bash
env
```
- Répertoire courant & liste des fichiers dans ce répertoire 
```bash
pwd
ls -la
```
- Distribution Linux & version du kernel
```bash
uname -a
```
- Liste des « packages » installés 
```bash
dpkg -l
apt list --installed
snap list
```
