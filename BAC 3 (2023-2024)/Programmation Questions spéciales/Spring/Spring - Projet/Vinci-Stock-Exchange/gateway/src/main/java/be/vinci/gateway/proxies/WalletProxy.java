package be.vinci.gateway.proxies;

import be.vinci.gateway.models.Position;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
@FeignClient(name = "wallet")
public interface WalletProxy {

    @GetMapping("/wallet/{username}")
    List<Position> getWalletByUsername(@PathVariable String username);

    @GetMapping("/wallet/{username}/net-worth")
    Double getNetWorth(@PathVariable String username);

    @PostMapping("/wallet/{username}")
    List<Position> addOrRemoveToWallet(@PathVariable String username, @RequestBody List<Position> positions);
}
