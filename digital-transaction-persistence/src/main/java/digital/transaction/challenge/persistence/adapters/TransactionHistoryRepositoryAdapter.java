package digital.transaction.challenge.persistence.adapters;

import digital.transaction.challenge.core.persistence.TransactionHistoryRepositoryPort;
import digital.transaction.challenge.domain.Transaction;
import digital.transaction.challenge.persistence.mappers.PersistenceEntityMapper;
import digital.transaction.challenge.persistence.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class TransactionHistoryRepositoryAdapter implements TransactionHistoryRepositoryPort {
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final PersistenceEntityMapper mapper;

    @Autowired
    public TransactionHistoryRepositoryAdapter(TransactionHistoryRepository transactionHistoryRepository, PersistenceEntityMapper mapper) {
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.mapper = mapper;
    }


    @Override
    public Transaction save(Transaction obj) {
        return mapper.from(transactionHistoryRepository.save(mapper.from(obj)));
    }

    @Override
    public Optional<Transaction> get(UUID id) {
        return Optional.empty();
    }

}
