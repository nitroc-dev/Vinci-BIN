
const express = require('express');
const app = express();
const port = 3000;

app.get('/', (req, res) => {
  const vegetables = ["Carrot", "onion", "salad"];

  for (let vege of vegetables) {
    console.log(vege);
  }

  // Autre solution
  /*for (let i = 0; i < vegetables.length; i++) {
    console.log(vegetables[i]);
  }*/

  res.send("FINI");
});

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});

