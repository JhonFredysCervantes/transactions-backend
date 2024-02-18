package com.project.transactions.domain.model.transaction.exception;

import com.project.transactions.domain.model.shared.exception.TransactionExceptionBase;

/**
 * Transaction name can not be null or empty exception
 */
public class TransactionNameCanNotBeNullOrEmptyException extends TransactionExceptionBase {
    /**
     * Constructor
     */
    public TransactionNameCanNotBeNullOrEmptyException() {
        super("TRAN-IA-001", "TransactionNameCanNotBeNullOrEmptyException", "The transaction name can not be null or empty");
    }
}
