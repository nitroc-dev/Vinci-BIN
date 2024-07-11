const sortPerCreationDate = require("../sortPerCreationDate");

describe("sortPerCreationDate Tests", () => {
    let items;
    let sortType;
    let result;

    const itemsMock = [
        {
            creationDate: 2002,
        },
        {
            creationDate: 2022,
        },
    ];

    it("should failed as items and sortType are not provided", () => {
        givenItems();
        givenSortType();
        whenSortPerCreationDate();
        thenResultIs(undefined);
    });

    it("should return empty array as items are empty", () => {
        givenItems([]);
        whenSortPerCreationDate();
        thenResultIs([]);
    });

    it("should return items as items contains only one", () => {
        givenItems([itemsMock[0]]);
        whenSortPerCreationDate();
        thenResultIs([itemsMock[0]]);
    });

    it("should return desc sorted items", () => {
        givenItems(itemsMock);
        givenSortType("desc");
        whenSortPerCreationDate();
        thenResultIs(items.reverse());
    });

    it("should return asc sorted items", () => {
        givenItems(itemsMock);
        givenSortType();
        whenSortPerCreationDate();
        thenResultIs(items);
    });

    function givenItems(customItems) {
        items = customItems;
    }

    function givenSortType(customSortType) {
        sortType = customSortType;
    }

    function whenSortPerCreationDate() {
        result = sortPerCreationDate(items, sortType);
    }

    function thenResultIs(expectedResult) {
        expect(result).toEqual(expectedResult);
    }
});
