package digital.transaction.challenge.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import digital.transaction.challenge.domain.Transaction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception ex) {
            return "";
        }
    }

    public Transaction toEvent(String json) {
        try {
            return objectMapper.readValue(json, Transaction.class);
        } catch (Exception ex) {
            log.error("Error object deserealization");
            return null;
        }
    }
}
