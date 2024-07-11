jest.mock("../../models/Funfact", () => ({
    list: jest.fn().mockReturnValue([]),
}));

const app = require("../../app");
const request = require("supertest");

describe("funFacts Routes Tests", () => {
    it("should respond with body success as login has succeed", async () => {
        const response = await request(app).get("/funfacts");
        expect(response.body).toEqual({
            funFacts: [],
            sources: ["https://www.mentalfloss.com/amazingfactgenerator"],
        });
    });
});
