package be.vinci.execution;

import be.vinci.execution.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/execute")
public class ExecutionController {
    private final ExecutionService executionService;

    public ExecutionController(ExecutionService executionService) {
        this.executionService = executionService;
    }

    @PostMapping("/{ticker}/{seller}/{buyer}")
    public ResponseEntity<Void> executeTransaction(@PathVariable String ticker, @PathVariable String seller, @PathVariable String buyer, @RequestBody Transaction transaction) {
        if (!transaction.getTicker().equals(ticker) || !transaction.getSeller().equals(seller) || !transaction.getBuyer().equals(buyer) || transaction.invalid()) {
            return ResponseEntity.badRequest().build();
        }
        if (!executionService.executeTransaction(transaction)) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }
}
