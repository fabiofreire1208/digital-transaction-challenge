package digital.transaction.challenge.messaging.consumer;

import digital.transaction.challenge.core.utils.JsonUtil;
import digital.transaction.challenge.messaging.service.TransactionOrchestrator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TransactionOrchestratorConsumer {
    private final JsonUtil jsonUtil;
    private final TransactionOrchestrator orchestrator;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.start-saga}"
    )
    public void consumeStartSagaEvent(String payload) {
        log.info("Receiving event {} from start-saga topic", payload);
        var event = jsonUtil.toEvent(payload);
        orchestrator.startSagaTransaction(event);
    }
}
