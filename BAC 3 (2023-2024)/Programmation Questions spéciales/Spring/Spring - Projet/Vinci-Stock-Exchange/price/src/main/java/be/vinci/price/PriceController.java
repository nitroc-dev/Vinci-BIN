package be.vinci.price;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    /**
     * Get the price of a ticker
     * @param ticker the ticker of the price
     * @return the price of the ticker
     */
    @GetMapping("/price/{ticker}")
    public ResponseEntity<Double> getPrice(@PathVariable String ticker) {
        double price = priceService.getPrice(ticker);
        return ResponseEntity.ok(price);
    }

    /**
     * Update the price of a ticker
     * @param ticker the ticker of the price
     * @param newValue the new value of the price
     * @return 200 OK if the price is updated
     *         400 if the new value is negative
     */
    @PatchMapping("/price/{ticker}")
    public ResponseEntity<Void> updatePrice(
            @PathVariable String ticker,
            @RequestBody double newValue) {
        if (newValue < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        priceService.updatePrice(ticker, newValue);
        return ResponseEntity.ok().build();
    }
}
