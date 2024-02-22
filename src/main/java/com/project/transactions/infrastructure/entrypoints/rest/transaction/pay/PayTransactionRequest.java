package com.project.transactions.infrastructure.entrypoints.rest.transaction.pay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Pay transactions request
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PayTransactionRequest implements Serializable {
    private List<String> ids;
    private long amount;
}
