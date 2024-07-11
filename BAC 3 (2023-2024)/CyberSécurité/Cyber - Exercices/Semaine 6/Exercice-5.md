# Exercice 5

## Utilisation de udptunnel

- Lancez un simple « udptunnel » sur un terminal – qui appartient à l’attaquant
```bash
udptunnel -s 5000 <serveur relais>/6000
```

- Sur l'autre terminal, lancez un udptunnel avec les bonnes options
```bash
udptunnel -c <serveur relais>/5000 127.0.0.1/3000
```