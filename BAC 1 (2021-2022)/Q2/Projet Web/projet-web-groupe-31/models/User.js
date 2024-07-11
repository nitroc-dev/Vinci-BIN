const db = require('./db_conf');
/*
 * User model
 */

// Add new user to database
module.exports.add = (personalDetails) => {
    const stmt = db.prepare("INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)");
    stmt.run(personalDetails.firstname, personalDetails.lastname, personalDetails.email, personalDetails.password);
}

// Get all questions openned for user
module.exports.getQuestionsOpened = (id_user) => {
    const stmt = db.prepare('SELECT * FROM questions WHERE is_solved = ? AND id_user = ?');
    return stmt.all(0, id_user);
}

// Get all questions solved for user
module.exports.getQuestionsSolved = (id_user) => {
    const stmt = db.prepare('SELECT * FROM questions WHERE is_solved = ? AND id_user = ?');
    return stmt.all(1, id_user);
}

// Return if the user is logged in
module.exports.isAuthenticated = (login) => {
    const stmt = db.prepare('SELECT * FROM users WHERE email = ?');
    let result = stmt.get(login);
    return !!result;
}

// Return if the user is admin
module.exports.isAdmin = (login) => {
    if (this.isAuthenticated(login)) {
        const stmt = db.prepare('SELECT is_admin FROM users WHERE email = ?');
        let result = stmt.get(login);
        return !!result.is_admin;
    } else {
        return false;
    }
}

// Get user by its id
module.exports.getUserById = (id) => {
    const stmt = db.prepare('SELECT * FROM users WHERE id_user = ?');
    return stmt.get(id);
}

// Get user by its email
module.exports.getUserByEmail = (email) => {
    const stmt = db.prepare('SELECT * FROM users WHERE email = ?');
    return stmt.get(email);
}

module.exports.getUsers = () => {
    const stmt = db.prepare('SELECT count(id_user) AS nbr_users FROM users');
    return stmt.get();
}