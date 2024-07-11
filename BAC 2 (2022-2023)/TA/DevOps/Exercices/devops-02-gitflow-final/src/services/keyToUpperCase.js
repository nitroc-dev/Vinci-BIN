module.exports = function (items, key) {
    return items.map((item) => {
        item[key] = item[key].length > 0 ? item[key].toUpperCase() : item[key];
        return item;
    });
};
