package be.vinci.wallet.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Price {

    private String ticker;
    private double value;
}