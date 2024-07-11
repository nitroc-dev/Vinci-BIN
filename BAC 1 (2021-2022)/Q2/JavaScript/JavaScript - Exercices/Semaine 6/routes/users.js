const express = require('express');
const User = require('../models/User');
const router = express.Router();
const bcrypt = require('bcrypt');
const saltRounds = 10;


/* form login / password */
router.get('/', (req, res, next) => {
  res.render('users/index');
});

/* check login and password */
router.post('/login', (req, res, next) => {
  const memberLogin = req.body.memberLogin;
  const userFound = User.find(memberLogin);
  if (bcrypt.compareSync(req.body.memberPassword, userFound.password)) {
    req.session.login = memberLogin;
    res.redirect('/members');
  } else {
    res.redirect('/users');
  }
});

/* check login and password */
router.post('/logout', (req, res, next) => {
  req.session.destroy();
  res.redirect('/users');
});


router.get('/register', (req, res, next) => {
  res.render('users/register');
});

router.post('/add', (req, res, next) => {
  const email = req.body.memberEmail;
  const password = req.body.memberPassword;
  const confirmpassword = req.body.memberPasswordConfirmation;
  otherErrors = [];
  if (password !== confirmpassword) {
    otherErrors.push({ msg: "Le mot de passe et le mot de passe de confirmation sont différents !" });
  }
  if (User.find(email)) {
    otherErrors.push({ msg: "Cet email existe déjà en DB ! " });
  }
  if (otherErrors) {
    res.render('users/register', { errors: otherErrors });
  }
  else {
    const passwordCrypted = bcrypt.hashSync(password, saltRounds);
    User.save({
      name: req.body.memberName,
      firstname: req.body.memberFirstname,
      email: req.body.memberEmail,
      password: passwordCrypted
    });
    res.redirect('/users');
  }
});

module.exports = router;
