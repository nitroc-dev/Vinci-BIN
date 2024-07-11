package be.vinci.wallet.repositories;

import be.vinci.wallet.models.Price;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name = "price")
public interface PricesProxy {

    @GetMapping("price/{ticker}")
    Double getPrice(@PathVariable String ticker);

}
