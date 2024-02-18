package com.project.transactions.infrastructure.adapters.persistence.transaction;

import com.project.transactions.infrastructure.adapters.persistence.transaction.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Transaction repository interface
 */
@Repository
public interface ITransactionRepository extends JpaRepository<TransactionEntity, String>,
        JpaSpecificationExecutor<TransactionEntity> {
}
