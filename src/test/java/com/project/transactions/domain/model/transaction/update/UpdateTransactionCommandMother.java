package com.project.transactions.domain.model.transaction.update;

public final class UpdateTransactionCommandMother {

    public static UpdateTransactionCommand buildValidUpdateTransactionCommand() {
        return new UpdateTransactionCommand(
                "f07b3c28-8dad-4e05-8d1e",
                "Market purchase",
                100
        );
    }

}
