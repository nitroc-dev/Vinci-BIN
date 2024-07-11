const keyToUpperCase = require("../keyToUpperCase");

describe("KeyToUpperCase Tests", () => {
    let items;
    let key;
    let result;
    const itemsMock = [
        {
            title: "test",
        },
        {
            title: "testNumberTwo",
        },
    ];

    it("should failed as items and key are not provided", () => {
        givenItems();
        givenKey();
        whenkeyToUpperCase();
        thenResultIs(undefined);
    });

    it("should failed as key is not provided", () => {
        givenItems(itemsMock);
        givenKey();
        whenkeyToUpperCase();
        thenResultIs(undefined);
    });

    it("should succeed and get the same array", () => {
        givenItems(itemsMock);
        givenKey("key");
        whenkeyToUpperCase();
        thenResultIs(items);
    });

    it("should succeed and get the updated array", () => {
        givenItems(itemsMock);
        givenKey("title");
        whenkeyToUpperCase();
        thenResultIs([
            {
                title: "TEST",
            },
            {
                title: "TESTNUMBERTWO",
            },
        ]);
    });

    function givenItems(customItems) {
        items = customItems;
    }

    function givenKey(customKey) {
        key = customKey;
    }

    function whenkeyToUpperCase() {
        result = keyToUpperCase(items, key);
    }

    function thenResultIs(expectedResult) {
        expect(result).toEqual(expectedResult);
    }
});
