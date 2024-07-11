var express = require('express');
var router = express.Router();

const Exoplanet = require('../models/Exoplanet.js');
let exoplanete1

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('exoplanets', {
      exoplanetsList: Exoplanet.list(),
      exoplanete: exoplanete1
  });
});

router.post('/add', function(req, res, next) {
  Exoplanet.add({uniqueName:req.body.uniqueName, hClass:req.body.hClass, discoveryYear:req.body.discoveryYear});
  res.redirect('/exoplanets')
});

router.post('/search', function(req, res, next) {
  exoplanete1 = Exoplanet.search(req.body.uniqueName);
  res.redirect('/exoplanets')
});

module.exports = router;