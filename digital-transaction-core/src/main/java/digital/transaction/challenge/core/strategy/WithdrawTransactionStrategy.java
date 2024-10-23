package digital.transaction.challenge.core.strategy;

import digital.transaction.challenge.domain.Transaction;
import digital.transaction.challenge.domain.enums.TransactionType;
import org.springframework.stereotype.Service;

@Service
public class WithdrawTransactionStrategy implements TransactionStrategy {
    @Override
    public void processTransaction(Transaction transaction) {
        System.out.println("Processing withdraw for account " + transaction.getAccountFrom());
    }

    @Override
    public TransactionType getType() {
        return TransactionType.WITHDRAW;
    }
}
