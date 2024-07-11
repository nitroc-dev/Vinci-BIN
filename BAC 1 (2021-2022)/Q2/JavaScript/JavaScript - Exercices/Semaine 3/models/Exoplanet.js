let exoplanetsList = [
    {uniqueName:"TRAPPIST-1-d", hClass:"Mésoplanète", discoveryYear:2016}, 
    {uniqueName:"KO1-1686-01", hClass:"Mésoplanète", discoveryYear:2011}, 
    {uniqueName:"LHS-1723-b", hClass:"Mésoplanète", discoveryYear:2017}];

let exoplanetsFound = []; 
module.exports.list = () => {
    return exoplanetsList;
};

module.exports.add = (data) => {
    exoplanetsList.push(data);
};

module.exports.search = (data) => {
    if (String(data).length < 3) {
        return "Veuillez entrer 3 caractères au minimum"
    }
    for (const exoplanet of exoplanetsList) {
        if (String(exoplanet.uniqueName.toLocaleLowerCase()).startsWith(data.toLocaleLowerCase())) {
            exoplanetsFound.push(exoplanet);
        };
    };
    return exoplanetsFound;
};
