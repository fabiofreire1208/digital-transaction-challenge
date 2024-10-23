package digital.transaction.challenge.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionStatus {
    CONFIRMED("confirmed"),
    PENDING("pending"),
    FAILED("failed");
    private final String status;
}
