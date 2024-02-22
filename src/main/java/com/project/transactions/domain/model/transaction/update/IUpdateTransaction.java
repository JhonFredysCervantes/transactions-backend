package com.project.transactions.domain.model.transaction.update;

import com.project.transactions.domain.model.transaction.Transaction;

/**
 * Transactions update interface
 */
public interface IUpdateTransaction {

    /**
     * Execute use case
     *
     * @param updateTransactionCommand The update transaction command
     * @return A transaction
     */
    Transaction execute(UpdateTransactionCommand updateTransactionCommand);
}
