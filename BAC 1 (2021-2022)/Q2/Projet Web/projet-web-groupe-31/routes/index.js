const express = require('express');
const router = express.Router();

const Category = require('../models/Category');
const Question = require('../models/Question');
const User = require('../models/User');

// Route to display the home page
router.get('/', (req, res, next) => {
   console.log("Nombre Utilisateurs: " + User.getUsers().nbr_users)
   console.log("Nombres Questions ouvertes: " + Question.getQuestionsOpen().length)
   console.log("Nombres Questions fermées: " + Question.getQuestionsClosed().length)

   if (req.query.categoryId) {
      res.render('index', {
         categories: Category.getAllCategories(),
         questions: Category.getQuestionsByCategoryId(req.query.categoryId),
         isAuthenticated: User.isAuthenticated(req.session.login),
         isAdmin: User.isAdmin(req.session.login)
      });
   } else if (req.query.title) {
      let questions = Question.getQuestionsByTitle(req.query.title);
      if (questions.length <= 0) {
         res.render('index', {
            categories: Category.getAllCategories(),
            questions: questions,
            error: 'No questions found',
            isAuthenticated: User.isAuthenticated(req.session.login),
            isAdmin: User.isAdmin(req.session.login)
         });
      } else {
         res.render('index', {
            categories: Category.getAllCategories(),
            questions: questions,
            isAuthenticated: User.isAuthenticated(req.session.login),
            isAdmin: User.isAdmin(req.session.login)
         });
      }
   } else {
      res.render('index', {
         categories: Category.getAllCategories(),
         questions: Category.getLastQuestions(20),
         isAuthenticated: User.isAuthenticated(req.session.login),
         isAdmin: User.isAdmin(req.session.login)
      });
   }
   req.session.error = null;
});

module.exports = router;