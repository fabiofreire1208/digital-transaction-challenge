package digital.transaction.challenge.messaging.producer;

import digital.transaction.challenge.core.messaging.TransactionOrchestratorProducerPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TransactionOrchestratorProducer implements TransactionOrchestratorProducerPort {
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Override
    public void send(String transaction, String topic) {
        try {
            log.info("Sending event to topic {} with data {}", topic, transaction);
            kafkaTemplate.send(topic, transaction);
        } catch (Exception ex) {
            log.error("Error trying to send data to topic {} with data {}", topic, transaction, ex);
        }
    }
}
