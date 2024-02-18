package com.project.transactions.infrastructure.entrypoints.rest.transaction.find;

import com.project.transactions.domain.model.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FindTransactionsResponse implements Serializable {
    private String id;
    private String name;
    private long amount;
    private String status;
    private String createdAt;

    /**
     * Convert transaction to response
     *
     * @param transaction The transaction
     * @return FindTransactionsResponse
     */
    public static FindTransactionsResponse toResponse(Transaction transaction) {
        return FindTransactionsResponse.builder()
                .id(transaction.getId().getValue())
                .name(transaction.getName().getValue())
                .amount(transaction.getAmount().getValue())
                .status(transaction.getStatus().name())
                .createdAt(transaction.getCreatedAt().getValue().toString())
                .build();
    }
}
