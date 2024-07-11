package be.vinci.order.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private String guid;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private int timestamp;

    @Column(nullable = false)
    private String ticker;

    @Column(nullable = false)
    private int quantity;

    @Enumerated()
    @Column(nullable = false)
    private OrderSide side;

    @Enumerated()
    @Column(nullable = false)
    private OrderType type;

    @Column(name = "limit_price")
    private int limit;

    @ColumnDefault("0")
    private int filled;

    public boolean invalid() {
        System.out.println("Order.invalid");
        System.out.println(this);
        return guid != null ||
                owner == null || owner.isBlank() ||
                ticker == null || ticker.isBlank() ||
                quantity <= 0 ||
                side == null ||
                type == null ||
                limit < 0 ||
                (type == OrderType.LIMIT && limit <= 0);
    }
}
