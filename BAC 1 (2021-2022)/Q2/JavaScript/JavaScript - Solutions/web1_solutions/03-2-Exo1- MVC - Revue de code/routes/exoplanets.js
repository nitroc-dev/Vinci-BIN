const express = require('express');
const router = express.Router();

const Exoplanet = require('../models/Exoplanet.js');



/* GET exoplanets index. */
router.get('/', (req, res, next) => {
  res.render('exoplanets/index', { exoplanetsTable: Exoplanet.list() });
});

/* POST add exoplanet. */
router.post('/add', (req, res, next) => {
  console.log("POST ADD EXOPLANET");
  Exoplanet.save({
    uniqueName: req.body.uniqueNameExoplanet,
    hClass: req.body.hClassExoplanet,
    discoveryYear: req.body.discoveryYearExoplanet
  });
  res.redirect('/exoplanets');
});

/* GET search exoplanet. */
router.get('/search', (req, res, next) => {
  console.log("GET SEARCH EXOPLANET");
  res.render('exoplanets/index', {
    exoplanetsTable: Exoplanet.list(),
    exoplanet: Exoplanet.search(req.query.uniqueNameExoplanet)
  });
});



module.exports = router;