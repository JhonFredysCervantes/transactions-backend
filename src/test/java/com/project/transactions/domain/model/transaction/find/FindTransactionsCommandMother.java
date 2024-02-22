package com.project.transactions.domain.model.transaction.find;

public final class FindTransactionsCommandMother {

    public static FindTransactionsCommand buildValidFindTransactionsCommand() {
        return new FindTransactionsCommand(
                "Transaction 1",
                "PENDING",
                "2023-12-03T23:59:59-05:00[America/Bogota]",
                "2023-12-03T23:59:59-05:00[America/Bogota]"
        );
    }
}
