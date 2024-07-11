package be.vinci.price;

import org.springframework.stereotype.Service;

@Service
public class PriceService {

    private final PriceRepository repository;

    public PriceService(PriceRepository repository) {
        this.repository = repository;
    }

    /**
     * Get the price of a ticker
     * @param ticker the ticker of the price
     * @return the price of the ticker
     */
    public double getPrice(String ticker) {
        return repository.findByTicker(ticker)
                .map(Price::getValue)
                .orElse(1.0); // default value for new tickers
    }

    /**
     * Update the price of a ticker
     * @param ticker the ticker of the price
     * @param newValue the new value of the price
     */
    public void updatePrice(String ticker, double newValue) {
        Price price = repository.findByTicker(ticker)
                .orElse(new Price(ticker, newValue));

        price.setValue(newValue);
        repository.save(price);
    }
}
