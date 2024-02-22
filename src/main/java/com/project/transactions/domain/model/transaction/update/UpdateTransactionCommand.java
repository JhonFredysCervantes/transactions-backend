package com.project.transactions.domain.model.transaction.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Update transaction command
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateTransactionCommand {
    private String id;
    private String name;
    private long amount;
}
