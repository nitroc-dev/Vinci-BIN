const db = require('./db_conf')

module.exports.add = (nom, prenom, email, password) => {
    const stmt = db.prepare("INSERT INTO members (name, firstname, email, password) VALUES (?, ?, ?, ?)");
    stmt.run(nom, prenom, email, password);
};

module.exports.getPasswordByEmail = (email) => {
    const stmt = db.prepare("SELECT password FROM members WHERE email=?");
    return stmt.get(email).password;
};

module.exports.contains = (email) => {
    const stmt = db.prepare("SELECT * FROM members WHERE email=?");
    if (stmt.all(email)) {
        return true;
    } else {
        return false;
    }
};