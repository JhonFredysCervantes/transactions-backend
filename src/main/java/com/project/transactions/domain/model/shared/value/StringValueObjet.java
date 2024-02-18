package com.project.transactions.domain.model.shared.value;

import lombok.Getter;

import java.io.Serializable;

/**
 * String value object
 */
@Getter
public abstract class StringValueObjet implements Serializable {

    private final String value;

    /**
     * Constructor
     *
     * @param value String value
     */
    protected StringValueObjet(String value) {
        this.value = value;
    }

}
