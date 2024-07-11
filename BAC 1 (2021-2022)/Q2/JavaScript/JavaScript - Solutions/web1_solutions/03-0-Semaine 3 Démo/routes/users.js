var express = require('express');
var router = express.Router();

// déclenchement du middleware uniquement pour /users et la suite 
router.use(function (req, res, next) {
  console.log('Exo ExoPlanet Users ...')
  next();
});

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send('respond with a resource');
});

module.exports = router;
