# Exercice 6

### Effectuez une mise à jour locale des scripts NMAP

```bash
nmap --script-updatedb
```

### Commencez par une attaque sur le service HTTP de X.X.X.X
- Chargez uniquement les scripts de la catégorie "discovery"
- Comparez les résultats avec un scan de ports classique
- Continuez votre recherche de scripts NSE et trouvez un script efficace  

```bash
nmap -sV --script=http-enum X.X.X.X
```
