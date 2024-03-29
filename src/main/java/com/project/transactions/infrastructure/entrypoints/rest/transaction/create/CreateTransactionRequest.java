package com.project.transactions.infrastructure.entrypoints.rest.transaction.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Create transaction request
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateTransactionRequest implements Serializable {
    private String name;
    private long amount;
}
