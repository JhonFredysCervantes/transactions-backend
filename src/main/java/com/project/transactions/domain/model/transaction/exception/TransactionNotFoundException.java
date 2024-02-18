package com.project.transactions.domain.model.transaction.exception;

import com.project.transactions.domain.model.shared.exception.TransactionExceptionBase;
import com.project.transactions.domain.model.transaction.ID;

import java.util.List;
import java.util.stream.Collectors;

import static com.project.transactions.domain.model.shared.Constants.COMMA;

/**
 * Transaction not found exception
 */
public class TransactionNotFoundException extends TransactionExceptionBase {
    /**
     * Constructor
     *
     * @param ids The ids searched
     */
    public TransactionNotFoundException(List<ID> ids) {
        super("TRAN-NF-001", "TransactionNotFoundException",
                String.format("Transaction/s with ID/s <%s> was not found.", ids.parallelStream()
                        .map(ID::getValue).collect(Collectors.joining(COMMA))));
    }
}
