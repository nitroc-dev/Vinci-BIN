# Exercice 4

- Effectuez un scan nmap "ping-only" sans aucune interaction avec les ports
```bash
nmap -sn X.X.X.X
```

- Quel est le protocole réseau derrière "ping" ?
```bash
ICMP
```

- Effectuez un premier scan seulement sur les ports 80 et 443 en TCP
```bash
nmap -p 80,443 X.X.X.X
```

- Augmentez le périmètre du scan sur les 20 ports communément utilisés
```bash
nmap --top-ports 20 X.X.X.X
```

- Ensuite, effectuez un scan TCP connect. Quelle est la différence avec un scan SYN ?
```bash
Le scan SYN est plus rapide et plus discret
```

- Sur les 10 ports communément utilisés, détectez l'OS et les services
```bash
nmap -O -sV --top-ports 10 X.X.X.X
```

- Essayez d'abord avec une agressivité minimale
```bash
nmap -O -sV --top-ports 10 --osscan-limit X.X.X.X
```

- Passez ensuite à une agressivité maximale 
```bash
nmap -O -sV --top-ports 10 --osscan-guess X.X.X.X
```

### Liens utiles

- https://nmap.org
- https://nmap.org/book/man-nse.html