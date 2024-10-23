package digital.transaction.challenge.core.messaging;

public interface TransactionOrchestratorProducerPort {
    void send(final String transaction, String topic);
}
