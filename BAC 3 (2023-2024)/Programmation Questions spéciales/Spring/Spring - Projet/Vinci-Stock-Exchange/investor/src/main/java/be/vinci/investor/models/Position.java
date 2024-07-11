package be.vinci.investor.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
public class Position {


    private String username;

    private String ticker;

    private int quantity;

    private double unitValue;

}
