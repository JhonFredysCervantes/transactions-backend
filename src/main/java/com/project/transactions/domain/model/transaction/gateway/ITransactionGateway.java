package com.project.transactions.domain.model.transaction.gateway;

import com.project.transactions.domain.model.transaction.FilterTransaction;
import com.project.transactions.domain.model.transaction.ID;
import com.project.transactions.domain.model.transaction.Transaction;

import java.util.List;

/**
 * Transaction gateway
 */
public interface ITransactionGateway {

    /**
     * Persist transaction method
     *
     * @param transaction The transaction to persist
     * @return Transaction persisted (including ID)
     */
    Transaction persist(Transaction transaction);

    /**
     * Find transactions method
     *
     * @param filterTransaction Filter transaction
     * @return List of transactions
     */
    List<Transaction> find(FilterTransaction filterTransaction);

    /**
     * Find transactions by IDs
     *
     * @param ids The Ids of transactions to find
     * @return List of transactions
     */
    List<Transaction> finByIds(List<ID> ids);
}
