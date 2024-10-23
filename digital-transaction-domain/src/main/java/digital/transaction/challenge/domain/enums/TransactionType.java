package digital.transaction.challenge.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionType {
    DEPOSIT("deposit"),
    WITHDRAW("withdraw"),
    PURCHASE("purchase"),
    CANCELLATION("cancellation"),
    REVERSAL("reversal");
    private final String type;
}
