var express = require('express');
var router = express.Router();


let exoplanetsTable = [
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


  res.render('indexExoplanet', {
    exoplanetsTable: exoplanetsTable
  });
});

/* POST add exoplanet. */
router.post('/exoplanets/add', function (req, res, next) {

  console.log("POST ADD EXOPLANET");

  let uniqueNameExoplanetParam = req.body.uniqueNameExoplanet;
  let hClassExoplanetParam = req.body.hClassExoplanet;
  let discoveryYearExoplanetParam = req.body.discoveryYearExoplanet;
  exoplanetsTable.push({ uniqueName: uniqueNameExoplanetParam, hClass: hClassExoplanetParam, discoveryYear: discoveryYearExoplanetParam });

  res.redirect('/exoplanets');
});

/* GET search exoplanet. */
router.get('/exoplanets/search', function (req, res, next) {

  console.log("POST SEARCH EXOPLANET");

  let uniqueNameExoplanetParam = req.query.uniqueNameExoplanet;

  let exoplanetFound = null;
  for (let i = 0; i < exoplanetsTable.length; i++) {
    if (uniqueNameExoplanetParam === exoplanetsTable[i].uniqueName) {
      console.log("trouvé");
      exoplanetFound = exoplanetsTable[i];
    }
  }

  res.render('indexExoplanet', { exoplanetsTable: exoplanetsTable, exoplanet: exoplanetFound });

});






module.exports = router;
