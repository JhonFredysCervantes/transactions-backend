package com.project.transactions.domain.model.transaction.create;

public final class CreateTransactionCommandMother {

    public static CreateTransactionCommand validCreateTransactionCommand() {
        return CreateTransactionCommand.builder()
                .name("Coffee without sugar")
                .amount(3500)
                .build();
    }

    public static CreateTransactionCommand createTransactionCommandWithEmptyName() {
        return CreateTransactionCommand.builder()
                .name("")
                .amount(3500)
                .build();
    }

    public static CreateTransactionCommand createTransactionCommandWithInvalidAmount() {
        return CreateTransactionCommand.builder()
                .name("Coffee without sugar")
                .amount(-2000)
                .build();
    }
}
