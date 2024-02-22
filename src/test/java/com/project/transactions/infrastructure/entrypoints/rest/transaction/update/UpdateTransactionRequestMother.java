package com.project.transactions.infrastructure.entrypoints.rest.transaction.update;


import com.project.transactions.infrastructure.adapters.persistence.transaction.entity.TransactionEntityMother;

public final class UpdateTransactionRequestMother {

    public static UpdateTransactionRequest validUpdateTransactionRequest() {
        var transaction = TransactionEntityMother.validTransactionEntity();
        return new UpdateTransactionRequest(transaction.getId(), "Name Updated", transaction.getAmount() + 100);
    }
}