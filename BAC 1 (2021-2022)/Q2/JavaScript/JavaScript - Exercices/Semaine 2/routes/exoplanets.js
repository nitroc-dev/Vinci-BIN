var express = require('express');
var router = express.Router();

let exoplanetsList1 = [{uniqueName:"TRAPPIST-1-d", hClass:"Mésoplanète", discoveryYear:2016}, {uniqueName:"KO1-1686-01", hClass:"Mésoplanète", discoveryYear:2011}, {uniqueName:"LHS-1723-b", hClass:"Mésoplanète", discoveryYear:2017}]
let exoplanete1

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('exoplanets', {
      exoplanetsList: exoplanetsList1,
      exoplanete: exoplanete1
  });
});

router.post('/add', function(req, res, next) {
  exoplanetsList1[exoplanetsList1.length] = {uniqueName:req.body.uniqueName, hClass:req.body.hClass, discoveryYear:req.body.discoveryYear}
  res.redirect('/exoplanets')
});

router.post('/search', function(req, res, next) {
  let index = 0;
  exoplanetsList1.forEach(element => {
    if (element.uniqueName === req.body.uniqueName) {
      exoplanete1 = exoplanetsList1[index];
    } else {
      exoplanete1 = "Non-Trouvée"
    }
    index++;
  });
  exoplanetsList = exoplanetsList1;
  res.redirect('/exoplanets')
});

module.exports = router;