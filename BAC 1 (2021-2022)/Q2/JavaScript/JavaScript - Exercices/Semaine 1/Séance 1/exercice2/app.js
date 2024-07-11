const express = require('express');
const app = express();
const port = 3000;

app.get('/', (req, res) => {
    let date = new Date().toLocaleDateString();
    let heure = new Date().toLocaleTimeString();
    res.send(`Nous sommes le ${date}. Il est ${heure} à Bruxelles.`);
});

app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`);
});