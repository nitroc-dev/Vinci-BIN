const validateCredentials = require("../validateCredentials");

describe("validateCredentials Services Tests", () => {
    let user;
    let password;
    let result;
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
        givenUser();
        givenPassword();
        whenValidateCredentials();
        thenResultIs(errorCredentialsMandatory);
    });

    it("should return error as credentials (password) are not provided", () => {
        givenUser("user");
        givenPassword();
        whenValidateCredentials();
        thenResultIs(errorCredentialsMandatory);
    });

    it("should return error as credentials (user) is an empty string", () => {
        givenUser();
        givenPassword("  ");
        whenValidateCredentials();
        thenResultIs(errorCredentialsMandatory);
    });

    it("should return error as credentials (password) is an empty string", () => {
        givenUser("user2");
        givenPassword("  ");
        whenValidateCredentials();
        thenResultIs(errorCredentialsMandatory);
    });

    it("should return error as username has less than 4 char.", () => {
        givenUser("use");
        givenPassword("tes");
        whenValidateCredentials();
        thenResultIs(errorUserNameTooShort);
    });

    it("should return error as username has more than 20 char.", () => {
        givenUser("useuseuseuseuseuseuseuseuseuseuseuseuseuse");
        givenPassword("tes");
        whenValidateCredentials();
        thenResultIs(errorUserNameTooLong);
    });

    it("should return error as password has less than 4 char.", () => {
        givenUser("user");
        givenPassword("tes");
        whenValidateCredentials();
        thenResultIs(errorPasswordTooShort);
    });

    it("should return error as password has more than 20 char.", () => {
        givenUser("user");
        givenPassword("etestestestestestestestestestestestess");
        whenValidateCredentials();
        thenResultIs(errorPasswordTooLong);
    });

    it("should return success message", () => {
        givenUser("user");
        givenPassword("password");
        whenValidateCredentials();
        thenResultIs("Credentials are valid");
    });

    function givenUser(customUser) {
        user = customUser;
    }

    function givenPassword(customPassword) {
        password = customPassword;
    }

    function whenValidateCredentials() {
        result = validateCredentials(user, password);
    }

    function thenResultIs(expectedResult) {
        expect(result).toEqual(expectedResult);
    }
});
