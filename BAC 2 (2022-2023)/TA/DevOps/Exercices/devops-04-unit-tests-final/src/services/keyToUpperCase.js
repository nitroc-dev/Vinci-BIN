module.exports = function (items, key) {
    if (!key) {
        return undefined;
    }
    return items.map((item) => {
        item[key] =
            item[key] && item[key].length > 0
                ? item[key].toUpperCase()
                : item[key];
        return item;
    });
};
