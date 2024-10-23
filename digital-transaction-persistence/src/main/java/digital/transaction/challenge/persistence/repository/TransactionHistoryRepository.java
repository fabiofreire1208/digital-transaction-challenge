package digital.transaction.challenge.persistence.repository;

import digital.transaction.challenge.persistence.model.TransactionHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistoryEntity, UUID> {

}
