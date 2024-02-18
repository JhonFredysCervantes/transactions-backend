package com.project.transactions.domain.usecase.transaction.pay;

import com.project.transactions.domain.model.transaction.ID;
import com.project.transactions.domain.model.transaction.Transaction;
import com.project.transactions.domain.model.transaction.exception.AmountValueCanNotBeLessThanZero;
import com.project.transactions.domain.model.transaction.exception.TransactionNotFoundException;
import com.project.transactions.domain.model.transaction.gateway.ITransactionGateway;
import com.project.transactions.domain.model.transaction.pay.IPayTransactions;
import com.project.transactions.domain.model.transaction.pay.PayTransactionsCommand;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.project.transactions.domain.model.shared.Constants.ZERO;

/**
 * Pay transaction use case
 */
public class PayTransactions implements IPayTransactions {
    private final ITransactionGateway transactionGateway;

    /**
     * Constructor
     *
     * @param transactionGateway The transaction gateway
     */
    public PayTransactions(ITransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    @Override
    public List<Transaction> execute(PayTransactionsCommand payTransactionsCommand) {

        var ids = payTransactionsCommand.getTransactionIDs()
                .parallelStream()
                .map(ID::new)
                .toList();

        var total = payTransactionsCommand.getTotalToPay();

        if (total < ZERO) {
            throw new AmountValueCanNotBeLessThanZero(total);
        }

        var transactionsFound = transactionGateway.finByIds(ids);

        if (transactionsFound.isEmpty()) {
            throw new TransactionNotFoundException(ids);
        }

        var currentAtomicAmount = new AtomicLong(total);

        return transactionsFound.stream()
                .sorted(Comparator.comparing(Transaction::getCreatedAt).reversed())
                .peek(transaction -> {
                    if (transaction.getAmount().getValue() <= currentAtomicAmount.get()) {
                        transaction.pay();
                        currentAtomicAmount.addAndGet(-transaction.getAmount().getValue());
                        transactionGateway.persist(transaction);
                    }
                }).toList();
    }
}
