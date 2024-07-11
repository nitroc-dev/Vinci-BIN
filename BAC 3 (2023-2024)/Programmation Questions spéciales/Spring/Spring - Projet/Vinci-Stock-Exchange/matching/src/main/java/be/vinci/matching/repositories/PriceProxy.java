package be.vinci.matching.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name = "price")
public interface PriceProxy {

  @GetMapping("/price/{ticker}")
  String _getLatestPrice(@PathVariable String ticker);

  default double getLatestPrice(String ticker) {
    return Double.parseDouble(_getLatestPrice(ticker));
  }

}
