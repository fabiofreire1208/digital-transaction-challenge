package digital.transaction.challenge.persistence.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"digital.transaction.challenge.persistence"})
@EntityScan(basePackages = {"digital.transaction.challenge.persistence.model"})
@EnableJpaRepositories(basePackages = {"digital.transaction.challenge.persistence"})
@EnableTransactionManagement
public class PersistenceConfiguration {
}
