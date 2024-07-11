// at least 8 char
// at least a letter
// at least a number

const validatePassword = require("../validatePassword");

describe("validatePassword test suites", () => {
    it("should return false as an empty string is provided", () => {
        expect(validatePassword("")).toBe(false);
    });

    it("should return true given a password 8 char. or longer, a letter and a number", () => {
        expect(validatePassword("0123456test")).toBe(true);
        expect(validatePassword("0123456TEST")).toBe(true);
        expect(validatePassword("0123456TEst")).toBe(true);
    });

    it("should return false given a password 8 char. or longer, without letter", () => {
        expect(validatePassword("01234567")).toBe(false);
    });

    it("should return false given a password 8 char. or longer, without number", () => {
        expect(validatePassword("azertyui")).toBe(false);
    });

    it("should return false given a password 7 char. or less, a letter and a number", () => {
        expect(validatePassword("azerty1")).toBe(false);
    });
});
