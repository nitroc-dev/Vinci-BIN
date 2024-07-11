const validateCredentials = require("../validateCredentials");

describe("validateCredentials Services Tests", () => {
    const errorCredentialsMandatory = { error: "Credentials are mandatory" };
    const errorUserNameTooShort = {
        error: "Username should be at least 4 characters.",
    };
    const errorUserNameTooLong = { error: "Username is too long." };
    const errorPasswordTooShort = {
        error: "Password should be at least 4 characters.",
    };
    const errorPasswordTooLong = { error: "Password is too long." };

    it("should return error as credentials (username) are not provided", () => {
        var result = validateCredentials();
        expect(result).toEqual(errorCredentialsMandatory);
    });

    it("should return error as credentials (password) are not provided", () => {
        var result = validateCredentials("user");
        expect(result).toEqual(errorCredentialsMandatory);
    });

    it("should return error as credentials (user) is an empty string", () => {
        var result = validateCredentials(" ");
        expect(result).toEqual(errorCredentialsMandatory);
    });

    it("should return error as credentials (password) is an empty string", () => {
        var result = validateCredentials("user2", "  ");
        expect(result).toEqual(errorCredentialsMandatory);
    });

    it("should return error as username has less than 4 char.", () => {
        var result = validateCredentials("use", "tes");
        expect(result).toEqual(errorUserNameTooShort);
    });

    it("should return error as username has more than 20 char.", () => {
        var result = validateCredentials(
            "useuseuseuseuseuseuseuseuseuseuseuseuseuse",
            "tes"
        );
        expect(result).toEqual(errorUserNameTooLong);
    });

    it("should return error as password has less than 4 char.", () => {
        var result = validateCredentials("user", "tes");
        expect(result).toEqual(errorPasswordTooShort);
    });

    it("should return error as password has more than 20 char.", () => {
        var result = validateCredentials(
            "user",
            "tetestestestestestestestestestestestess"
        );
        expect(result).toEqual(errorPasswordTooLong);
    });

    it("should return success message", () => {
        var result = validateCredentials("user", "password");
        expect(result).toEqual("Credentials are valid");
    });
});
