const signin = require("../signin");

describe("Signin Services Tests", () => {
    let username;
    let password;
    let result;

    it("should failed as credentials are not in the fake database", () => {
        givenUsername("test");
        givenPassword("test");
        whenSignin();
        thenResultIs({ error: "Invalid credentials" });
    });

    it("should succeed as credentials are in the fake database", () => {
        givenUsername("admin");
        givenPassword("admin");
        whenSignin();
        thenResultIs("Success !!!");
    });

    it("should failed as credentials are not provided ", () => {
        givenUsername();
        givenPassword();
        whenSignin();
        thenResultIs({ error: "Empty credentials" });
    });

    function givenUsername(customUsername) {
        username = customUsername;
    }

    function givenPassword(customPassword) {
        password = customPassword;
    }

    function whenSignin() {
        result = signin(username, password);
    }

    function thenResultIs(expectedResult) {
        expect(result).toEqual(expectedResult);
    }
});
