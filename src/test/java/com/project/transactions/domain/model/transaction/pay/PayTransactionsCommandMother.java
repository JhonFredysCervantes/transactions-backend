package com.project.transactions.domain.model.transaction.pay;

import java.util.List;

public final class PayTransactionsCommandMother {

    public static PayTransactionsCommand validPayTransactionsCommand() {
        return PayTransactionsCommand.builder()
                .transactionIDs(List.of("UUID-01", "UUID-02"))
                .totalToPay(56000)
                .build();
    }

    public static PayTransactionsCommand emptyIdPayTransactionsCommand() {
        return PayTransactionsCommand.builder()
                .transactionIDs(List.of(""))
                .totalToPay(74000)
                .build();
    }

    public static PayTransactionsCommand invalidAmountPayTransactionsCommand() {
        return PayTransactionsCommand.builder()
                .transactionIDs(List.of("UUID-01"))
                .totalToPay(-5500)
                .build();
    }
}
