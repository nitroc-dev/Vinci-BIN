const config = require('../config');
const dbPath = config.dbPath;

const db = require('better-sqlite3')(dbPath);

module.exports = db;