package digital.transaction.challenge.core.business;

import digital.transaction.challenge.core.command.Command;
import digital.transaction.challenge.domain.Transaction;

import java.util.Optional;

public interface DepositTransactionPort extends Command<Optional<Transaction>> {
}
