package com.project.transactions.domain.model.transaction.exception;

import com.project.transactions.domain.model.shared.exception.TransactionExceptionBase;

/**
 * Amount value can not be less than zero exception
 */
public class AmountValueCanNotBeLessThanZero extends TransactionExceptionBase {
    /**
     * Constructor
     *
     * @param amount The amount value
     */
    public AmountValueCanNotBeLessThanZero(long amount) {
        super("TRAN-IA-002", "AmountValueCanNotBeLessThanZero", String.format("The amount value can not be less than zero. Value: %d", amount));
    }
}
