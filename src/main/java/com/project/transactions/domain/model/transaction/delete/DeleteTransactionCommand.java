package com.project.transactions.domain.model.transaction.delete;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Delete transaction command
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeleteTransactionCommand {
    private String id;
}
