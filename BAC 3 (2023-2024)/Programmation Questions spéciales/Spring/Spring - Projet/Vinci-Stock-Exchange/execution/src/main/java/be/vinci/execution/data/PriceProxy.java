package be.vinci.execution.data;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
@FeignClient("price")
public interface PriceProxy {

    @PatchMapping("/price/{ticker}")
    Double updatePrice(@PathVariable String ticker, @RequestBody Double price);
}
