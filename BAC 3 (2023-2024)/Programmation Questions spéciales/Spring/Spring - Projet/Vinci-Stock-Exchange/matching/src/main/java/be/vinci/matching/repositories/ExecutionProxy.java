package be.vinci.matching.repositories;

import be.vinci.matching.models.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Repository
@FeignClient(name = "execution")
public interface ExecutionProxy {

  @PostMapping("/execute/{ticker}/{seller}/{buyer}")
  void execute(@PathVariable String ticker, @PathVariable String seller,
      @PathVariable String buyer, Transaction transaction);

}
