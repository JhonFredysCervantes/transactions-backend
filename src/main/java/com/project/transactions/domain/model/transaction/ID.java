package com.project.transactions.domain.model.transaction;

import com.project.transactions.domain.model.shared.value.StringValueObjet;
import com.project.transactions.domain.model.transaction.exception.TransactionIdCanNotBeNullOrEmptyException;

/**
 * Identification of transaction
 */
public class ID extends StringValueObjet {
    /**
     * Constructor
     *
     * @param value String value
     */
    public ID(String value) {
        super(value);

        if (value == null || value.isBlank()) {
            throw new TransactionIdCanNotBeNullOrEmptyException();
        }
    }
}
