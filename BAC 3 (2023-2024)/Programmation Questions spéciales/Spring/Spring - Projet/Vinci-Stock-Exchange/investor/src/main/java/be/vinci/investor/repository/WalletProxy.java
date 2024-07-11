package be.vinci.investor.repository;

import be.vinci.investor.models.Position;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
@FeignClient(name = "Wallet")
public interface WalletProxy {

    @PostMapping("/wallet/{username}")
    List<Position> addPositions(@PathVariable String username, @RequestBody List<Position> positions);

    @GetMapping("/wallet/{username}")
    List<Position> getOpenPositions(@PathVariable String username);
}
