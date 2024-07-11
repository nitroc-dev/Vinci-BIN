const db = require('./db_conf');
/*
 * Category model
 */

// Get all categories
module.exports.getAllCategories = () => {
    const stmt = db.prepare('SELECT * FROM categories');
    return stmt.all();
}

// Get all questions for a category id
module.exports.getQuestionsByCategoryId = (id_category) => {
    const stmt = db.prepare('SELECT * FROM questions WHERE id_category = ? AND is_reported = 0 ORDER BY creation_date DESC ');
    return stmt.all(id_category);
}

// Get all latest questions
module.exports.getLastQuestions = (numberOfQuestions) => {
    const stmt = db.prepare('SELECT * FROM questions WHERE is_solved= 0 ORDER BY creation_date DESC LIMIT ?');
    return stmt.all(numberOfQuestions);
}

// Get category by name
module.exports.getCategoryByName = (name) => {
    const stmt = db.prepare('SELECT * FROM categories WHERE name = ?');
    return stmt.get(name);
}

// Get category by id
module.exports.getCategoryById = (id_category) => {
    const stmt = db.prepare('SELECT * FROM categories WHERE id_category = ?');
    return stmt.get(id_category);
}