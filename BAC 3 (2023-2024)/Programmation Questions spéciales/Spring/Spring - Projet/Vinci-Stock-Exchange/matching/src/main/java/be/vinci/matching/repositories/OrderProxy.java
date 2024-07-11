package be.vinci.matching.repositories;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import be.vinci.matching.models.Order;

@Repository
@FeignClient(name = "orders")
public interface OrderProxy {

  @GetMapping("/order/open/by-ticker/{ticker}/{side}")
  List<Order> getAllOpen(@PathVariable String ticker, @PathVariable String side);

}
