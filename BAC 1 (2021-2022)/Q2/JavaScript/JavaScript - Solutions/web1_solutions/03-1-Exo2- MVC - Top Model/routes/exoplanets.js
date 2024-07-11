const express = require('express');
const router = express.Router();



// TRANSFER in model Exoplanet
/*let exoplanetsTable = [ 
    { uniqueName : "TRAPPIST-1-d", hClass : "Mésoplanète", discoveryYear : 2016 },
    { uniqueName : "KOI-1686.01", hClass : "Mésoplanète", discoveryYear : 2011 },
    { uniqueName : "LHS 1723 b", hClass : "Mésoplanète", discoveryYear : 2017 },
  ];
*/

const Exoplanet = require('../models/Exoplanet.js');

/* GET exoplanets index. */
router.get('/', (req, res, next) => {
  res.render('exoplanets/index', {
    exoplanetsTable: Exoplanet.list()
  });
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
  let exoplanetFound = null;
  for (let exoplanet of exoplanetsTable) {
    if (exoplanet.uniqueName === req.query.uniqueNameExoplanet) {
      console.log("trouvé");
      exoplanetFound = exoplanet;
      // stop the loop !
      break;
    }
  }
  // On peut également faire ceci en une seule ligne
  // const exoplanetFound = uniqueName => exoplanetsTable.find(exoplanet => exoplanet.uniqueName === req.query.uniqueNameExoplanet) || null
  res.render('exoplanets/index', { exoplanetsTable: exoplanetsTable, exoplanet: exoplanetFound });
});



module.exports = router;