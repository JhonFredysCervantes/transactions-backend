package com.project.transactions.domain.model.transaction;

import com.project.transactions.domain.model.shared.value.DateTimeValueObject;

import java.time.ZonedDateTime;

/**
 * DateTimeZone
 */
public class DateTimeZone extends DateTimeValueObject implements Comparable<DateTimeZone> {

    /**
     * Constructor
     *
     * @param value DateTime value
     */
    public DateTimeZone(ZonedDateTime value) {
        super(value);
    }

    public DateTimeZone(String dateTime) {
        super(dateTime);
    }

    @Override
    public int compareTo(DateTimeZone dateTimeZone) {
        return dateTimeZone.getValue().compareTo(dateTimeZone.getValue());
    }
}
