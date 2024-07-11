const express = require('express');
const app = express();
const port = 3000;

const db = require('better-sqlite3')('exoplanets.db', { verbose: console.log });

app.get('/', (req, res) => {

    // back stick -> string multi-lines
    const createTableAndOneInsert = `
        CREATE TABLE IF NOT EXISTS EXOPLANETS (
            exoplanet_id INTEGER PRIMARY KEY AUTOINCREMENT, 
            unique_name VARCHAR(100) NOT NULL,
            hclass VARCHAR(100),
            discovery_year INTEGER
        );
        INSERT INTO EXOPLANETS(unique_name, hclass, discovery_year) VALUES ('Super Mario Planète', 'Thermoplanète', 2000);
    `;
    db.exec(createTableAndOneInsert);
    res.send('DB créée avec une ligne -> à vérifier avec Datagrip !');
});


app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`);
}); 