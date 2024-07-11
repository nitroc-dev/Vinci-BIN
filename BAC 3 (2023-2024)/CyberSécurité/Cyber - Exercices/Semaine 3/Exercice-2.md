# Exercice 2

### Sur Google

- Un répertoire web (Index of dans le titre) avec le dossier ’ftp’ > Google dork
```bash
intitle:"Index of" ftp
```

- Un fichier de type « env » avec le mot clé DB_PASSWORD
```bash
filetype:env DB_PASSWORD
```

- Un dump SQL « database.sql.zip » dans un répertoire web
```bash
intitle:"Index of" "database.sql.zip"
```

### Sur Github

- Des clés privées RSA
```bash
site:github.com filetype:ppk
```

- Des fichiers de configuration FTP
```bash
site:github.com filetype:env
```

- Un webhook Slack (https://hooks.slack.com/services/T)
```bash
site:github.com "https://hooks.slack.com/services/T*"
```

### Sur Shodan

- Analysez l’IP de vinci.be – trouvez des nouvelles informations

Ouvrir shodan et rechercher l'ip de vinci.be

### Liens utiles

- https://gist.github.com/sundowndev/283efaddbcf896ab405488330d1bbc06
- https://dorkgenius.com/