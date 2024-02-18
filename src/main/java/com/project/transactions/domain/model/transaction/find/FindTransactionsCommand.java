package com.project.transactions.domain.model.transaction.find;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Find transactions command
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FindTransactionsCommand {
    private String name;
    private String status;
    private String fromDateTime;
    private String toDateTime;
}
