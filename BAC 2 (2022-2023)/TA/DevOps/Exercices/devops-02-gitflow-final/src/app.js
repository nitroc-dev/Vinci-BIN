const express = require("express");
const app = express();
const inventionsRouter = require("./routes/inventions");
const port = 3000;

app.get("/", (req, res) => {
    res.send("Hello World!");
});

app.use(inventionsRouter);

app.listen(port, () => {
    console.log(`Example app listening on port ${port}!`);
});
