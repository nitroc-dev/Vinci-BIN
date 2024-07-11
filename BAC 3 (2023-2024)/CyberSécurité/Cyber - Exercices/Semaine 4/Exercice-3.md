# Exercice 3

### Avec votre compte SSH actuel, certaines informations très intéressantes pour continuer votre attaque (clés d’accès, mots de passes, …) peuvent être retrouvés sur la session de l’utilisateur.
### Relisez la slide « Quelques pistes intéressantes » pour obtenir des idées pratiques de pistes exploitables. 3 flags sont cachés sous le format {FLAG_}

Aller voir dans le dossier .ssh => Trouver une clé 
```bash
cd .ssh
```

Trouver les clés avec gpg
```bash	
gpg --list-secret-keys --keyid-format LONG
```

Voir les variables d'environnement
```bash
env
```

Aller voir dans le bash_history
```bash
cat .bash_history
```
