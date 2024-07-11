package be.vinci.order.data;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Repository
@FeignClient(name = "matching")
public interface MatchingProxy {
    @PostMapping("/trigger/{ticker}")
    void triggerMatching(@PathVariable String ticker);
}
