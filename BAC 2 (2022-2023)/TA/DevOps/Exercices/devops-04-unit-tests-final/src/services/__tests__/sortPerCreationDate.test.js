const sortPerCreationDate = require("../sortPerCreationDate");

describe("sortPerCreationDate Tests", () => {
    const items = [
        {
            creationDate: 2002,
        },
        {
            creationDate: 2022,
        },
    ];

    it("should failed as items and sortType are not provided", () => {
        var result = sortPerCreationDate();
        expect(result).toEqual(undefined);
    });

    it("should return empty array as items are empty", () => {
        var result = sortPerCreationDate([]);
        expect(result).toEqual([]);
    });

    it("should return items as items contains only one", () => {
        var result = sortPerCreationDate([items[0]]);
        expect(result).toEqual([items[0]]);
    });

    it("should return desc sorted items", () => {
        var result = sortPerCreationDate(items);
        expect(result).toEqual(items.reverse());
    });

    it("should return asc sorted items", () => {
        var result = sortPerCreationDate(items, "asc");
        expect(result).toEqual(items);
    });
});
