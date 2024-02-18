package com.project.transactions.domain.usecase.transaction.find;

import com.project.transactions.domain.model.transaction.DateTimeZone;
import com.project.transactions.domain.model.transaction.FilterTransaction;
import com.project.transactions.domain.model.transaction.Name;
import com.project.transactions.domain.model.transaction.Status;
import com.project.transactions.domain.model.transaction.Transaction;
import com.project.transactions.domain.model.transaction.find.FindTransactionsCommand;
import com.project.transactions.domain.model.transaction.find.IFindTransactions;
import com.project.transactions.domain.model.transaction.gateway.ITransactionGateway;

import java.util.List;

import static java.util.Objects.isNull;

/**
 * Find transactions use case
 */
public class FindTransactions implements IFindTransactions {
    private final ITransactionGateway transactionGateway;

    /**
     * Constructor
     *
     * @param transactionGateway Transaction gateway
     */
    public FindTransactions(ITransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    @Override
    public List<Transaction> execute(FindTransactionsCommand findTransactionsCommand) {

        Name name = null;
        if (!isNull(findTransactionsCommand.getName())) {
            name = new Name(findTransactionsCommand.getName());
        }

        Status status = null;
        if (!isNull(findTransactionsCommand.getStatus())) {
            status = Status.valueOf(findTransactionsCommand.getStatus());
        }

        DateTimeZone fromDateTime = null;
        DateTimeZone toDateTime = null;
        if (!isNull(findTransactionsCommand.getFromDateTime())) {
            fromDateTime = new DateTimeZone(findTransactionsCommand.getFromDateTime());
            toDateTime = new DateTimeZone(findTransactionsCommand.getToDateTime());
        }

        var filterTransaction = FilterTransaction.builder()
                .name(name)
                .status(status)
                .fromDate(fromDateTime)
                .toDate(toDateTime)
                .build();

        return transactionGateway.find(filterTransaction);
    }
}
