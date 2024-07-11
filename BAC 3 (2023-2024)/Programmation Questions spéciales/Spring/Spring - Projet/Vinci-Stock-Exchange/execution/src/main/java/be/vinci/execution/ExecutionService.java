package be.vinci.execution;

import be.vinci.execution.data.OrderProxy;
import be.vinci.execution.data.PriceProxy;
import be.vinci.execution.data.WalletProxy;
import be.vinci.execution.model.PatchOrderDTO;
import be.vinci.execution.model.PositionDTO;
import be.vinci.execution.model.Transaction;
import feign.FeignException;
import org.springframework.stereotype.Service;

@Service
public class ExecutionService {
    private final OrderProxy orderProxy;
    private final PriceProxy priceProxy;
    private final WalletProxy walletProxy;

    public ExecutionService(OrderProxy orderProxy, PriceProxy priceProxy, WalletProxy walletProxy) {
        this.orderProxy = orderProxy;
        this.priceProxy = priceProxy;
        this.walletProxy = walletProxy;
    }

    public boolean executeTransaction(Transaction transaction) {
        return executeWalletExchange(transaction)
                && updatePriceValue(transaction.getTicker(), transaction.getPrice())
                && updateOrderFilledValue(transaction);
    }

    private boolean executeWalletExchange(Transaction transaction) {
        double unitPrice = transaction.getPrice() / transaction.getQuantity();
        try {
            // seller
            walletProxy.updateWallet(transaction.getSeller(), new PositionDTO(transaction.getTicker(), transaction.getQuantity(), -unitPrice));
            walletProxy.updateWallet(transaction.getSeller(), new PositionDTO("CASH", transaction.getPrice(), 1));
            // buyer
            walletProxy.updateWallet(transaction.getBuyer(), new PositionDTO(transaction.getTicker(), transaction.getQuantity(), unitPrice));
            walletProxy.updateWallet(transaction.getBuyer(), new PositionDTO("CASH", -transaction.getPrice(), 1));
        } catch (FeignException e) {
            return false;
        }
        return true;
    }

    private boolean updatePriceValue(String ticker, double newPrice) {
        try {
            priceProxy.updatePrice(ticker, newPrice);
        } catch (FeignException e) {
            return false;
        }
        return true;
    }

    private boolean updateOrderFilledValue(Transaction transaction) {
        try {
            orderProxy.updateOrderFilled(transaction.getSellOrderGuid(), new PatchOrderDTO(transaction.getQuantity()));
            orderProxy.updateOrderFilled(transaction.getBuyOrderGuid(), new PatchOrderDTO(transaction.getQuantity()));
        } catch (FeignException e) {
            return false;
        }
        return true;
    }
}
