const app = require("../../app");
const request = require("supertest");

describe("Signin Routes Tests", () => {
    it("should respond with body success as login has succeed", async () => {
        // Act
        const response = await request(app).get(
            "/signin?username=admin&&password=admin"
        );
        // Assert
        expect(response.body).toEqual({ result: "Success !!!" });
    });

    it("should respond with body error as login has failed", async () => {
        // Act
        const response = await request(app).get(
            "/signin?username=adm&&password=adm"
        );
        // Assert
        expect(response.body).toEqual({
            result: { error: "Invalid credentials" },
        });
    });

    it("should respond with body error as validate credentials has failed", async () => {
        // Act
        const response = await request(app).get(
            "/validate?username=adm&&password=adm"
        );
        // Assert
        expect(response.body).toEqual({
            result: { error: "Username should be at least 4 characters." },
        });
    });
});
