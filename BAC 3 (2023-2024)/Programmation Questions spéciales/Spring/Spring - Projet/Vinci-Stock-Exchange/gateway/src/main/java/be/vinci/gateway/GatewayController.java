package be.vinci.gateway;

import be.vinci.gateway.exceptions.BadRequestException;
import be.vinci.gateway.exceptions.ConflictException;
import be.vinci.gateway.exceptions.NotFoundException;
import be.vinci.gateway.exceptions.UnauthorizedException;
import be.vinci.gateway.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GatewayController {

    private final GatewayService service;

    public GatewayController(GatewayService service) {
        this.service = service;
    }

    /**
     * Connect an investor with his credentials
     * @param credentials the credentials of the investor
     * @return a token
     *  - 200 OK if the credentials are correct
     *  - 400 BAD REQUEST if the credentials are not correct
     *  - 401 UNAUTHORIZED if the credentials are not correct
     */
    @PostMapping("/authentication/connect")
    public ResponseEntity<String> connect(@RequestBody Credentials credentials) {
        try {
            String token = service.connect(credentials);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (UnauthorizedException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Verify a token
     * @param username the username of the investor
     * @param credentials the credentials of the investor
     * @return the username of the investor
     *  - 200 OK if the token is valid
     *  - 400 BAD REQUEST if the credentials are not correct
     *  - 404 NOT FOUND if the investor is not found
     */
    @PutMapping("/authentication/{username}")
    public ResponseEntity<Void> updateCredentials(@PathVariable String username, @RequestBody Credentials credentials) {
        if (!username.equals(credentials.getUsername())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            service.updateCredentials(username, credentials);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get an investor by his username
     * @param username the username of the investor
     * @return the investor
     * - 200 OK if the investor is found
     * - 400 BAD REQUEST if the username is not valid
     * - 404 NOT FOUND if the investor is not found
     */
    @GetMapping("/investor/{username}")
    public ResponseEntity<InvestorData> getInvestorByUsername(@PathVariable String username, @RequestHeader("Authorization") String token) {
        String usernameToken = service.verify(token);
        if (usernameToken == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if (!username.equals(usernameToken)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        InvestorData investor = service.getInvestorByUsername(username);

        if (investor == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(investor, HttpStatus.OK);
    }

    /**
     * Create an investor
     * @param username the username of the investor
     * @param investor the data of the investor
     * @return nothing
     * - 201 CREATED if the investor is created
     * - 400 BAD REQUEST if the username is not valid
     * - 409 CONFLICT if the investor already exists
     */
    @PostMapping("/investor/{username}")
    public ResponseEntity<Void> createInvestor(@PathVariable String username, @RequestBody InvestorWithPassword investor) {
        if (!username.equals(investor.getInvestorData().getUsername())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            service.createInvestor(username, investor);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ConflictException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update an investor
     * @param username the username of the investor
     * @param investor the data of the investor
     * @return nothing
     * - 200 OK if the investor is updated
     * - 400 BAD REQUEST if the username is not valid
     * - 404 NOT FOUND if the investor is not found
     */
    @PutMapping("/investor/{username}")
    public ResponseEntity<Void> updateInvestor(@PathVariable String username, @RequestBody InvestorData investor) {
        if (!username.equals(investor.getUsername())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            service.updateInvestor(username, investor);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete an investor
     * @param username the username of the investor
     * @return nothing
     * - 200 OK if the investor is deleted
     * - 400 BAD REQUEST if the username is not valid
     * - 404 NOT FOUND if the investor is not found
     */
    @DeleteMapping("/investor/{username}")
    public ResponseEntity<Void> deleteInvestor(@PathVariable String username) {
        try {
            service.deleteInvestor(username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Pass an order
     * @param order the order to create
     * @return the created order
     * - 200 CREATED if the order is created
     * - 400 BAD REQUEST if the order is not valid
     */
    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        try {
            Order createdOrder = service.createOrder(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get orders by username
     * @param username the username of the investor
     * @param token the token of the investor
     * @return the orders of the investor
     * - 200 OK if the orders are found
     * - 401 UNAUTHORIZED if the token is not valid
     * - 404 NOT FOUND if the orders are not found
     */
    @GetMapping("/order/by-user/{username}")
    public ResponseEntity<Iterable<Order>> getOrdersByUsername(@PathVariable String username, @RequestHeader("Authorization") String token) {
        String usernameToken = service.verify(token);
        if (usernameToken == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if (!username.equals(usernameToken)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        try {
            Iterable<Order> orders = service.getOrdersByUsername(username);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get wallet by username
     * @param username the username of the investor
     * @param token the token of the investor
     * @return the wallet of the investor
     * - 200 OK if the wallet is found
     * - 401 UNAUTHORIZED if the token is not valid
     * - 404 NOT FOUND if the wallet is not found
     */
    @GetMapping("/wallet/{username}")
    public ResponseEntity<Iterable<Position>> getWalletByUsername(@PathVariable String username, @RequestHeader("Authorization") String token) {
        String usernameToken = service.verify(token);
        if (usernameToken == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        try {
            Iterable<Position> positions = service.getWalletByUsername(username);
            return new ResponseEntity<>(positions, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add or remove cash to wallet
     * @param username the username of the investor
     * @param cash the cash to add or remove
     * @param token the token of the investor
     * @return the wallet of the investor
     * - 200 OK if the cash is added or removed
     * - 401 UNAUTHORIZED if the token is not valid
     * - 404 NOT FOUND if the wallet is not found
     */
    @PostMapping("/wallet/{username}/cash")
    public ResponseEntity<List<Position>> addOrRemoveCashToWallet(@PathVariable String username, @RequestBody Cash cash, @RequestHeader("Authorization") String token) {
        String usernameToken = service.verify(token);
        if (usernameToken == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        try {
            List<Position> positions = service.addOrRemoveCashToWallet(username, cash);
            return new ResponseEntity<>(positions, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get net worth by username
     * @param username the username of the investor
     * @param token the token of the investor
     * @return the net worth of the investor
     * - 200 OK if the net worth is found
     * - 401 UNAUTHORIZED if the token is not valid
     * - 404 NOT FOUND if the net worth is not found
     */
    @GetMapping("/wallet/{username}/net-worth")
    public ResponseEntity<Double> getNetWorth(@PathVariable String username, @RequestHeader("Authorization") String token) {
        String usernameToken = service.verify(token);
        if (usernameToken == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        try {
            Double netWorth = service.getNetWorth(username);
            return new ResponseEntity<>(netWorth, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add or remove position to wallet
     * @param username the username of the investor
     * @param ticker the ticker of the position
     * @param token the token of the investor
     * @return the wallet of the investor
     * - 200 OK if the position is added or removed
     * - 401 UNAUTHORIZED if the token is not valid
     * - 404 NOT FOUND if the wallet is not found
     */
    @PostMapping("/wallet/{username}/position/{ticker}")
    public ResponseEntity<List<Position>> addOrRemovePositionToWallet(@PathVariable String username, @PathVariable String ticker, @RequestBody Quantity quantity, @RequestHeader("Authorization") String token) {
        String usernameToken = service.verify(token);
        if (usernameToken == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Position position = new Position(username, ticker, quantity.getQuantity(), 1);

        try {
            List<Position> positions = service.addOrRemoveToWallet(username, position);
            return new ResponseEntity<>(positions, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
