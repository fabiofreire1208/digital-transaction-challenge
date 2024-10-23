package digital.transaction.challenge.messaging.service;

import digital.transaction.challenge.core.strategy.TransactionStrategy;
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

    @Value("${spring.kafka.topic.notify-ending}")
    private String notifySagaTopic;

    @Autowired
    public TransactionOrchestrator(TransactionStrategyFactory strategyFactory, TransactionOrchestratorProducer orchestratorProducer, JsonUtil jsonUtil) {
        this.strategyFactory = strategyFactory;
        this.orchestratorProducer = orchestratorProducer;
        this.jsonUtil = jsonUtil;
    }

    public void startSagaTransaction(Transaction transaction) {
        try{
            TransactionStrategy strategy = strategyFactory.getStrategy(transaction.getTransactionType())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid transaction type"));
            strategy.processTransaction(transaction);

            transaction.setStatus(TransactionStatus.CONFIRMED);
            transaction.setTransactionUpdateDate(LocalDateTime.now());
            orchestratorProducer.send(jsonUtil.toJson(transaction), notifySagaTopic);
        } catch (Exception exception) {
            transaction.setStatus(TransactionStatus.FAILED);
            transaction.setTransactionUpdateDate(LocalDateTime.now());
            orchestratorProducer.send(jsonUtil.toJson(transaction), notifySagaTopic);
        }
    }
}
