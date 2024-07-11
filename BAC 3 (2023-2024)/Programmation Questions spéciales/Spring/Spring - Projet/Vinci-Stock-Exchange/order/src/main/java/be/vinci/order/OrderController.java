package be.vinci.order;

import be.vinci.order.model.Order;
import be.vinci.order.model.OrderSide;
import be.vinci.order.model.PatchOrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // TODO : a enlever pas dans l'API
    @GetMapping("/order")
    public ResponseEntity<Iterable<Order>> getAllOrders() {
        Iterable<Order> ordersList = orderService.getAllOrders();
        if (ordersList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ordersList);
    }

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        if (order.invalid()) return ResponseEntity.badRequest().build();
        Order newOrder = orderService.createOrder(order);
        if (newOrder == null) {
            return ResponseEntity.internalServerError().build();
        }

//        orderService.triggerMatching(order.getTicker());
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    @GetMapping("/order/{guid}")
    public ResponseEntity<Order> getOrder(@PathVariable String guid) {
        Order order = orderService.readOrder(guid);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PatchMapping("/order/{guid}")
    public ResponseEntity<Void> updateOrder(@PathVariable String guid, @RequestBody PatchOrderDTO orderDTO) {
        System.out.println("OrderController.updateOrder");
        Order modfiedOrder = orderService.updateOrder(guid, orderDTO.getFilled());
        if (modfiedOrder == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/order/by-user/{username}")
    public ResponseEntity<Iterable<Order>> getOrdersByUser(@PathVariable String username) {
        Iterable<Order> ordersList = orderService.getOrdersByUser(username);
        if (ordersList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ordersList);
    }

    @GetMapping("/order/open/by-ticker/{ticker}/{side}")
    public ResponseEntity<Iterable<Order>> getOrderByTicker(@PathVariable String ticker, @PathVariable OrderSide side) {
        Iterable<Order> ordersList = orderService.getOrdersByTickerAndSide(ticker, side);
        return ResponseEntity.ok(ordersList);
    }
}
