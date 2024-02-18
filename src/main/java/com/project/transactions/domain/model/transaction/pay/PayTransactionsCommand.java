package com.project.transactions.domain.model.transaction.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Pay transactions command
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PayTransactionsCommand {
    private List<String> transactionIDs;
    private long totalToPay;
}
