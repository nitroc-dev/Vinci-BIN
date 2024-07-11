const express = require('express');
const app = express();
const port = 3000;

app.get('/', (req, res) => {
    let tva = calculerTVA(100,"BE");
    res.send(`${tva}`)
});

app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`);
});

const calculerTVA = (valeur, pays) => {
    switch (pays) {
        case "BE":
            return valeur + valeur*0.21;
        case "FR":
            return valeur + valeur*0.20;
        default:
            return "Aucun résultat";
    }
}