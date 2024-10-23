package digital.transaction.challenge.processor.adapters;

import digital.transaction.challenge.core.business.CreateTransactionHistoryPort;
import digital.transaction.challenge.core.command.Context;
import digital.transaction.challenge.core.persistence.TransactionHistoryRepositoryPort;
import digital.transaction.challenge.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateTransactionHistoryAdapter implements CreateTransactionHistoryPort {

    private final TransactionHistoryRepositoryPort transactionHistoryRepositoryPort;


    @Autowired
    public CreateTransactionHistoryAdapter(TransactionHistoryRepositoryPort transactionHistoryRepositoryPort) {
        this.transactionHistoryRepositoryPort = transactionHistoryRepositoryPort;
    }

    @Override
    public Optional<Transaction> process(Context context) {
        final Transaction data = context.getData(Transaction.class);
        return Optional.ofNullable(transactionHistoryRepositoryPort.save(data));
    }
}
