let express = require('express');
let router = express.Router();



const exoplanetsTable = [
  { uniqueName: "TRAPPIST-1-d", hClass: "Mésoplanète", discoveryYear: 2016 },
  { uniqueName: "KOI-1686.01", hClass: "Mésoplanète", discoveryYear: 2011 },
  { uniqueName: "LHS 1723 b", hClass: "Mésoplanète", discoveryYear: 2017 },
];

/* GET exoplanets index. */
router.get('/', (req, res, next) => {
  res.render('exoplanets/index', { exoplanetsTable });
});

/* POST add exoplanet. */
router.post('/add', (req, res, next) => {
  console.log("POST ADD EXOPLANET");
  exoplanetsTable.push({
    uniqueName: req.body.uniqueNameExoplanet,
    hClass: req.body.hClassExoplanet,
    discoveryYear: req.body.discoveryYearExoplanet
  });
  res.redirect('/exoplanets');
});

/* GET search exoplanet. */
router.get('/search', (req, res, next) => {
  console.log("GET SEARCH EXOPLANET");
  const uniqueNameExoplanetParam = req.query.uniqueNameExoplanet;
  let exoplanetFound = null;
  for (let exoplanet of exoplanetsTable) {
    if (exoplanet.uniqueName === uniqueNameExoplanetParam) {
      console.log("trouvé");
      exoplanetFound = exoplanet;
      // stop the loop !
      break;
    }
  }
  res.render('exoplanets/index', { exoplanetsTable: exoplanetsTable, exoplanet: exoplanetFound });
});



module.exports = router;