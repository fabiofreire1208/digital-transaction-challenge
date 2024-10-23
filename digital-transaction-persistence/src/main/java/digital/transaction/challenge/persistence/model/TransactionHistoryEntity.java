package digital.transaction.challenge.persistence.model;

import digital.transaction.challenge.domain.enums.TransactionStatus;
import digital.transaction.challenge.domain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions_history")
@Getter
@Setter
public class TransactionHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;

    @Column(name = "account_from_id")
    private UUID accountFrom;

    @Column(name = "account_to_id")
    private UUID accountTo;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status")
    private TransactionStatus status;

    private LocalDateTime transactionCreationDate = LocalDateTime.now();

    private LocalDateTime transactionUpdateDate;
}
