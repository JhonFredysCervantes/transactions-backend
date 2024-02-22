package com.project.transactions.domain.model.transaction.exception;

/**
 * Transaction can not be deleted
 */
public class TransactionCanNotBeDeleted extends IllegalOperationException {
    /**
     * Constructor
     */
    public TransactionCanNotBeDeleted() {
        super("Delete transaction paid");
    }
}
