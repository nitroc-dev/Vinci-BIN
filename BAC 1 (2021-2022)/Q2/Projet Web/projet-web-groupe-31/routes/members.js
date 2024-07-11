const express = require('express');
const User = require("../models/User");
const router = express.Router();

// Route to display the member page
router.get('/', (req, res, next) => {
    if (User.isAuthenticated(req.session.login)) {
        const id_user = User.getUserByEmail(req.session.login).id_user;
        res.render('members/index', {
            questionsOpened: User.getQuestionsOpened(id_user),
            questionsSolved: User.getQuestionsSolved(id_user),
            isAuthenticated: User.isAuthenticated(req.session.login),
            isAdmin: User.isAdmin(req.session.login)
        });
    } else {
        res.redirect('/users');
    }
    req.session.error = null;
});

module.exports = router;