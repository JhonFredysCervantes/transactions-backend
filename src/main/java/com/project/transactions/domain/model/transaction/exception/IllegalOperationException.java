package com.project.transactions.domain.model.transaction.exception;

import com.project.transactions.domain.model.shared.exception.TransactionExceptionBase;

/**
 * Illegal operation exception
 */
public class IllegalOperationException extends TransactionExceptionBase {
    /**
     * Constructor
     *
     * @param operation The operation name
     */
    public IllegalOperationException(String operation) {
        super("TRANS-IO-001", IllegalOperationException.class.getSimpleName(), String.format("Illegal operation: %s", operation));
    }
}
