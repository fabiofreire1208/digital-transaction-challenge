package digital.transaction.challenge.processor.adapters;

import digital.transaction.challenge.core.business.DepositTransactionPort;
import digital.transaction.challenge.core.command.Context;
import digital.transaction.challenge.domain.Transaction;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Predicate;

@Service
public class DepositTransactionAdapter implements DepositTransactionPort {
    @Override
    public Optional<Transaction> process(Context context) {
        final Transaction transaction = context.getData(Transaction.class);
        Predicate<Transaction> hasSufficientBalance = tx -> tx.getAccountFrom().getBalance().compareTo(tx.getAmount()) >= 0;
        Predicate<Transaction> isSameAccounts = tx -> tx.getAccountFrom().equals(tx.getAccountTo());

        if (isSameAccounts.test(transaction)) {
            throw new IllegalArgumentException("Account destination should be different from account sender.");
        }

        if (!hasSufficientBalance.test(transaction)) {
            throw new IllegalArgumentException("Account does not have enough balance.");
        }


        return Optional.of(deposit(transaction));
    }

    private Transaction deposit(Transaction transaction) {
        Double balanceFrom = transaction.getAccountFrom().getBalance() - transaction.getAmount();
        Double balanceTo = transaction.getAccountTo().getBalance() + transaction.getAmount();

        transaction.getAccountFrom().setBalance(balanceFrom);
        transaction.getAccountTo().setBalance(balanceTo);

        return transaction;
    }
}
