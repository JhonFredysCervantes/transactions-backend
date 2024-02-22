package com.project.transactions.infrastructure.adapters.persistence.transaction;

import com.project.transactions.domain.model.transaction.FilterTransaction;
import com.project.transactions.domain.model.transaction.ID;
import com.project.transactions.domain.model.transaction.Transaction;
import com.project.transactions.domain.model.transaction.gateway.ITransactionGateway;
import com.project.transactions.infrastructure.adapters.persistence.transaction.entity.TransactionEntity;
import com.project.transactions.infrastructure.adapters.persistence.transaction.query.FilterByNameStatusAndDate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Transaction gateway implementation
 */
@Service
public class TransactionGatewayImp implements ITransactionGateway {
    private final ITransactionRepository transactionRepository;

    /**
     * Constructor
     *
     * @param transactionRepository The transaction repository
     */
    public TransactionGatewayImp(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction persist(Transaction transaction) {
        var transactionEntity = TransactionEntity.toEntity(transaction);
        var infoPersisted = transactionRepository.save(transactionEntity);
        return TransactionEntity.toModel(infoPersisted);
    }

    @Override
    public List<Transaction> find(FilterTransaction filterTransaction) {
        var transactions = transactionRepository.findAll(new FilterByNameStatusAndDate(filterTransaction));
        return transactions.parallelStream()
                .map(TransactionEntity::toModel)
                .toList();
    }

    @Override
    public Optional<Transaction> findById(ID id) {
        var transaction = transactionRepository.findById(id.getValue());
        return transaction.map(TransactionEntity::toModel);
    }

    @Override
    public List<Transaction> finByIds(List<ID> ids) {
        var idsInString = ids.parallelStream()
                .map(ID::getValue)
                .toList();
        var transactions = transactionRepository.findAllById(idsInString);
        return transactions.parallelStream()
                .map(TransactionEntity::toModel)
                .toList();
    }

    @Override
    public Transaction update(Transaction transaction) {
        var transactionUpdated = transactionRepository.save(TransactionEntity.toEntity(transaction));
        return TransactionEntity.toModel(transactionUpdated);
    }

    @Override
    public Void deleteById(ID id) {
        transactionRepository.deleteById(id.getValue());
        return null;
    }
}
