package digital.transaction.challenge.messaging.service;

import digital.transaction.challenge.core.business.TransactionStrategy;
import digital.transaction.challenge.core.persistence.TransactionHistoryRepositoryPort;
import digital.transaction.challenge.core.utils.JsonUtil;
import digital.transaction.challenge.domain.Transaction;
import digital.transaction.challenge.domain.enums.TransactionStatus;
import digital.transaction.challenge.messaging.producer.TransactionOrchestratorProducer;
import digital.transaction.challenge.core.strategy.TransactionStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionOrchestrator {
    private final TransactionStrategyFactory strategyFactory;
    private final TransactionOrchestratorProducer orchestratorProducer;
    private final JsonUtil jsonUtil;
    private final TransactionHistoryRepositoryPort transactionHistoryRepositoryPort;

    @Value("${spring.kafka.topic.notify-ending}")
    private String notifySagaTopic;

    @Autowired
    public TransactionOrchestrator(TransactionStrategyFactory strategyFactory, TransactionOrchestratorProducer orchestratorProducer, JsonUtil jsonUtil, TransactionHistoryRepositoryPort transactionHistoryRepositoryPort) {
        this.strategyFactory = strategyFactory;
        this.orchestratorProducer = orchestratorProducer;
        this.jsonUtil = jsonUtil;
        this.transactionHistoryRepositoryPort = transactionHistoryRepositoryPort;
    }

    public void startSagaTransaction(Transaction transaction) {
        try{
            TransactionStrategy strategy = strategyFactory.getStrategy(transaction.getTransactionType())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid transaction type"));
            strategy.processTransaction(transaction);

            transaction.setStatus(TransactionStatus.CONFIRMED);
            transaction.setTransactionUpdateDate(LocalDateTime.now());
        } catch (Exception exception) {
            transaction.setStatus(TransactionStatus.FAILED);
            transaction.setTransactionUpdateDate(LocalDateTime.now());
        } finally {
            transactionHistoryRepositoryPort.save(transaction);
            orchestratorProducer.send(jsonUtil.toJson(transaction), notifySagaTopic);
        }
    }
}
