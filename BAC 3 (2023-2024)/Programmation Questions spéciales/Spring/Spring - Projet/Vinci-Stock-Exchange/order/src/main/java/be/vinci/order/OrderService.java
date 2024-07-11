package be.vinci.order;

import be.vinci.order.data.MatchingProxy;
import be.vinci.order.data.OrderRepository;
import be.vinci.order.model.Order;
import be.vinci.order.model.OrderSide;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final MatchingProxy matchingProxy;
    private final OrderRepository orderRepository;

    public OrderService(MatchingProxy matchingProxy, OrderRepository orderRepository) {
        this.matchingProxy = matchingProxy;
        this.orderRepository = orderRepository;
    }

    public void triggerMatching(String ticker) {
        matchingProxy.triggerMatching(ticker);
    }

    public Order readOrder(String guid) {
        return this.orderRepository.findById(guid).orElse(null);
    }

    // TODO : a enlever pas dans l'API
    public Iterable<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        order.setGuid(UUID.randomUUID().toString());
        order.setFilled(0);
        return this.orderRepository.save(order);
    }

    public Order updateOrder(String guid, int filled) {
        Order modifiedOrder;
        modifiedOrder = this.orderRepository.findById(guid).orElse(null);
        if (modifiedOrder == null) return null;
        modifiedOrder.setFilled(modifiedOrder.getFilled() + filled);
        this.orderRepository.save(modifiedOrder);
        return modifiedOrder;
    }

    public Iterable<Order> getOrdersByUser(String username) {
        return this.orderRepository.findAllByOwner(username);
    }

    public Iterable<Order> getOrdersByTickerAndSide(String ticker, OrderSide side) {
        return this.orderRepository.findAllByTickerAndSide(ticker, side);
    }
}

