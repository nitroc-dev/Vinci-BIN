package be.vinci.order.data;

import be.vinci.order.model.Order;
import be.vinci.order.model.OrderSide;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {

    Iterable<Order> findAllByOwner(String owner);

    @Query("SELECT o FROM orders o WHERE o.ticker = :ticker AND o.side = :side AND o.filled < o.quantity")
    Iterable<Order> findAllByTickerAndSide(String ticker, OrderSide side);
}
