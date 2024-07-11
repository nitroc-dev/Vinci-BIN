const express = require('express');
const router = express.Router();

const Question = require('../models/Question');
const User = require('../models/User');

// Route to display the admin page
router.get('/', (req, res, next) => {
  if (User.isAdmin(req.session.login)) {
    res.render('admin/index', {
      questionsReported: Question.getQuestionsReported(),
      answersReported: Question.getAnswersReported(),
      isAuthenticated: User.isAuthenticated(req.session.login),
      isAdmin: User.isAdmin(req.session.login),
    });
  } else {
    res.redirect('/users');
  }
  req.session.error = null;
});

// Route to delete a question or an answer that has been reported
router.post('/delete', (req, res, next) => {
  if (req.body.id_question) {
    Question.deleteQuestion(req.body.id_question);
  } else if (req.body.id_answer) {
    const answer = Question.getAnswerById(req.body.id_answer);
    if (answer.is_correct) {
      Question.reopenQuestion(answer.id_question);
    }
    Question.deleteAnswer(req.body.id_answer);
  }
  res.redirect('/admin');
});

// Route to keep a question or an answer that has been reported
router.post('/decline', (req, res, next) => {
  if (req.body.id_question) {
    Question.removeReportQuestion(req.body.id_question);
  } else if (req.body.id_answer) {
    Question.removeReportAnswer(req.body.id_answer);
  }
  res.redirect('/admin');
});

module.exports = router;