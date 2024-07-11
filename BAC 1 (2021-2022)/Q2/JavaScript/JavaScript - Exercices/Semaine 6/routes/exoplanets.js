const express = require('express');
const router = express.Router();

const multer = require('multer');
const upload = multer({ dest: 'public/images/' })

const Exoplanet = require('../models/Exoplanet.js');
const fs = require('fs');


/* GET exoplanets index. */
router.get('/', (req, res, next) => {
  return res.render('exoplanets/index', { exoplanetsTable: Exoplanet.list() });
});

/* POST add exoplanet. */
router.post('/add', upload.single('image_exoplanet'), (req, res, next) => {
    const tempPath = req.file.path;
    const today = new Date();
    const todayString = today.getDate() + "-" + (today.getMonth() + 1) + "-" + today.getFullYear()
      + "-" + today.getHours() + "-" + today.getMinutes() + "-" + today.getSeconds();

    const definitivePath = "public/images/" + todayString + "-" + req.file.originalname;
    const pathToDB = "images/" + todayString + "-" + req.file.originalname;

    fs.renameSync(tempPath, definitivePath);

    Exoplanet.save({
        uniqueName: req.body.uniqueNameExoplanet,
        hClass: req.body.hClassExoplanet,
        discoveryYear: req.body.discoveryYearExoplanet,
        image: pathToDB
    });
    return res.redirect('/exoplanets');
});


/* GET search exoplanet. */
router.get('/search', (req, res, next) => {
    let exoplanetsTable = null;
    let min3Char = false;
    const uniqueNameExoplanetParam = req.query.uniqueNameExoplanet;
    if (uniqueNameExoplanetParam.length >= 3) {
        min3Char = true;
        exoplanetsTable = Exoplanet.search(uniqueNameExoplanetParam);
    }
    return res.render('exoplanets/index', { exoplanetsTable, min3Char });
});

router.post('/delete', (req, res, next) => {
    Exoplanet.delete(req.body.id);
    return res.redirect('/exoplanets');
});


router.post('/update/index', (req, res, next) => {
    results = Exoplanet.find(req.body.id);
    return res.render('exoplanets/indexUpdate', { exoplanet: results });
});

router.post('/update', (req, res, next) => {
    Exoplanet.save({
        id: req.body.id,
        uniqueName: req.body.uniqueNameExoplanet,
        hClass: req.body.hClassExoplanet,
        discoveryYear: req.body.discoveryYearExoplanet
    });
    return res.redirect('/exoplanets');
});



module.exports = router;