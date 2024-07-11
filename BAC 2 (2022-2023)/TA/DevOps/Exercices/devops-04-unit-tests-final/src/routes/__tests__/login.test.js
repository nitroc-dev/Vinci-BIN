const app = require("../../app");
const request = require("supertest");

describe("Login Routes Tests", () => {
    it("should respond with body success as login has succeed", async () => {
        const response = await request(app).get(
            "/login?username=admin&&password=admin"
        );
        expect(response.body).toEqual({ result: "Success !!!" });
    });

    it("should respond with body error as login has failed", async () => {
        const response = await request(app).get(
            "/login?username=adm&&password=adm"
        );
        expect(response.body).toEqual({
            result: { error: "Invalid credentials" },
        });
    });

    it("should respond with body error as validate credentials has failed", async () => {
        const response = await request(app).get(
            "/validate?username=adm&&password=adm"
        );
        expect(response.body).toEqual({
            result: { error: "Username should be at least 4 characters." },
        });
    });
});
