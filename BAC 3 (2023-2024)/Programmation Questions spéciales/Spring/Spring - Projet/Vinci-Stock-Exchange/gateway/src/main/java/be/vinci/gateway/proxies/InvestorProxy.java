package be.vinci.gateway.proxies;

import be.vinci.gateway.models.InvestorData;
import be.vinci.gateway.models.InvestorWithPassword;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Repository
@FeignClient(name = "investor")
public interface InvestorProxy {

    @GetMapping("/investor/{username}")
    InvestorData getInvestor(@PathVariable String username);

    @PostMapping("/investor/{username}")
    void createInvestor(@PathVariable String username, InvestorWithPassword investor);

    @PutMapping("/investor/{username}")
    void updateInvestor(@PathVariable String username, InvestorData investor);

    @DeleteMapping("/investor/{username}")
    void deleteInvestor(@PathVariable String username);
}
