package com.project.transactions.infrastructure.entrypoints.rest.transaction.pay;

import com.project.transactions.domain.model.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Pay transactions response
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PayTransactionResponse implements Serializable {
    private String id;
    private String status;

    /**
     * Convert transaction to response
     *
     * @param transaction The transaction
     * @return FindTransactionsResponse
     */
    public static PayTransactionResponse toResponse(Transaction transaction) {
        return PayTransactionResponse.builder()
                .id(transaction.getId().getValue())
                .status(transaction.getStatus().name())
                .build();
    }
}
