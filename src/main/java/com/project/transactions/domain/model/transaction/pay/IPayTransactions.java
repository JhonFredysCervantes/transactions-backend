package com.project.transactions.domain.model.transaction.pay;

import com.project.transactions.domain.model.transaction.Transaction;

import java.util.List;

/**
 * Pay transactions interface
 */
public interface IPayTransactions {

    /**
     * Execute the use case
     *
     * @param payTransactionsCommand The pay transactions command
     * @return The list of transactions
     */
    List<Transaction> execute(PayTransactionsCommand payTransactionsCommand);
}
