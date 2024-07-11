package be.vinci.wallet;

import be.vinci.wallet.models.Position;
import be.vinci.wallet.repositories.PricesProxy;
import be.vinci.wallet.repositories.WalletsRepository;
import be.vinci.wallet.execptions.NotFoundException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ComponentScan(basePackages = "be.vinci.wallet.PricesProxy")
public class WalletsService {

    private final WalletsRepository repository;
    private final PricesProxy pricesProxy;

    public WalletsService(WalletsRepository repository, PricesProxy pricesProxy) {
        this.repository = repository;
        this.pricesProxy = pricesProxy;
    }

    /**
     * Get the net worth of a user
     *
     * @param username the username of the user
     * @return the net worth of the user
     * @throws NotFoundException if the user is not found
     */
    public double calculateNetWorth(String username) throws NotFoundException {
        List<Position> allPositions = (List<Position>) repository.findByUsername(username);

        if (allPositions == null || allPositions.isEmpty()) {
            throw new NotFoundException("l'investisseur n'a pas pu être trouvé");
        }

        double netWorth = 0.0;
        for (Position position : allPositions) {
            netWorth += position.getQuantity() * pricesProxy.getPrice(position.getTicker());
        }
        return netWorth;
    }

    /**
     * Get the open positions of a user
     *
     * @param username the username of the user
     * @return the open positions of the user
     * @throws NotFoundException if the user is not found
     */
    public List<Position> getOpenPositions(String username) throws NotFoundException {
        List<Position> allPositions = (List<Position>) repository.findByUsername(username);

        if (allPositions == null || allPositions.isEmpty()) {
            throw new NotFoundException("l'investisseur n'a pas pu être trouvé");
        }

        List<Position> openPositions = allPositions.stream()
                .filter(position -> position.getQuantity() > 0)
                .collect(Collectors.toList());

        return openPositions;
    }

    /**
     * Add positions to a user
     *
     * @param username  the username of the user
     * @param positions the positions to add
     * @return the updated positions of the user
     * @throws NotFoundException if the user is not found
     */
    public List<Position> addPositions(String username, List<Position> positions) throws NotFoundException {
        if (repository.findByUsername(username) == null ) {
            throw new NotFoundException("l'investisseur n'a pas pu être trouvé");
        }

        for (Position position : positions) {
            Position existingPosition = repository.findByUsernameAndTicker(username, position.getTicker());
            if (existingPosition != null) {
                String username1 = existingPosition.getUsername();
                String ticker = existingPosition.getTicker();
                repository.delete(existingPosition);

                Position newPosition = new Position();
                newPosition.setUsername(username1);
                newPosition.setTicker(ticker);
                newPosition.setQuantity(existingPosition.getQuantity() + position.getQuantity());
                newPosition.setUnitValue(position.getUnitValue());

                repository.save(newPosition);

            } else {
                Position newPosition = new Position();
                newPosition.setUsername(username);
                newPosition.setTicker(position.getTicker());
                newPosition.setQuantity(position.getQuantity());
                newPosition.setUnitValue(position.getUnitValue());

                repository.save(newPosition);
            }
        }
        return getOpenPositions(username);
    }
}

