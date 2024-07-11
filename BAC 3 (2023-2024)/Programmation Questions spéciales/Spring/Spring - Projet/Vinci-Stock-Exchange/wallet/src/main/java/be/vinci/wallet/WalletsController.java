package be.vinci.wallet;

import be.vinci.wallet.models.Position;
import be.vinci.wallet.execptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WalletsController {

    private final WalletsService walletsService;

    public WalletsController(WalletsService walletsService) {
        this.walletsService = walletsService;
    }

    /**
     * Get the net worth of a user
     * @param username the username of the user
     * @return the net worth of the user
     *     200 OK  if the user exists
     *     404 if the user is not found
     */
    @GetMapping("/wallet/{username}/net-worth")
    public ResponseEntity<Double> getNetWorth(@PathVariable String username) {
        try {
            double netWorth = walletsService.calculateNetWorth(username);
            return ResponseEntity.ok(netWorth);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get the open positions of a user
     * @param username the username of the user
     * @return the open positions of the user
     *     200 OK if the user exists
     *     404 if the user is not found
     */
    @GetMapping("/wallet/{username}")
    public ResponseEntity<List<Position>> getOpenPositions(@PathVariable String username) {
        try {
            List<Position> openPositions = walletsService.getOpenPositions(username);
            return ResponseEntity.ok(openPositions);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add positions to a user
     * @param username the username of the user
     * @param positions the positions to add
     * @return the updated positions of the user
     *     200 OK if the user exists
     *     404 if the user is not found
     */
    @PostMapping("/wallet/{username}")
    public ResponseEntity<List<Position>> addPositions(
            @PathVariable String username,
            @RequestBody List<Position> positions) throws NotFoundException {
        List<Position> updatedPositions = walletsService.addPositions(username, positions);
        return ResponseEntity.ok(updatedPositions);
    }

}

