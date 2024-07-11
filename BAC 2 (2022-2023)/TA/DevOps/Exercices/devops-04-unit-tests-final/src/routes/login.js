const express = require("express");
const login = require("../services/login");
const validateCredentials = require("../services/validateCredentials");

const router = express.Router();

router.get("/validate", (req, res) => {
    const result = validateCredentials(req.query.username, req.query.password);
    res.send({
        result,
    });
});

router.get("/login", (req, res) => {
    const result = login(req.query.username, req.query.password);
    res.send({
        result,
    });
});

module.exports = router;
