package digital.transaction.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    private UUID id;
    private String accountNumber;
    private Client client;
    private Double balance;
}
