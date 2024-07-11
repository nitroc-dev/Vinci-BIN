
const express = require('express');
const app = express();
const port = 3000;



const TVA = (value, country) => {
  if (country === "BE") return value * 1.21;
  if (country === "FR") return value * 1.20;
}

const TVA_JSON = (value, country) => {
  let result = null;
  let resultJSON = null;
  if (country === "BE") {
    result = value * 1.21;
    resultJSON = { value: value, valueWithTVA: result };
  }
  if (country === "FR") {
    result = value * 1.20;
    resultJSON = { value: value, valueWithTVA: result };
  }
  return resultJSON;
}



app.get('/', (req, res) => {
  const result = TVA_JSON(100, "BE");
  if (result !== null)
    res.send("TVA pour la valeur 100 : " + JSON.stringify(result));
  else
    res.send("Aucun résultat");
});

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});