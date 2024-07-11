const express = require("express");
const signin = require("../services/signin");
const validateCredentials = require("../services/validateCredentials");

const router = express.Router();

router.get("/validate", (req, res) => {
    const result = validateCredentials(req.query.username, req.query.password);
    res.send({
        result,
    });
});

router.get("/signin", (req, res) => {
    const result = signin(req.query.username, req.query.password);
    res.send({
        result,
    });
});

module.exports = router;
