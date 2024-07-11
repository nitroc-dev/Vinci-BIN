package be.vinci.gateway;

import be.vinci.gateway.exceptions.BadRequestException;
import be.vinci.gateway.exceptions.ConflictException;
import be.vinci.gateway.exceptions.NotFoundException;
import be.vinci.gateway.exceptions.UnauthorizedException;
import be.vinci.gateway.models.*;
import be.vinci.gateway.proxies.AuthenticationProxy;
import be.vinci.gateway.proxies.InvestorProxy;
import be.vinci.gateway.proxies.OrderProxy;
import be.vinci.gateway.proxies.WalletProxy;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatewayService {

    private final AuthenticationProxy authenticationProxy;
    private final InvestorProxy investorProxy;
    private final OrderProxy orderProxy;
    private final WalletProxy walletProxy;

    public GatewayService(AuthenticationProxy authenticationProxy, InvestorProxy investorProxy, OrderProxy orderProxy, WalletProxy walletProxy) {
        this.authenticationProxy = authenticationProxy;
        this.investorProxy = investorProxy;
        this.orderProxy = orderProxy;
        this.walletProxy = walletProxy;
    }

    /**
     * Verify the token and return the username if the token is valid
     * @param token the token to verify
     * @return the username if the token is valid, null otherwise
     */
    public String verify(String token) {
        try {
            return authenticationProxy.verify(token);
        } catch (FeignException e) {
            if (e.status() == 401) return null;
            else throw e;
        }
    }

    /**
     * Connect the user and return the token if the credentials are valid
     * @param credentials the credentials to connect
     * @return the token if the credentials are valid, null otherwise
     * @throws BadRequestException if the credentials are not valid
     * @throws UnauthorizedException if the credentials are not valid
     */
    public String connect(Credentials credentials) throws BadRequestException, UnauthorizedException {
        try {
            return authenticationProxy.connect(credentials);
        } catch (FeignException e) {
            if (e.status() == 400) throw new BadRequestException();
            else if (e.status() == 401) throw new UnauthorizedException();
            else throw e;
        }
    }

    /**
     * Update the credentials of the user
     * @param username the username of the user
     * @param credentials the new credentials
     * @throws NotFoundException if the user is not found
     * @throws BadRequestException if the credentials are not valid
     */
    public void updateCredentials(String username, Credentials credentials) throws NotFoundException, BadRequestException {
        InvestorData previousInvestor;
        try {
            previousInvestor = investorProxy.getInvestor(username);
        } catch (FeignException e) {
            if (e.status() == 404) throw new NotFoundException();
            else throw e;
        }

        try {
            authenticationProxy.updateCredentials(username, credentials);
        } catch (FeignException e) {
            try {
                investorProxy.updateInvestor(username, previousInvestor);
            } catch (FeignException ignored) {}
            if (e.status() == 400) throw new BadRequestException();
            else if (e.status() == 404) throw new NotFoundException();
            else throw e;
        }
    }


    /**
     * Get the investor by username
     * @param username the username of the investor
     * @return the investor if found, null otherwise
     */
    public InvestorData getInvestorByUsername(String username) {
        try {
            return investorProxy.getInvestor(username);
        } catch (FeignException e) {
            if (e.status() == 404) return null;
            else throw e;
        }
    }

    /**
     * Create an investor
     * @param username the username of the investor
     * @param investor the investor to create
     * @throws ConflictException if the investor already exists
     */
    public void createInvestor(String username, InvestorWithPassword investor) throws ConflictException, BadRequestException {
        try {
            investorProxy.createInvestor(username, investor);
        } catch (FeignException e) {
            if (e.status() == 409) throw new ConflictException();
            else if (e.status() == 400) throw new BadRequestException();
            else throw e;
        }
    }

    /**
     * Update an investor
     * @param username the username of the investor
     * @param investor the investor to update
     * @throws BadRequestException if the investor is not valid
     * @throws NotFoundException if the investor is not found
     */
    public void updateInvestor(String username, InvestorData investor) throws BadRequestException, NotFoundException {
        try {
            investorProxy.updateInvestor(username, investor);
        } catch (FeignException e) {
            if (e.status() == 400) throw new BadRequestException();
            else if (e.status() == 404) throw new NotFoundException();
            else throw e;
        }
    }

    /**
     * Delete an investor
     * @param username the username of the investor
     * @throws BadRequestException if the investor is not valid
     * @throws NotFoundException if the investor is not found
     */
    public void deleteInvestor(String username) throws BadRequestException, NotFoundException {
        try {
            investorProxy.deleteInvestor(username);
        } catch (FeignException e) {
            if (e.status() == 400) throw new BadRequestException();
            else if (e.status() == 404) throw new NotFoundException();
            else throw e;
        }
    }

    /**
     * Create an order
     * @param order the order to create
     * @return the created order
     * @throws BadRequestException if the order is not valid
     */
    public Order createOrder(Order order) throws BadRequestException {
        InvestorData investor = getInvestorByUsername(order.getOwner());
        if (investor == null) throw new BadRequestException();

        try {
            return orderProxy.createOrder(order);
        } catch (FeignException e) {
            if (e.status() == 400) throw new BadRequestException();
            else throw e;
        }
    }

    /**
     * Get an order by username
     * @param username the username of the investor
     * @return the list of orders
     * @throws NotFoundException if the investor is not found
     */
    public List<Order> getOrdersByUsername(String username) throws NotFoundException {
        try {
            return orderProxy.getOrdersByUsername(username);
        } catch (FeignException e) {
            if (e.status() == 404) throw new NotFoundException();
            else throw e;
        }
    }

    /**
     * Get the wallet by username
     * @param username the username of the investor
     * @return the list of positions
     * @throws NotFoundException if the investor is not found
     */
    public List<Position> getWalletByUsername(String username) throws NotFoundException {
        try {
            return walletProxy.getWalletByUsername(username);
        } catch (FeignException e) {
            if (e.status() == 404) throw new NotFoundException();
            else throw e;
        }
    }

    /**
     * Add or remove cash to the wallet
     * @param username the username of the investor
     * @param cash the cash to add or remove
     * @return the list of positions
     * @throws NotFoundException if the investor is not found
     */
    public List<Position> addOrRemoveCashToWallet(String username, Cash cash) throws NotFoundException {
        try {
            return walletProxy.addOrRemoveToWallet(username, List.of(new Position(username, "CASH", cash.getAmount(), 1)));
        } catch (FeignException e) {
            if (e.status() == 404) throw new NotFoundException();
            else throw e;
        }
    }

    /**
     * Get the net worth of the investor
     * @param username the username of the investor
     * @return the net worth of the investor
     * @throws NotFoundException if the investor is not found
     */
    public Double getNetWorth(String username) throws NotFoundException {
        try {
            return walletProxy.getNetWorth(username);
        } catch (FeignException e) {
            if (e.status() == 404) throw new NotFoundException();
            else throw e;
        }
    }

    /**
     * Add or remove positions to the wallet
     * @param username the username of the investor
     * @param position the position to add or remove
     * @return the list of positions
     * @throws NotFoundException if the investor is not found
     */
    public List<Position> addOrRemoveToWallet(String username, Position position) throws NotFoundException {
        List<Position> positions = List.of(position);
        try {
            return walletProxy.addOrRemoveToWallet(username, positions);
        } catch (FeignException e) {
            if (e.status() == 404) throw new NotFoundException();
            else throw e;
        }
    }
}
