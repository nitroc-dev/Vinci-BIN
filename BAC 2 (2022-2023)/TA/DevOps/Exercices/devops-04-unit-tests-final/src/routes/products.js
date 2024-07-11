const express = require("express");
const fetch = require("node-fetch");
const router = express.Router();

router.get("/products", async (req, res) => {
    const result = await fetch("https://dummyjson.com/products");
    const products = await result.json();
    res.send({
        products,
    });
});

router.get("/products/:id", async (req, res) => {
    const result = await fetch(
        "https://dummyjson.com/products/" + req.params.id
    );
    const products = await result.json();
    res.send({
        products,
    });
});

module.exports = router;
