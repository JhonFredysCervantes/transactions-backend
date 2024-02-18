package com.project.transactions.domain.model.transaction;

import com.project.transactions.domain.model.shared.value.LongValueObject;
import com.project.transactions.domain.model.transaction.exception.AmountValueCanNotBeLessThanZero;

/**
 * Amount
 */
public class Amount extends LongValueObject {
    /**
     * Constructor
     *
     * @param value Integer value
     */
    public Amount(long value) {
        super(value);

        if (value < 0) {
            throw new AmountValueCanNotBeLessThanZero(value);
        }
    }
}
