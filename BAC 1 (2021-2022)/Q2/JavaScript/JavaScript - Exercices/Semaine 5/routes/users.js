var express = require('express');
var router = express.Router();
const bcrypt = require('bcrypt');
const saltRounds = 10;

const User = require('../models/User.js');

router.get('/login', function(req, res, next) { 
    res.render('users/login');
});

router.get('/register', function(req, res, next) {
    res.render('users/register', {
        message: req.session.message
    });
});

router.post('/add', function(req, res, next) {
    if (!User.contains(req.body.email)) {
        if (req.body.password1 === req.body.password2)  {
            const passwordEncrypted = bcrypt.hashSync(req.body.password1, saltRounds);
            User.add(req.body.nom, req.body.prenom, req.body.email, passwordEncrypted);
            res.redirect('/users/login');
        } else {
            req.session.message = 'Les deux mots de passe entré ne sont pas les memes.'
            res.redirect('/users/register');
        }
    } else {
        req.session.message = "L'email entré est deja dans la base de données."
    }
});

router.post('/login', function(req, res, next) {
    if (bcrypt.compareSync(req.body.password, User.getPasswordByEmail(req.body.email))) {
        req.session.login = req.body.email;
        res.redirect('/members');
    } else {
        res.redirect('/users/login');
    }
});

router.post('/logout', function(req, res, next) {
    req.session.destroy();
    res.redirect('/users/login');
});

module.exports = router;