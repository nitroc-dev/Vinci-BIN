package be.vinci.execution.data;

import be.vinci.execution.model.PositionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
@FeignClient("wallet")
public interface WalletProxy {
    @PostMapping("/wallet/{username}")
    PositionDTO updateWallet(@PathVariable("username") String username, @RequestBody PositionDTO position);
}

