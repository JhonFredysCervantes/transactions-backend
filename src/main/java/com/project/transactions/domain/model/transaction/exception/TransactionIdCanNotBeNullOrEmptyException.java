package com.project.transactions.domain.model.transaction.exception;

import com.project.transactions.domain.model.shared.exception.TransactionExceptionBase;

/**
 * Transaction ID can not be null or empty exception
 */
public class TransactionIdCanNotBeNullOrEmptyException extends TransactionExceptionBase {
    /**
     * Constructor
     */
    public TransactionIdCanNotBeNullOrEmptyException() {
        super("TRAN-IA-003", "TransactionIdCanNotBeNullOrEmptyException", "The transaction ID can not be null or empty");
    }
}
