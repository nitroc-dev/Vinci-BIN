const db = require('../models/db_conf');

module.exports.list = () => db.prepare("SELECT * FROM EXOPLANETS").all();

module.exports.save = (data) => {
  if (data.id) {
    const stmt = db.prepare('UPDATE EXOPLANETS SET unique_name = ?, hclass = ?, discovery_year = ? WHERE exoplanet_id = ?');
    stmt.run(data.uniqueName, data.hClass, data.discoveryYear, data.id);
  } else {
    const stmt = db.prepare('INSERT INTO EXOPLANETS (unique_name, hclass, discovery_year, image) VALUES (? , ?, ?, ?)');
    stmt.run(data.uniqueName, data.hClass, data.discoveryYear, data.image);
  }
};

module.exports.search = (uniqueName) => {
  return db.prepare('SELECT * FROM EXOPLANETS WHERE unique_name LIKE ?').all(uniqueName + '%');
};

module.exports.delete = (id) => {
  const stmt = db.prepare('DELETE FROM EXOPLANETS WHERE exoplanet_id = ?')
  stmt.run(id);
};


module.exports.find = (id) => {
  const stmt = db.prepare('SELECT * FROM EXOPLANETS WHERE exoplanet_id = ?');
  return stmt.get(id);
} 

