
const express = require('express');
const app = express();
const port = 3000;

// route "/"
app.get('/', (req, res) => {
  res.send("Pour accéder à index.html -> http://localhost:3000/index.html");
});

// route "/index.html"
app.get('/index.html', (req, res) => {
  res.sendFile(__dirname + "/index.html");
});

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});

