package com.project.transactions.domain.model.transaction;

import com.project.transactions.domain.model.shared.value.StringValueObjet;
import com.project.transactions.domain.model.transaction.exception.TransactionNameCanNotBeNullOrEmptyException;

/**
 * Transaction name
 */
public class Name extends StringValueObjet {

    /**
     * Constructor
     *
     * @param name Transaction name
     */
    public Name(String name) {
        super(name);

        if (name == null || name.isBlank()) {
            throw new TransactionNameCanNotBeNullOrEmptyException();
        }
    }
}
