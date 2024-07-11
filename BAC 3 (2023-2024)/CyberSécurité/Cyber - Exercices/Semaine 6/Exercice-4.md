# Exercice 4

## Via 2 terminaux sur votre poste de travail
- Lancez un simple « ptunnel » sur un terminal – qui appartient à l’attaquant
```bash
sudo ptunnel
```
- Sur le terminal « infecté », lancez un ptunnel avec les bonnes options, pour écouter sur le port 4000 de la machine infectée, pour faire passer le trafic réseau via la machine de l’attaquant, pour envoyer le trafic réseau vers le serveur vinci.be 
```bash
sudo ptunnel -p 127.0.0.1 -lp 4000 -da vinci.be -dp 80
```
- Lancez une capture réseau via Wireshark sur les deux machines
```bash
wireshark
```
- Effectuer une requête http://localhost:4000 et validez le résultat
```bash
curl http://localhost:4000
```