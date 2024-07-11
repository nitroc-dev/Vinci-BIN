const express = require('express');
const app = express();
const port = 3000;

app.get('/', (req, res) => {
    let fruits = ["banane", "pomme"]
    let taille = fruits.length
    res.send(`${taille}`);
    fruits.forEach(fruit => {
        console.log(fruit);
    });
});

app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`);
});