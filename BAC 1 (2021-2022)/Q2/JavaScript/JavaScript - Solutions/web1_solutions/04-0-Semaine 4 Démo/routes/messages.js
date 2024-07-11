const express = require('express');
const router = express.Router();
const Message = require('../models/Message.js');



/* GET forum. */
router.get('/', (req, res, next) => {
  res.render('messages/index', { messagesTable: Message.list() });
});

/* POST add forum. */
router.post('/add', (req, res, next) => {
  console.log("POST ADD FORUM");
  //messagesTable.push({message : req.body.message, author: req.body.author});
  Message.add({ message: req.body.message, author: req.body.author });
  res.redirect('/messages');
});



module.exports = router;