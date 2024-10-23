package digital.transaction.challenge.core.persistence;

import digital.transaction.challenge.domain.Transaction;

import java.util.Optional;
import java.util.UUID;

public interface TransactionHistoryRepositoryPort {

    Transaction save(final Transaction obj);

    Optional<Transaction> get(final UUID id);
}
