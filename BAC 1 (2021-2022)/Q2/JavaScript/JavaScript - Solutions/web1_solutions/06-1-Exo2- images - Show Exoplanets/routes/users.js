const express = require('express');
const User = require('../models/User');
const router = express.Router();
const bcrypt = require('bcrypt');
const saltRounds = 10;


/* form login / password */
router.get('/', (req, res, next) => {
  console.log("USERS INDEX");
  res.render('users/index');
});

/* check login and password */
router.post('/login', (req, res, next) => {
  console.log("USERS LOGIN");
  const memberLogin = req.body.memberLogin;
  // User in DB ? -> return the record of the user if found
  const userFound = User.find(memberLogin);
  console.log("User found" + JSON.stringify(userFound));
  if (bcrypt.compareSync(req.body.memberPassword, userFound.password)) {
    console.log("password correct");
    req.session.login = memberLogin;
    res.redirect('/members');
  } else {
    console.log("bad password");
    res.redirect('/users');
  }
});

/* check login and password */
router.post('/logout', (req, res, next) => {
  console.log("USERS LOGOUT");
  req.session.destroy();
  res.redirect('/users');
});


router.get('/register', (req, res, next) => {
  console.log("USERS REGISTER");
  res.render('users/register');

});

router.post('/add', (req, res, next) => {
  console.log("USERS ADD");

  //other verifications
  const email = req.body.memberEmail;
  const password = req.body.memberPassword;
  const confirmpassword = req.body.memberPasswordConfirmation;
  otherErrors = [];
  if (password !== confirmpassword) {
    console.log("password !== confirmpassword");
    otherErrors.push({ msg: "Le mot de passe et le mot de passe de confirmation sont différents !" });
  }
  if (User.find(email)) {
    console.log("user email already exist in DB");
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
