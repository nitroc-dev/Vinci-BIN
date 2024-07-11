const db = require('./db_conf')

module.exports.list = () => {
    const stmt = db.prepare("SELECT * FROM exoplanets");
    return stmt.all();
};

module.exports.add = (data) => {
    const stmt = db.prepare("INSERT INTO exoplanets (unique_name, hclass, discovery_year) VALUES (?, ?, ?)");
    stmt.run(data.unique_name, data.hclass, data.discovery_year);
};

module.exports.search = (data) => {
    if (String(data).length < 3) {
        return "Veuillez entrer 3 caractères au minimum"
    }
    const stmt = db.prepare("SELECT * FROM exoplanets WHERE unique_name LIKE ?");
    return stmt.all(data.toLowerCase()+"%");
};

module.exports.delete = (data) => {
    const stmt = db.prepare("DELETE FROM exoplanets WHERE exoplanet_id = ?");
    stmt.run(data);
};

module.exports.getPlanetById = (exoplanet_id) => {
    const stmt = db.prepare("SELECT * FROM exoplanets WHERE exoplanet_id = ?");
    return stmt.get(exoplanet_id);
};

module.exports.update = (exoplanet) => {
    const stmt = db.prepare("UPDATE exoplanets SET unique_name= ?, hclass= ?, discovery_year= ? WHERE exoplanet_id= ?");
    stmt.run(exoplanet.unique_name, exoplanet.hclass, exoplanet.discovery_year, exoplanet.exoplanet_id);
}