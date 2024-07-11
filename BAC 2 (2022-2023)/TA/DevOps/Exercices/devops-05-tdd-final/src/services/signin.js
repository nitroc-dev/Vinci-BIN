const fakeLoginUsername = "admin";
const fakeLoginPassword = "admin";

module.exports = function (username, password) {
    if (!username || !password) {
        return { error: "Empty credentials" };
    }
    if (username === fakeLoginUsername && password === fakeLoginPassword) {
        return `Success !!!`;
    }

    return { error: "Invalid credentials" };
};
