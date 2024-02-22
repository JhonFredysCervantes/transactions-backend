package com.project.transactions.domain.model.transaction;

public final class FilterTransactionMother {

    public static FilterTransaction buildValidFilterTransaction() {
        return FilterTransaction.builder()
                .name(new Name("Transaction 1"))
                .status(Status.PENDING)
                .fromDate(new DateTimeZone("2023-12-03T23:59:59-05:00[America/Bogota]"))
                .toDate(new DateTimeZone("2023-12-03T23:59:59-05:00[America/Bogota]"))
                .build();
    }
}
