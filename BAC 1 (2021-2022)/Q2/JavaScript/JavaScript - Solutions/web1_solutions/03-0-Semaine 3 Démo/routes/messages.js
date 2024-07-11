var express = require('express');
var router = express.Router();

const Message = require('../models/Message.js');


/* GET forum. */
router.get('/', function(req, res, next) {
 
    let messagesTable = Message.list();
    res.render('messages/index', { messagesTable : messagesTable });
  });
  
  
  /* POST add forum. */
  router.post('/add', function(req, res, next) {
  
    console.log("POST ADD FORUM");
   
    let messageParam = req.body.message;
    let authorParam = req.body.author;
    //messagesTable.push({message : messageParam, author: authorParam});

    Message.add({message : messageParam, author: authorParam});
  
    res.redirect('/messages');
  });

  module.exports = router;