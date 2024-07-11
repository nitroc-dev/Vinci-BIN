package be.vinci.wallet.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Getter
@Setter
@ToString
@NoArgsConstructor
@IdClass(Position.class)
@Entity(name = "positions")
public class Position implements Serializable {

    @Id
    @Column(name = "username")
    private String username;

    @Id
    @Column(nullable = false)
    private String ticker;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "unit_value", nullable = false)
    private double unitValue;

    public boolean invalid() {
        return ticker == null || ticker.isBlank() ||
                ticker.isEmpty() ||
                quantity < 0 ||
                unitValue < 0;
    }

}

