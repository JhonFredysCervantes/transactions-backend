package com.project.transactions.domain.model.transaction.find;

import com.project.transactions.domain.model.transaction.Transaction;

import java.util.List;

/**
 * Find transactions interface
 */
public interface IFindTransactions {

    /**
     * Execute the use case
     *
     * @param findTransactionsCommand The find transactions command
     * @return The list of transactions
     */
    List<Transaction> execute(FindTransactionsCommand findTransactionsCommand);
}
