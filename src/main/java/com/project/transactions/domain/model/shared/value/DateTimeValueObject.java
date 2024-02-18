package com.project.transactions.domain.model.shared.value;

import lombok.Getter;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * DateTime value object
 */
@Getter
public abstract class DateTimeValueObject implements Serializable {
    private final ZonedDateTime value;

    /**
     * Constructor
     *
     * @param value DateTime value
     */
    protected DateTimeValueObject(ZonedDateTime value) {
        this.value = value;
    }

    /**
     * Constructor
     *
     * @param value String value
     */
    protected DateTimeValueObject(String value) {
        this.value = ZonedDateTime.parse(value);
    }
}
