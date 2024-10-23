package digital.transaction.challenge.core.strategy;

import digital.transaction.challenge.domain.enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TransactionStrategyFactory {

    private final Map<TransactionType, TransactionStrategy> strategies;

    public TransactionStrategyFactory(List<TransactionStrategy> strategies) {
        this.strategies = strategies.stream()
                .collect(Collectors
                        .toMap(TransactionStrategy::getType, strategy -> strategy));
    }

    public Optional<TransactionStrategy> getStrategy(TransactionType type){
        return Optional.ofNullable(strategies.get(type));
    }
}
