package com.project.transactions.domain.model.shared.exception;

import lombok.Getter;

/**
 * Transaction exception base
 */
@Getter
public class TransactionExceptionBase extends RuntimeException {
    private final Error error;

    /**
     * Constructor
     *
     * @param code        The exception code
     * @param name        The exception name
     * @param description The description about exception
     */
    public TransactionExceptionBase(String code, String name, String description) {
        super();
        this.error = new Error(code, name, description);
    }
}
