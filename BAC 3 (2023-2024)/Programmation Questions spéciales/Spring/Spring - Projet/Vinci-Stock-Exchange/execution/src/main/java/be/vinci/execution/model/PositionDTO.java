package be.vinci.execution.model;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PositionDTO {
    private String ticker;
    private double quantity;
    private double unitValue;
}
