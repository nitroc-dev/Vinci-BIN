package be.vinci.matching.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Order {

  private String guid;
  private String owner;
  private long timestamp;
  private String ticker;
  private int quantity;
  private Side side;
  private Type type;
  private double limit;
  private int filled;

  @JsonFormat(shape = Shape.STRING)
  public enum Side {
    BUY, SELL
  }

  @JsonFormat(shape = Shape.STRING)
  public enum Type {
    MARKET, LIMIT
  }

}
