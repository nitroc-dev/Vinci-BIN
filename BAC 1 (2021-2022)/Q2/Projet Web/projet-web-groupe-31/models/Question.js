const db = require('./db_conf');
/*
 * Question model
 */

// Add the Question to the db
module.exports.addQuestion = (question_title, question_subject, id_user, id_category) => {
    const stmt = db.prepare('INSERT INTO questions(title, subject, id_user, id_category) VALUES (?, ?, ?, ?) RETURNING id_question');
    return stmt.get(question_title, question_subject, id_user, id_category);
}

// Get a question id by its title
module.exports.getQuestionIdByTitle = (title) => {
    const stmt = db.prepare("SELECT id_question FROM questions WHERE title = ?");
    return stmt.get(title);
}

// Get a question by its title
module.exports.getQuestionsByTitle = (title) => {
    const stmt = db.prepare('SELECT * FROM questions WHERE title LIKE ?');
    return stmt.all("%" + title + "%");
}

// Get all questions reported
module.exports.getQuestionsReported = () => {
    const stmt = db.prepare("SELECT * FROM questions WHERE is_reported = 1");
    return stmt.all();
}

// Delete the question in the db
module.exports.deleteQuestion = (id_question) => {
    const stmt = db.prepare("DELETE FROM questions WHERE id_question = ?");
    stmt.run(id_question);
}

// Report the question to the admins of the website
module.exports.reportQuestion = (id_question) => {
    const stmt = db.prepare("UPDATE questions SET is_reported = 1 WHERE id_question = ?");
    stmt.run(id_question);
}

// Remove the report of the question
module.exports.removeReportQuestion = (id_question) => {
    const stmt = db.prepare("UPDATE questions SET is_reported = 0 WHERE id_question = ?");
    stmt.run(id_question);
}

// Remove the report of the answer
module.exports.removeReportAnswer = (id_answer) => {
    const stmt = db.prepare("UPDATE answers SET is_reported = 0 WHERE id_answer = ?");
    stmt.run(id_answer);
}

// Get question by its id
module.exports.getQuestionById = (id_question) => {
    const stmt = db.prepare("SELECT * FROM questions qu, users us WHERE id_question = ? AND us.id_user = qu.id_user");
    return stmt.get(id_question);
}

// Get all answers of a question
module.exports.getAnswersToQuestion = (id_question) => {
    const stmt = db.prepare("SELECT * FROM answers an, users us WHERE an.id_question = ? AND an.id_user = us.id_user ORDER BY an.is_correct DESC, an.creation_date DESC");
    return stmt.all(id_question);
}

// Get question id of an answer
module.exports.getQuestionIdToAnswer = (id_answer) => {
    const stmt = db.prepare("SELECT id_question FROM answers WHERE id_answer = ?");
    return stmt.get(id_answer);
}

// Mark the question as not solved to allow new answers
module.exports.reopenQuestion = (id_question) => {
    const stmt = db.prepare("UPDATE questions SET is_solved = 0 WHERE id_question = ?");
    stmt.run(id_question);
}

// Add an answer to a specific question
module.exports.addAnswer = (answer, id_question, id_user) => {
    const stmt = db.prepare('INSERT INTO answers(question_answer, id_question, id_user) VALUES (?, ? ,?)');
    stmt.run(answer, id_question, id_user);
}

// Get all answers reported
module.exports.getAnswersReported = () => {
    const stmt = db.prepare("SELECT * FROM answers an, questions qu WHERE an.is_reported = 1 AND an.id_question = qu.id_question");
    return stmt.all();
}

// Delete an answer from the db
module.exports.deleteAnswer = (id_answer) => {
    const stmt = db.prepare("DELETE FROM answers WHERE id_answer = ?");
    stmt.run(id_answer);
}

// Get the answer by its id
module.exports.getAnswerById = (id_answer) => {
    const stmt = db.prepare('SELECT * FROM answers WHERE id_answer = ?');
    return stmt.get(id_answer);
}

// Mark the answer as correct
module.exports.validateAnswer = (id_answer, id_question) => {
    const stmt = db.prepare('UPDATE answers SET is_correct = true WHERE id_answer = ?');
    stmt.run(id_answer);
    const stmt2 = db.prepare('UPDATE questions SET is_solved = true WHERE id_question = ?');
    stmt2.run(id_question)
}

// Report the answer to the admins of the website
module.exports.report = (id_answer) => {
    const stmt = db.prepare('UPDATE answers SET is_reported = true WHERE id_answer = ?');
    stmt.run(id_answer);
}

// Return if the answer is reported
module.exports.isReported = (id_answer) => {
    const stmt = db.prepare('SELECT is_reported FROM answers WHERE id_answer = ?');
    const result = stmt.run(id_answer);
    if (result) {
        return !!result.is_reported;
    }
}

module.exports.getQuestionsOpen = () => {
    const stmt = db.prepare('SELECT * FROM questions WHERE is_solved = ?');
    return stmt.all(0);
}

module.exports.getQuestionsClosed = () => {
    const stmt = db.prepare('SELECT * FROM questions WHERE is_solved = ?');
    return stmt.all(1);
}