package digital.transaction.challenge.core.strategy;

import digital.transaction.challenge.core.business.DepositTransactionPort;
import digital.transaction.challenge.core.business.TransactionStrategy;
import digital.transaction.challenge.core.command.Context;
import digital.transaction.challenge.domain.Transaction;
import digital.transaction.challenge.domain.enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositTransactionStrategy implements TransactionStrategy {
    private final DepositTransactionPort depositTransactionPort;

    @Autowired
    public DepositTransactionStrategy(DepositTransactionPort depositTransactionPort) {
        this.depositTransactionPort = depositTransactionPort;
    }

    @Override
    public void processTransaction(Transaction transaction) {
        System.out.println("Processing deposit for account " + transaction.getAccountTo());
        final Context context = new Context();
        context.setData(transaction);
        depositTransactionPort.process(context);
    }

    @Override
    public TransactionType getType() {
        return TransactionType.DEPOSIT;
    }
}
