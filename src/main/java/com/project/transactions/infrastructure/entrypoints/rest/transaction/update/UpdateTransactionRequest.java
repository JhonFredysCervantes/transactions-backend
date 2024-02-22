package com.project.transactions.infrastructure.entrypoints.rest.transaction.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Update transaction request
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateTransactionRequest {
    private String id;
    private String name;
    private long amount;
}
