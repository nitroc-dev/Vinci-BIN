/* Authentication Controller */
const express = require('express');
const router = express.Router();

/* form login / password */
router.get('/', (req, res, next) => {
  console.log("MEMBER INDEX");
  if (req.session.login) {
    res.render('members/index', { memberLogin: req.session.login });
  }
  else {
    res.redirect('/users');
  }
});

module.exports = router;