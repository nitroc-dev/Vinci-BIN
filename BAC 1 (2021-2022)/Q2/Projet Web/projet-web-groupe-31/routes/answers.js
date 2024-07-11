const express = require('express');
const router = express.Router();

const Question = require('../models/question');
const User = require('../models/user');

// Route to mark an answer as correct
router.post('/validate', (req, res, next) => {
    if (User.isAuthenticated(req.session.login)) {
        const id_answer = req.body.id_answer;
        const id_user = req.body.id_user;
        const answer = Question.getAnswerById(id_answer);
        if (parseInt(User.getUserByEmail(req.session.login).id_user) === parseInt(id_user)) {
            Question.validateAnswer(id_answer, answer.id_question);
        }
        res.redirect('/questions?questionId=' + answer.id_question);
    } else {
        res.redirect('/users');
    }
});

// Route to add an answer to a question
router.post('/add', (req, res, next) => {
    if (req.body.newAnswer.length < 10) {
        req.session.error = 'Answer must be at least 10 characters long';
        return res.redirect('/questions?questionId=' + req.body.id_question);
    }
    if (User.isAuthenticated(req.session.login)) {
        Question.addAnswer(req.body.newAnswer, parseInt(req.body.id_question), User.getUserByEmail(req.session.login).id_user);
        res.redirect('/questions?questionId=' + req.body.id_question);
    } else {
        res.redirect('/users');
    }
});

// Route to report an answer
router.post('/report', (req, res, next) => {
    if (User.isAuthenticated(req.session.login)) {
        console.log(Question.isReported(req.body.id_answer));
        if (!Question.isReported(req.body.id_answer)) {
            console.log(req.body.id_answer);
            Question.report(req.body.id_answer);
        }
        res.redirect('/questions?questionId=' + req.body.id_question);
    } else {
        res.redirect('/users');
    }
});

module.exports = router;