package com.project.transactions.domain.usecase.transaction.update;

import com.project.transactions.domain.model.transaction.Amount;
import com.project.transactions.domain.model.transaction.ID;
import com.project.transactions.domain.model.transaction.Name;
import com.project.transactions.domain.model.transaction.Transaction;
import com.project.transactions.domain.model.transaction.exception.TransactionNotFoundException;
import com.project.transactions.domain.model.transaction.gateway.ITransactionGateway;
import com.project.transactions.domain.model.transaction.update.IUpdateTransaction;
import com.project.transactions.domain.model.transaction.update.UpdateTransactionCommand;

import java.util.List;

/**
 * Update transaction
 */
public class UpdateTransaction implements IUpdateTransaction {
    private final ITransactionGateway transactionGateway;

    /**
     * Constructor
     *
     * @param transactionGateway The transaction gateway
     */
    public UpdateTransaction(ITransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    @Override
    public Transaction execute(UpdateTransactionCommand updateTransactionCommand) {

        var id = new ID(updateTransactionCommand.getId());

        var transactionFound = transactionGateway.findById(id);

        if (transactionFound.isEmpty()) {
            throw new TransactionNotFoundException(List.of(id));
        }

        var transaction = transactionFound.get();

        var name = new Name(updateTransactionCommand.getName());
        var amount = new Amount(updateTransactionCommand.getAmount());
        var status = transaction.getStatus();
        var createAt = transaction.getCreatedAt();

        var transactionToUpdate = Transaction.load(id, name, amount, status, createAt);

        return transactionGateway.update(transactionToUpdate);
    }
}
