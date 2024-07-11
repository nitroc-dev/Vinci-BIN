const express = require('express');
const router = express.Router();

const Category = require('../models/Category');
const Question = require('../models/Question');
const User = require('../models/User');

// Route to display the question page
router.get('/', (req, res, next) => {
    const idQuestion = req.query.questionId;

    let theQuestion = Question.getQuestionById(idQuestion);
    let listAnswers = Question.getAnswersToQuestion(idQuestion)
    let isOwnerOfQuestion = false;

    if (req.session.login) {
        if (theQuestion.id_user === User.getUserByEmail(req.session.login).id_user) {
            isOwnerOfQuestion = true;
        }
    }

    const isAuthenticated = User.isAuthenticated(req.session.login);
    const isAdmin = User.isAdmin(req.session.login);
    if (theQuestion && listAnswers) {
        res.render('questions/index', {
            theQuestion: theQuestion,
            isOwnerOfQuestion: isOwnerOfQuestion,
            isSolved: theQuestion.is_solved,
            category: Category.getCategoryById(theQuestion.id_category).name,
            listAnswers: listAnswers,
            isAuthenticated: isAuthenticated,
            isAdmin: isAdmin,
            error: req.session.error,
        });
    } else {
        res.render('questions/index', {
            error: 'Question non trouvée',
            isAuthenticated: isAuthenticated,
            isAdmin: isAdmin
        });
    }
    req.session.error = null;
});

// Route to display the form to add a question
router.get('/add', (req, res, next) => {
    if (req.session.login) {
        res.render('questions/create', {
            error: req.session.error,
            categories: Category.getAllCategories(),
            isAuthenticated: User.isAuthenticated(req.session.login),
            isAdmin: User.isAdmin(req.session.login),
        });
        req.session.error = null;
    } else {
        res.redirect('/users');
    }
});

// Route to report a question
router.post('/report', (req, res, next) => {
    if (User.isAuthenticated(req.session.login)) {
        Question.reportQuestion(req.body.id_question);
        res.redirect('/questions?questionId=' + req.body.id_question);
    } else {
        res.redirect('/users');
    }
});

// Route to add a question
router.post('/add', (req, res, next) => {
    if (User.isAuthenticated(req.session.login)) {
        if (req.body.id_category && req.body.title && req.body.subject) {
            if (req.body.subject.length < 5) {
                req.session.error = "Subject inférieur à 5";
                return res.redirect('/questions/add');
            }
            const idQuestionCree = Question.addQuestion(req.body.title, req.body.subject, User.getUserByEmail(req.session.login).id_user, req.body.id_category).id_question;
            res.redirect('/questions?questionId=' + idQuestionCree);
        } else {
            res.redirect('/questions/add');
        }
    } else {
        res.redirect('/users');
    }
});

module.exports = router;