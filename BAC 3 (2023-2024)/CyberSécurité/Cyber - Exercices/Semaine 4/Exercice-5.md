# Exercice 5

### Le serveur X.X.X.X possède un service MySQL qui peut contenir des données intéressantes - et vous permettra de déployer d’autres techniques d’élévation (utilisation de user modules, vulnérabilité applicative, etc.)
- Trouvez un accès au service MySQL (nom d’utilisateur & mot de passe)
- Prouvez la réussite de votre recherche par un SELECT sur la table d’utilisateurs

```bash
cd /var/www/restricted
cat admin.php
```
On trouve le mot de passe de l'utilisateur admin : `production:NeverGuessThis!`

```bash
mysql -u production -p
```
On fait un SELECT pour tous les utilisateurs mysql.
```sql
SHOW DATABASES;
```
On trouve la base de données `webapp`
```sql
USE webapp;
SHOW TABLES;
```
On trouve la table `users`
```sql
SELECT * FROM users;
```