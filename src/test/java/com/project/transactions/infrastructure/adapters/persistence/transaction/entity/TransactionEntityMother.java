package com.project.transactions.infrastructure.adapters.persistence.transaction.entity;


import java.sql.Timestamp;

public final class TransactionEntityMother {

    public static TransactionEntity validTransactionEntity() {
        return TransactionEntity.builder()
                .id("bfbe96d2-b8bf-4998-973b")
                .name("Transaction Test")
                .status("PENDING")
                .amount(13000)
                .createdAt(Timestamp.valueOf("2023-10-10 10:10:10.0"))
                .build();
    }

    public static TransactionEntity paidTransactionEntity() {
        return TransactionEntity.builder()
                .id("p-a-i-d")
                .name("Transaction Test")
                .status("PAID")
                .amount(18000)
                .createdAt(Timestamp.valueOf("2023-12-05 11:11:11.0"))
                .build();
    }

}