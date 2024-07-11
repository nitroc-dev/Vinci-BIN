const express = require("express");
const Funfacts = require("../models/Funfact");
const keyToUpperCase = require("../services/keyToUpperCase");

const router = express.Router();

const sources = ["https://www.mentalfloss.com/amazingfactgenerator"];

router.get("/funfacts", (req, res) => {
    const funFacts = keyToUpperCase(Funfacts.list(), "description");
    res.send({
        funFacts,
        sources,
    });
});

module.exports = router;
