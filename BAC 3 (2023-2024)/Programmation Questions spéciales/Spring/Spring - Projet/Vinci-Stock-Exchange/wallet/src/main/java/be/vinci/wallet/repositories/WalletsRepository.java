package be.vinci.wallet.repositories;


import be.vinci.wallet.models.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WalletsRepository extends CrudRepository<Position, String> {

    Iterable<Position> findByUsername(String username);

    Position findByUsernameAndTicker(String username, String ticker);


}