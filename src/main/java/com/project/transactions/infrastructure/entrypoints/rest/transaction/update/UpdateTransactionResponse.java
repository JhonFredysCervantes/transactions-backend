package com.project.transactions.infrastructure.entrypoints.rest.transaction.update;

import com.project.transactions.domain.model.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Update transaction response
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateTransactionResponse {
    private String id;
    private String name;
    private long amount;
    private String status;
    private String createdAt;

    /**
     * Create update transaction response from transaction
     *
     * @param transaction Transaction
     * @return Update transaction response
     */
    public static UpdateTransactionResponse from(Transaction transaction) {
        return new UpdateTransactionResponse(
                transaction.getId().getValue(),
                transaction.getName().getValue(),
                transaction.getAmount().getValue(),
                transaction.getStatus().name(),
                transaction.getCreatedAt().getValue().toString()
        );
    }
}
