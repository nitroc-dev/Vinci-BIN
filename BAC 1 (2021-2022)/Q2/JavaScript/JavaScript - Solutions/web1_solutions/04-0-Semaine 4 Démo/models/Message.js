const db = require('../models/db_conf');



module.exports.list = () => db.prepare("SELECT * FROM MESSAGES").all();

module.exports.add = (data) => {
  //messagesTable.push(data);
  // use of prepared statement with parameters
  const stmt = db.prepare('INSERT INTO MESSAGES (message, author) VALUES (?, ?)');
  const info = stmt.run(data.message, data.author);
  console.log("messages model save" + info.changes);
};