# Exercice 2

### Maintenant que l’inventaire basique est réalisé, à vous de voir comment tirer cela à votre avantage pour trouver des faiblesses dans le système. Notez chacune de vos réponses.
- Sur base des services « systemd », tentez de décrire les rôles du serveur
```bash	
systemctl --type=service
ss -tulpn
```
Le serveur est un serveur web, un serveur de base de données redis et mysql, tout cela tournant avec nginx.
Et un service wazuh qui est un service de détection de mauvaises configurations.
- Etes-vous un utilisateur avec des droits « sudo » ?
```bash
sudo -nv
```
Non, nous ne sommes pas un utilisateur avec des droits sudo.
- Quels utilisateurs possèdent les droits « sudo » sur le serveur ?
```bash
cat /etc/sudoers
```
L'utilisateur root possède les droits sudo.
- Ou pouvez-vous trouver des vulnérabilités connues sur le kernel Linux ?
Dans les CVE sur internet [Mitre](https://cve.mitre.org) en cherchant le kernel ou les services installés.