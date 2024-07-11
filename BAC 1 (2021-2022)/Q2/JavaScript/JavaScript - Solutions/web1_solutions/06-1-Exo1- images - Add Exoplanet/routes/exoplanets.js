const express = require('express');
const router = express.Router();

const multer = require('multer');
const upload = multer({ dest: 'public/images/' });

const Exoplanet = require('../models/Exoplanet.js');
const fs = require('fs');


/* GET exoplanets index. */
router.get('/', (req, res, next) => {
  res.render('exoplanets/index', { exoplanetsTable: Exoplanet.list() });
});

/* POST add exoplanet. */
router.post('/add', upload.single('image_exoplanet'),
  (req, res, next) => {
    console.log("POST ADD EXOPLANET");

    console.log("MULTER INFOS : " + req.file.originalname + " " + req.file.path);
    const tempPath = req.file.path;
    const today = new Date();
    const todayString = today.getDate() + "-" + (today.getMonth() + 1) + "-" + today.getFullYear()
      + "-" + today.getHours() + "-" + today.getMinutes() + "-" + today.getSeconds();
    const definitivePath = "public/images/" + todayString + "-" + req.file.originalname;
    const pathToDB = "images/" + todayString + "-" + req.file.originalname;
    console.log("TARGET PATH : " + definitivePath);
    console.log("PATH to DB : " + pathToDB);

    fs.renameSync(tempPath, definitivePath);

    Exoplanet.save({
      uniqueName: req.body.uniqueNameExoplanet,
      hClass: req.body.hClassExoplanet,
      discoveryYear: req.body.discoveryYearExoplanet,
      image: pathToDB
    });
    console.log("multer" + req.file, req.body)
    res.redirect('/exoplanets');

  });


/* GET search exoplanet. */
router.get('/search', (req, res, next) => {
  let exoplanetsTable = null;
  let min3Char = false;
  console.log("GET SEARCH EXOPLANET");
  const uniqueNameExoplanetParam = req.query.uniqueNameExoplanet;
  if (uniqueNameExoplanetParam.length >= 3) {
    min3Char = true;
    exoplanetsTable = Exoplanet.search(uniqueNameExoplanetParam);
  }
  res.render('exoplanets/index', { exoplanetsTable, min3Char });
});

router.post('/delete', (req, res, next) => {
  console.log("id Exoplanète à supprimer : " + req.body.id);
  Exoplanet.delete(req.body.id);
  res.redirect('/exoplanets');
});


router.post('/update/index', (req, res, next) => {
  console.log("id exoplanet : " + req.body.id);
  results = Exoplanet.find(req.body.id);
  res.render('exoplanets/indexUpdate', { exoplanet: results });
});

router.post('/update', (req, res, next) => {
  console.log("POST UPDATE EXOPLANET");
  Exoplanet.save({
    id: req.body.id,
    uniqueName: req.body.uniqueNameExoplanet,
    hClass: req.body.hClassExoplanet,
    discoveryYear: req.body.discoveryYearExoplanet
  });
  res.redirect('/exoplanets');
});



module.exports = router;