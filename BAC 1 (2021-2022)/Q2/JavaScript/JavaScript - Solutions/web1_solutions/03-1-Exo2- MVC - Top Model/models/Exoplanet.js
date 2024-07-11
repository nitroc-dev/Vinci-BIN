const exoplanetsTable = [
  { uniqueName: "TRAPPIST-1-d", hClass: "Mésoplanète", discoveryYear: 2016 },
  { uniqueName: "KOI-1686.01", hClass: "Mésoplanète", discoveryYear: 2011 },
  { uniqueName: "LHS 1723 b", hClass: "Mésoplanète", discoveryYear: 2017 },
];

module.exports.list = () => {
  return exoplanetsTable;
};

// On peut également écrire 
// module.exports.list = () => exoplanetsTable;