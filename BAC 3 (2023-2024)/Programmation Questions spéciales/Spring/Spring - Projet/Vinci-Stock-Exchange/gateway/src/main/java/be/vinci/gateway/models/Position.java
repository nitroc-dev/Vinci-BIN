package be.vinci.gateway.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    private String username;
    private String ticker;
    private int quantity;
    private double unitvalue;
}
