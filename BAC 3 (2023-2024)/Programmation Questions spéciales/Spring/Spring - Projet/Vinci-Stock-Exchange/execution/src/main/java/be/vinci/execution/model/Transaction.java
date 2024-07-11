package be.vinci.execution.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "transactions")
public class Transaction {
    @Id
    @Column(nullable = false)
    private String ticker;

    @Column(nullable = false)
    private String seller;

    @Column(nullable = false)
    private String buyer;

    @Column(nullable = false, name = "buy_order_guid")
    @JsonProperty(value = "buy_order_guid")
    private String buyOrderGuid;

    @Column(nullable = false, name = "sell_order_guid")
    @JsonProperty(value = "sell_order_guid")
    private String sellOrderGuid;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    public boolean invalid() {
        return ticker == null || ticker.isBlank() ||
                seller == null || seller.isBlank() ||
                buyer == null || buyer.isBlank() ||
                buyOrderGuid == null || buyOrderGuid.isBlank() ||
                sellOrderGuid == null || sellOrderGuid.isBlank() ||
                quantity < 0 ||
                price < 0;
    }
}
