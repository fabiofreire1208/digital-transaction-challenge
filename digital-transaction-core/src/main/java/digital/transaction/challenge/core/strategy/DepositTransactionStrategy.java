package digital.transaction.challenge.core.strategy;

import digital.transaction.challenge.domain.Transaction;
import digital.transaction.challenge.domain.enums.TransactionType;
import org.springframework.stereotype.Service;

@Service
public class DepositTransactionStrategy implements TransactionStrategy {
    @Override
    public void processTransaction(Transaction transaction) {
        System.out.println("Processing deposit for account " + transaction.getAccountTo());
    }

    @Override
    public TransactionType getType() {
        return TransactionType.DEPOSIT;
    }
}
