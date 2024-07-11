const express = require('express');
const User = require('../models/User');
const router = express.Router();


/* form login / password */
router.get('/', (req, res, next) => {
  console.log("USERS INDEX");
  res.render('users/index');
});

/* check login and password */
router.post('/login', (req, res, next) => {
  console.log("USERS LOGIN");
  if (req.body.memberPassword === 'js') {
    // define a session variable
    req.session.login = req.body.memberLogin;
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
  User.save({
    name: req.body.memberName,
    firstname: req.body.memberFirstname,
    email: req.body.memberEmail,
    password: req.body.memberPassword
  });
  res.redirect('/users');
});



module.exports = router;
