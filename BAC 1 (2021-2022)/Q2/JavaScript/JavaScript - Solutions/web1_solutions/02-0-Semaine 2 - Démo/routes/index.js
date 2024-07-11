const express = require('express');
const router = express.Router();

/* GET home page. */
router.get('/', function (req, res, next) {
  res.render('index', { title: 'Express' });
});

/* GET home page. */
router.post('/', function (req, res, next) {
  console.log(req.body.test);
  res.send("param reçu de : " + req.body.test);
  //res.render('index', { title: 'Express' });
});

module.exports = router;
