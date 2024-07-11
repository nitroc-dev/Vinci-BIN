var express = require('express');
var router = express.Router();

router.get('/', function(req, res, next) { 
    if (req.session.login) {
        res.render('members/index', {
            login: req.session.login
        });
    } else {
        res.redirect('/users/login');
    }
});

module.exports = router;