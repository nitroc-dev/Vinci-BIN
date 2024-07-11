const keyToUpperCase = require("../keyToUpperCase");

describe("KeyToUpperCase Tests", () => {
    const items = [
        {
            title: "test",
        },
        {
            title: "testNumberTwo",
        },
    ];

    it("should failed as items and key are not provided", () => {
        var result = keyToUpperCase();
        expect(result).toEqual(undefined);
    });

    it("should failed as key is not provided", () => {
        var result = keyToUpperCase(items);
        expect(result).toEqual(undefined);
    });

    it("should succeed and get the same array", () => {
        var result = keyToUpperCase(items, "key");
        expect(result).toEqual(items);
    });

    it("should succeed and get the updated array", () => {
        var result = keyToUpperCase(items, "title");
        expect(result).toEqual([
            {
                title: "TEST",
            },
            {
                title: "TESTNUMBERTWO",
            },
        ]);
    });
});
