
let messagesTable = [ 
];

                  
module.exports.list = () => {
  return messagesTable;
};

module.exports.add = (data) => {
  messagesTable.push(data);
};