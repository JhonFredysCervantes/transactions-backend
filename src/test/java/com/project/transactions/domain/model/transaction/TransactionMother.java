package com.project.transactions.domain.model.transaction;


public final class TransactionMother {

    public static Transaction buildValidTransaction() {
        return Transaction.load(
                new ID("1"),
                new Name("Transaction 1"),
                new Amount(100),
                Status.PENDING,
                new DateTimeZone("2023-12-03T23:59:59-05:00[America/Bogota]")
        );
    }

    public static Transaction buildPaidTransaction() {
        return Transaction.load(
                new ID("2"),
                new Name("Transaction 2"),
                new Amount(500),
                Status.PAID,
                new DateTimeZone("2023-12-22T23:59:59-05:00[America/Bogota]")
        );
    }
}
