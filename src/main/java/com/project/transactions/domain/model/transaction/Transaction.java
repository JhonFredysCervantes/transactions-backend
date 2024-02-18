package com.project.transactions.domain.model.transaction;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

/**
 * Transaction model
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Transaction {
    private ID id;
    private Name name;
    private Amount amount;
    private Status status;
    private DateTimeZone createdAt;

    /**
     * Build transaction
     *
     * @param id     The id of the transaction
     * @param name   The name of the transaction
     * @param status The status of the transaction
     * @param amount The amount of the transaction
     * @return Transaction
     */
    public static Transaction build(ID id, Name name, Status status, Amount amount) {
        return Transaction.builder()
                .id(id)
                .name(name)
                .amount(amount)
                .status(status)
                .createdAt(new DateTimeZone(ZonedDateTime.now()))
                .build();
    }

    /**
     * Load transaction
     *
     * @param id        The id of the transaction
     * @param name      The name of the transaction
     * @param amount    The amount of the transaction
     * @param status    The status of the transaction
     * @param createdAt The date and time of the transaction
     * @return Transaction
     */
    public static Transaction load(ID id, Name name, Amount amount, Status status, DateTimeZone createdAt) {
        return Transaction.builder()
                .id(id)
                .name(name)
                .amount(amount)
                .status(status)
                .createdAt(createdAt)
                .build();
    }

    /**
     * Pay transaction method
     */
    public void pay() {
        this.status = Status.PAID;
    }
}
