jest.mock("node-fetch");

const app = require("../../app");
const request = require("supertest");
const fetch = require("node-fetch");
const { Response } = jest.requireActual("node-fetch");

describe("Products Routes Tests", () => {
    const products = [{ id: 1 }, { id: 2 }, { id: 3 }];

    beforeEach(() => {
        jest.clearAllMocks();
    });

    it("should respond with body products", async () => {
        // Arrange
        fetch.mockResolvedValueOnce(
            Promise.resolve(new Response(JSON.stringify(products)))
        );
        // Act
        const response = await request(app).get("/products");
        // Assert
        expect(response.body).toEqual({ products });
        expect(fetch).toHaveBeenCalledTimes(1);
        expect(fetch).toHaveBeenCalledWith("https://dummyjson.com/products");
    });

    it("should respond with body products for product id", async () => {
        // Arrange
        fetch.mockResolvedValueOnce(
            Promise.resolve(new Response(JSON.stringify(products)))
        );
        // Act
        const response = await request(app).get("/products/69");
        // Assert
        expect(response.body).toEqual({ products });
        expect(fetch).toHaveBeenCalledTimes(1);
        expect(fetch).toHaveBeenCalledWith("https://dummyjson.com/products/69");
    });
});
