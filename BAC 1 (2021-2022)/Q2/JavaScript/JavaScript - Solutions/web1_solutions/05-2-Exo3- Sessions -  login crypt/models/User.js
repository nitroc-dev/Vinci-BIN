
const db = require('../models/db_conf');

module.exports.save = (data) => {
    console.log(data);
    const stmt = db.prepare('INSERT INTO MEMBERS(name, firstname, email, password) VALUES (?, ?, ?, ?)');
    const info = stmt.run(data.name, data.firstname, data.email, data.password);
    console.log("user model save member" + info.changes);
}


module.exports.find = (email) => {
    console.log(email);
    const stmt = db.prepare('SELECT * FROM MEMBERS WHERE email = ?');
    // get -> only one record
    return stmt.get(email);
}