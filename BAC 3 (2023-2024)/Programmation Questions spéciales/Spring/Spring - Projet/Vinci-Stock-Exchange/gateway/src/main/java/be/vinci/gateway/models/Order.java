package be.vinci.gateway.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String owner;
    private int timestamp;
    private String ticker;
    private int quantity;
    private String side;
    private String type;
    private int limit;
    private int filled;
}
