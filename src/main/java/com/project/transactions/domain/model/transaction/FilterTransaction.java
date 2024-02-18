package com.project.transactions.domain.model.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Filter transaction
 */
@AllArgsConstructor
@Builder
@Getter
public class FilterTransaction {
    private Name name;
    private Status status;
    private DateTimeZone fromDate;
    private DateTimeZone toDate;
}
