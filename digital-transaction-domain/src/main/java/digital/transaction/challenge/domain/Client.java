package digital.transaction.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
    private UUID id;
    private String name;
    private String document;
    private String email;
    private String phone;
}
