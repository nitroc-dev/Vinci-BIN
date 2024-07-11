
const express = require('express');
const app = express();
const port = 3000;

app.get('/', (req, res) => {
  const today = new Date();
  /* Attention Date.now() est tentant mais renvoie une valeur numérique et non un objet Date
  donc impossible de faire ceci par exemple
  let now = Date.now(); 
  now.getFullYear()*/
  const todayString = "Nous sommes le " + today.getDate() + "/" + (today.getMonth() + 1) + "/" + today.getFullYear();
  const hourtodayString = "Il est " + today.getHours() + ":" + today.getMinutes() + " à Bruxelles";

  res.send(todayString + "--- " + hourtodayString);
});

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});

