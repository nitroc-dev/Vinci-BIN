const asc = "asc";

module.exports = function (items, sortType) {
    if (!items) {
        return;
    }

    if (items.length === 0 || items.length === 1) {
        return items;
    }
    const sortedItems = items.sort(
        (item1, item2) => item1.creationDate - item2.creationDate
    );
    return sortType === asc ? sortedItems : sortedItems.reverse();
};
