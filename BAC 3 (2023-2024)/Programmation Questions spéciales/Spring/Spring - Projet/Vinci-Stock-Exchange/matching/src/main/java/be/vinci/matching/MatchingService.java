package be.vinci.matching;

import java.util.List;
import org.springframework.stereotype.Service;
import be.vinci.matching.models.Order;
import be.vinci.matching.models.Transaction;
import be.vinci.matching.repositories.ExecutionProxy;
import be.vinci.matching.repositories.OrderProxy;
import be.vinci.matching.repositories.PriceProxy;

@Service
public class MatchingService {

  private final ExecutionProxy executionProxy;
  private final OrderProxy orderProxy;

  private final PriceProxy priceProxy;

  public MatchingService(ExecutionProxy executionProxy, OrderProxy orderProxy,
      PriceProxy priceProxy) {
    this.executionProxy = executionProxy;
    this.orderProxy = orderProxy;
    this.priceProxy = priceProxy;
  }

  /**
   * Look for any matching orders for a given ticker, and report to the execution service if any
   *
   * @param ticker The ticker to match on
   */
  public void trigger(String ticker) {
    List<Order> buyOrders = orderProxy.getAllOpen(ticker, String.valueOf(Order.Side.BUY));
    List<Order> sellOrders = orderProxy.getAllOpen(ticker, String.valueOf(Order.Side.SELL));

    for (Order buyOrder : buyOrders) {
      int sharesLeftToBuy = buyOrder.getQuantity() - buyOrder.getFilled();

      for (Order sellOrder : sellOrders) {
        int sharesLeftToSell = sellOrder.getQuantity() - sellOrder.getFilled();
        if (sharesLeftToSell == 0) {
          continue;
        }

        double buyPrice = buyOrder.getLimit();
        double sellPrice = sellOrder.getLimit();

        switch (buyOrder.getType()) {
          case LIMIT:
            if (sellPrice > buyPrice) {
              continue;
            }
            break;
          case MARKET:
            buyPrice = priceProxy.getLatestPrice(ticker);
            break;
        }

        // Everything looks good, we can execute the trade
        int quantity = Math.min(sharesLeftToBuy, sharesLeftToSell);

        // We update the orders for the next matching
        sharesLeftToBuy -= quantity;
        sellOrder.setFilled(sellOrder.getFilled() + quantity);

        double price = buyOrder.getType() == Order.Type.MARKET ? sellPrice
            : sellOrder.getType() == Order.Type.MARKET ? buyPrice : (buyPrice + sellPrice) / 2;

        Transaction transaction = new Transaction();
        transaction.setTicker(ticker);
        transaction.setSeller(sellOrder.getOwner());
        transaction.setBuyer(buyOrder.getOwner());
        transaction.setBuyOrderGuid(buyOrder.getGuid());
        transaction.setSellOrderGuid(sellOrder.getGuid());
        transaction.setQuantity(quantity);
        transaction.setPrice(price);

        executionProxy.execute(ticker, sellOrder.getOwner(), buyOrder.getOwner(), transaction);

        if (sharesLeftToBuy == 0) {
          break;
        }
      }
    }
  }

}
