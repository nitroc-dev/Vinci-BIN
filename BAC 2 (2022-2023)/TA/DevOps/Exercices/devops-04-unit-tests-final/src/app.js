const express = require("express");
const app = express();
const inventionsRouter = require("./routes/inventions");
const funFactsRouter = require("./routes/funFacts");
const loginRouter = require("./routes/login");
const productsRouter = require("./routes/products");

app.get("/", (req, res) => {
    res.send("Hello World!");
});

app.use(inventionsRouter);
app.use(funFactsRouter);
app.use(loginRouter);
app.use(productsRouter);

module.exports = app;
