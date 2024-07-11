# Exercice 1

### En naviguant sur le site web « vinci.be », obtenez quelques informations :
- L’adresse IP et des informations réseau concernant l'IP (personne de contact, ...)
- Le serveur web (logiciel) et sa version
- La présence potentielle d’un CMS et d’un répertoire d’upload

1) L’adresse IP et des informations réseau concernant l'IP (personne de contact, ...)

```bash
whois vinci.be
```

2) Le serveur web (logiciel) et sa version

```bash
curl -I vinci.be
```

3) La présence potentielle d’un CMS et d’un répertoire d’upload

Rechercher dans le code source de la page avec le raccourci clavier "Ctrl + Shift + I"

### En regardant les certificats

- Aller sur crt.sh et rechercher le domaine vinci.be

### Liens utiles

- https://crt.sh/
- https://www.shodan.io/
- https://www.exploit-db.com
- https://github.com/digininja/DVWA
- https://www.maltego.com/
- https://www.osinttechniques.com/
- https://wireshark.org/
- https://github.com/trufflesecurity/trufflehog
- https://github.com/x0rz/tweets_analyzer
- https://github.com/laramies/theHarvester
- https://github.com/kpcyrd/sn0int
- https://github.com/lanmaster53/recon-ng
