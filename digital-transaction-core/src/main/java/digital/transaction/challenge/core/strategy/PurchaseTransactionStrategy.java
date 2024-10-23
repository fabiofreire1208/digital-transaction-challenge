package digital.transaction.challenge.core.strategy;

import digital.transaction.challenge.core.business.TransactionStrategy;
import digital.transaction.challenge.domain.Transaction;
import digital.transaction.challenge.domain.enums.TransactionType;
import org.springframework.stereotype.Service;

@Service
public class PurchaseTransactionStrategy implements TransactionStrategy {
    @Override
    public void processTransaction(Transaction transaction) {

    }

    @Override
    public TransactionType getType() {
        return TransactionType.PURCHASE;
    }
}
