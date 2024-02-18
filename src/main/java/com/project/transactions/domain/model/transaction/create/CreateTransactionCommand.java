package com.project.transactions.domain.model.transaction.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Create transaction command
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateTransactionCommand {
    private String name;
    private long amount;
}
