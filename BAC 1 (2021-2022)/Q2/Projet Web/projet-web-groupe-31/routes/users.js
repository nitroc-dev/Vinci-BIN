const express = require('express');
const router = express.Router();

const User = require('../models/user');

const bcrypt = require('bcrypt');
const saltRounds = 10;

// Route to display the login page
router.get('/', (req, res, next) => {
    res.render('users/index', {error: req.session.error});
    req.session.error = null;
});

// Route to display the register page
router.get('/register', (req, res, next) => {
    res.render('users/register', {error: req.session.error});
    req.session.error = null;
});

// Route to register a new user
router.post('/register', (req, res, next) => {
    const password = req.body.password;
    const passwordbiss = req.body.passwordbiss;

    if (password !== passwordbiss) {
        req.session.error = "The passwords are different";
        return res.redirect('/users/register');
    }

    const email = req.body.email
    const fisrtname = req.body.firstname.toLocaleLowerCase()
    const lastname = req.body.lastname.toLocaleLowerCase()

    const string1 = fisrtname + "." + lastname + "@student.vinci.be"
    const string2 = fisrtname + "." + lastname + "@vinci.be"

    if (!email.includes("@student.vinci.be") && !email.includes("@vinci.be")) {
        req.session.error = "L'email n'est pas de chez vinci";
        return res.redirect('/users/register');
    }

    if (string1 !== email) {
        if (string2 !== email) {
            req.session.error = "L'email est incorrect";
            return res.redirect('/users/register');
        }
    }

    const emaildb = User.getUserByEmail(email)

    if (emaildb) {
        req.session.error = "L'email est deja atribuer"
        return res.redirect('/users/register')
    } else {
        const encryptedpassword = bcrypt.hashSync(req.body.password, saltRounds);
        let newMember = {
            lastname: req.body.lastname,
            firstname: req.body.firstname,
            email: req.body.email,
            password: encryptedpassword
        }
        User.add(newMember);
        req.session.login = req.body.email;
        return res.redirect('/');
    }

});

// Route to login a user
router.post('/login', (req, res, next) => {
    const cryptedPw = User.getUserByEmail(req.body.email);

    if (cryptedPw === undefined) {
        req.session.error = "L'email n'est pas attribuée";
        return res.redirect('/users');
    }
    if (bcrypt.compareSync(req.body.password, cryptedPw.password)) {
        req.session.login = req.body.email;
        return res.redirect('/');
    } else {
        req.session.error = "Le mot de passe est incorrect";
        return res.redirect('/users');
    }
});

// Route to logout a user
router.post('/logout', (req, res, next) => {
    if (req.session.login) {
        req.session.destroy();
        return res.redirect('/users');
    } else {
        return res.redirect('/users');
    }
});

module.exports = router;