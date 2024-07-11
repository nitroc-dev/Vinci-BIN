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
    Exoplanet.add({unique_name:req.body.unique_name, hclass:req.body.hclass, discovery_year:req.body.discovery_year});
    res.redirect('/exoplanets');
});

router.post('/search', function(req, res, next) {
    exoplanete1 = Exoplanet.search(req.body.unique_name);
    res.redirect('/exoplanets');
});

router.post('/delete', function(req, res, next) {
    Exoplanet.delete(req.body.exoplanet_id);
    res.redirect('/exoplanets');
});

// Update
router.get('/update', function(req, res, next) {
    res.render('update', {
        exoplanet: Exoplanet.getPlanetById(req.query.exoplanet_id)
    });
});

router.post('/update', function(req, res, next) {
    res.redirect('/exoplanets')
    Exoplanet.update({
        unique_name: req.body.unique_name_update,
        hclass: req.body.hclass_update,
        discovery_year: req.body.discovery_year_update,
        exoplanet_id: req.body.exoplanet_id_update
    });
});

module.exports = router;