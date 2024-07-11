package be.vinci.price;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, String> {
    Optional<Price> findByTicker(String ticker);
}


