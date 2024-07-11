const express = require('express');
const router = express.Router();


const exoplanetsTable = [
  { uniqueName: "TRAPPIST-1-d", hClass: "Mésoplanète", discoveryYear: 2016 },
  { uniqueName: "KOI-1686.01", hClass: "Mésoplanète", discoveryYear: 2011 },
  { uniqueName: "LHS 1723 b", hClass: "Mésoplanète", discoveryYear: 2017 },
];

/* GET home page. */
router.get('/', function (req, res, next) {
  res.render('index', {
    exoplanetsTable: ['Trappist-1-d', 'Trappist-1-e', 'Trappist-1-f', 'Trappist-1-g']
  });
});

/* GET exoplanets index. */
router.get('/exoplanets', function (req, res, next) {
  res.render('indexExoplanet', { exoplanetsTable });
});

/* POST add exoplanet. */
router.post('/exoplanets/add', function (req, res, next) {
  console.log("POST ADD EXOPLANET");
  exoplanetsTable.push({
    uniqueName: req.body.uniqueNameExoplanet,
    hClass: req.body.hClassExoplanet,
    discoveryYear: req.body.discoveryYearExoplanet
  });
  res.redirect('/exoplanets');
});



module.exports = router;
