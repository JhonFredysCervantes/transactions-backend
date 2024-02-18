package com.project.transactions.domain.model.transaction.create;

import com.project.transactions.domain.model.transaction.ID;

/**
 * Create transaction interface
 */
public interface ICreateTransaction {

    /**
     * Execute the use case
     *
     * @param createTransactionCommand The create transaction command
     * @return The transaction ID
     */
    ID execute(CreateTransactionCommand createTransactionCommand);
}
