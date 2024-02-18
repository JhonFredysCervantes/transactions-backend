package com.project.transactions.domain.model.shared.value;

import lombok.Getter;

import java.io.Serializable;

/**
 * Long value object
 */
@Getter
public class LongValueObject implements Serializable {
    private final long value;

    /**
     * Constructor
     *
     * @param value Integer value
     */
    public LongValueObject(long value) {
        this.value = value;
    }

}
