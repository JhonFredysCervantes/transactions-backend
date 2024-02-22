package com.project.transactions.infrastructure.entrypoints.rest.transaction.create;

public final class CreateTransactionRequestMother {

    public static CreateTransactionRequest validCreateTransactionRequest() {
        return new CreateTransactionRequest("Test Transaction", 17000);
    }

    public static CreateTransactionRequest emptyNameCreateTransactionRequest() {
        return new CreateTransactionRequest("", 15000);
    }

    public static CreateTransactionRequest negativeAmountCreateTransactionRequest() {
        return new CreateTransactionRequest("Test Transaction", -1700);
    }

}