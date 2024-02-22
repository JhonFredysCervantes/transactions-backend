package com.project.transactions.domain.model.transaction.delete;

/**
 * Delete transaction interface
 */
public interface IDeleteTransaction {

    /**
     * Execute de use case
     *
     * @param deleteTransactionCommand The delete transaction command
     * @return Null or Void instance
     */
    Void execute(DeleteTransactionCommand deleteTransactionCommand);
}
