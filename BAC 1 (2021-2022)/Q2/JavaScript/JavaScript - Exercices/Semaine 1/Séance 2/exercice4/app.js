const express = require('express');
const app = express();
const port = 3000;

app.get('/', (req, res) => {
    res.send(JSON.stringify(calculerTVA(100,"BE")))
});

app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`);
});

const calculerTVA = (valeur, pays) => {
    switch (pays) {
        case "BE":
            return {"valeur":valeur, "valeurAvecTVA":valeur+valeur*0.21};
        case "FR":
            return {"valeur":valeur, "valeurAvecTVA":valeur+valeur*0.21};
        default:
            return "Aucun résultat";
    }
}