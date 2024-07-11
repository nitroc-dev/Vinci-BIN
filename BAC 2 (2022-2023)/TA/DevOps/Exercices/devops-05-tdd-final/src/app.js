const express = require("express");
const app = express();
const inventionsRouter = require("./routes/inventions");
const loginRouter = require("./routes/signin");
const productsRouter = require("./routes/products");

app.get("/", (req, res) => {
    res.send("Hello World!");
});

app.use(inventionsRouter);
app.use(loginRouter);
app.use(productsRouter);

module.exports = app;
