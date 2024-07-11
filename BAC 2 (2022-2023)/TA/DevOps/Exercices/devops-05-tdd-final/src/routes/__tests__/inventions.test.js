const inventionsMock = [
    {
        id: 69691,
        title: "L'Appel du devoir",
        author: "Activision",
        creationDate: 2003,
    },
    {
        id: 10101,
        title: "One Piece",
        author: "Eichiro Oda",
        creationDate: 1997,
    },
];

jest.mock("../../models/Invention", () => ({
    list: jest
        .fn()
        .mockReturnValueOnce([])
        .mockReturnValueOnce(inventionsMock)
        .mockReturnValueOnce(inventionsMock),
}));

const app = require("../../app");
const request = require("supertest");

describe("inventions Routes Tests", () => {
    it("should return inventions", async () => {
        // Act
        const response = await request(app).get("/inventions");
        // Assert
        expect(response.body).toEqual({
            inventions: [],
            sources: [
                "https://www.thoughtco.com/20th-century-timeline-1992486",
                "https://en.wikipedia.org/wiki",
            ],
        });
    });

    it("should return inventions sorted desc", async () => {
        const response = await request(app).get("/inventions/sort/desc");
        expect(response.body.inventions).toEqual(inventionsMock);
    });
});
