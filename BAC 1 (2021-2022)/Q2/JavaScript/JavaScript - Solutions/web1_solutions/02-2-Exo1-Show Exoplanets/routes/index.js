const express = require('express');
const router = express.Router();

/* GET home page. */
router.get('/', function (req, res, next) {
  res.render('index', {
    exoplanetsTable: ['Trappist-1-d', 'Trappist-1-e', 'Trappist-1-f', 'Trappist-1-g']
  });
});

/* GET exoplanets index. */
router.get('/exoplanets', function (req, res, next) {
  const exoplanetTable = [
    { uniqueName: "TRAPPIST-1-d", hClass: "Mésoplanète", discoveryYear: 2016 },
    { uniqueName: "KOI-1686.01", hClass: "Mésoplanète", discoveryYear: 2011 },
    { uniqueName: "LHS 1723 b", hClass: "Mésoplanète", discoveryYear: 2017 },
  ];
  res.render('indexExoplanet', { exoplanetsTable });
});



module.exports = router;
