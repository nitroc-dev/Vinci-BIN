const generateNameAndYears = require("../generateNameAndYears");

describe("Generate Name and Years Tests", () => {
    it("should failed as no name is provided", () => {
        var result = generateNameAndYears();
        expect(result).toEqual(`${undefined} (${undefined} years old)`);
    });

    it("should succeed", () => {
        var result = generateNameAndYears("Name", "23");
        expect(result).toEqual(`Name (23 years old)`);
    });
});
