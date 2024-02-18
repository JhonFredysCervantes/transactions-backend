package com.project.transactions.infrastructure.adapters.persistence.transaction.entity;

import com.project.transactions.domain.model.transaction.Amount;
import com.project.transactions.domain.model.transaction.DateTimeZone;
import com.project.transactions.domain.model.transaction.ID;
import com.project.transactions.domain.model.transaction.Name;
import com.project.transactions.domain.model.transaction.Status;
import com.project.transactions.domain.model.transaction.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZoneId;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransactionEntity implements Serializable {
    @Id
    private String id;
    private String name;
    private String status;
    private long amount;
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp createdAt;

    /**
     * Method that converts a transaction model/domain to a transaction entity
     *
     * @param transaction The transaction model instance
     * @return A transaction entity
     */
    public static TransactionEntity toEntity(Transaction transaction) {
        return TransactionEntity.builder()
                .id(transaction.getId().getValue())
                .name(transaction.getName().getValue())
                .status(transaction.getStatus().name())
                .amount(transaction.getAmount().getValue())
                .createdAt(Timestamp.from(transaction.getCreatedAt().getValue().toInstant()))
                .build();
    }

    /**
     * Method that converts a transaction entity to a transaction model
     *
     * @param transactionEntity The transaction entity
     * @return A transaction model
     */
    public static Transaction toModel(TransactionEntity transactionEntity) {
        var id = new ID(transactionEntity.getId());
        var name = new Name(transactionEntity.getName());
        var status = Status.valueOf(transactionEntity.getStatus());
        var amount = new Amount(transactionEntity.getAmount());
        var createdAt = new DateTimeZone(transactionEntity.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()));

        return Transaction.load(id, name, amount, status, createdAt);
    }
}
