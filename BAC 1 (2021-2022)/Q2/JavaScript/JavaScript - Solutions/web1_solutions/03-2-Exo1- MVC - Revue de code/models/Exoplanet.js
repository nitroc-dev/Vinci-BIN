
const exoplanetsTable = [
  { uniqueName: "TRAPPIST-1-d", hClass: "Mésoplanète", discoveryYear: 2016 },
  { uniqueName: "KOI-1686.01", hClass: "Mésoplanète", discoveryYear: 2011 },
  { uniqueName: "LHS 1723 b", hClass: "Mésoplanète", discoveryYear: 2017 },
];

module.exports.list = () => exoplanetsTable;

module.exports.save = (data) => {
  exoplanetsTable.push(data);
};

module.exports.search = (uniqueName) => {
  for (exoplanet of exoplanetsTable) {
    if (exoplanet.uniqueName === uniqueName) {
      console.log("trouvé");
      return exoplanet;
    }
  }
  // not found
  return null;
};

// ON peut également faire le find comme ceci, en tirant parti des méthodes JS existantes
// module.exports.search = uniqueName => exoplanetsTable.find(exoplanet => exoplanet.uniqueName === uniqueName) || null