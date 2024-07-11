
const express = require('express');
const app = express();
const port = 3000;



const TVA = (value, country) => {
  if (country === "BE") return value * 1.21;
  if (country === "FR") return value * 1.20;
}

app.get('/', (req, res) => {
  const result = TVA(100, "BE");
  console.log(result);
  if (result !== null)
    res.send("TVA pour la valeur 100 : " + result);
  else
    res.send("Aucun résultat");
});

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});