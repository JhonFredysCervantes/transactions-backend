package com.project.transactions.infrastructure.entrypoints.rest.transaction.pay;


import com.project.transactions.infrastructure.adapters.persistence.transaction.entity.TransactionEntityMother;

import java.util.List;

public final class PayTransactionRequestMother {
    public static PayTransactionRequest validPayTransactionRequest() {
        var transaction = TransactionEntityMother.validTransactionEntity();
        return new PayTransactionRequest(List.of(transaction.getId()), transaction.getAmount());
    }

    public static PayTransactionRequest payTransactionRequestWithNegativeAmount() {
        return new PayTransactionRequest(List.of("bfbe96d2-b8bf-4998-973a"), -30000);
    }

}