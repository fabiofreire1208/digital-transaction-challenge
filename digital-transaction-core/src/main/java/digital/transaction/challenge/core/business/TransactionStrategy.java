package digital.transaction.challenge.core.business;

import digital.transaction.challenge.domain.Transaction;
import digital.transaction.challenge.domain.enums.TransactionType;

public interface TransactionStrategy {
    void processTransaction(Transaction transaction);
    TransactionType getType();
}
