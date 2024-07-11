package be.vinci.price;

import jakarta.persistence.*;
import lombok.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = "prices")
public class Price {

    @Id
    @Column(nullable = false)
    private String ticker;

    @Column(name = "_value" ,nullable = false)
    private double value;

    public Price(String ticker, double value) {
        this.ticker = ticker;
        this.value = value;
    }

    public boolean invalid() {
        return ticker == null || ticker.isBlank() ||
                ticker.isEmpty() ||
                value < 0;
    }
}
