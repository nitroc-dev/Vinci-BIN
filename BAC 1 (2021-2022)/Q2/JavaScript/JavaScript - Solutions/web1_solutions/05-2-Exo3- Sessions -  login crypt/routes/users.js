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
  const memberPassword = req.body.memberPassword;

  /*  if (memberPassword === 'js') {
      // define a session variable
      req.session.login = memberLogin;
      res.redirect('/members');
  
    }*/

  // User in DB ? -> return the record of the user if found
  const userFound = User.find(memberLogin);
  console.log("User found" + JSON.stringify(userFound));
  if (bcrypt.compareSync(memberPassword, userFound.password)) {
    console.log("password correct");
    req.session.login = memberLogin;
    res.redirect('/members');
  }

  else {
    console.log("bad password or user");
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
  res.render('users/register', { errors: req.session.errors });
  req.session.errors = null;

});

router.post('/add', (req, res, next) => {
  console.log("USERS ADD");
  User.save({
    name: req.body.memberName,
    firstname: req.body.memberFirstname,
    email: req.body.memberEmail,
    password: bcrypt.hashSync(req.body.memberPassword, saltRounds)
  });
  res.redirect('/users');
});



module.exports = router;
