const login = require("../login");

describe("Login Services Tests", () => {
    it("should failed as credentials are not in the fake database", () => {
        var result = login("test", "test");
        expect(result).toEqual({ error: "Invalid credentials" });
    });

    it("should succeed as credentials are in the fake database", () => {
        var result = login("admin", "admin");
        expect(result).toEqual("Success !!!");
    });

    it("should failed as credentials are not provided ", () => {
        var result = login();
        expect(result).toEqual({ error: "Empty credentials" });
    });
});
