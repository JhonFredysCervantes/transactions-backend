package com.project.transactions.domain.usecase.transaction.delete;

import com.project.transactions.domain.model.transaction.ID;
import com.project.transactions.domain.model.transaction.Status;
import com.project.transactions.domain.model.transaction.delete.DeleteTransactionCommand;
import com.project.transactions.domain.model.transaction.delete.IDeleteTransaction;
import com.project.transactions.domain.model.transaction.exception.TransactionCanNotBeDeleted;
import com.project.transactions.domain.model.transaction.gateway.ITransactionGateway;

/**
 * Delete transaction
 */
public class DeleteTransaction implements IDeleteTransaction {
    private final ITransactionGateway transactionGateway;

    /**
     * Constructor
     *
     * @param transactionGateway The transactions gateway
     */
    public DeleteTransaction(ITransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    @Override
    public Void execute(DeleteTransactionCommand deleteTransactionCommand) {

        var id = new ID(deleteTransactionCommand.getId());

        var transactionFound = transactionGateway.findById(id);

        if (transactionFound.isEmpty()) {
            return null;
        }

        var transaction = transactionFound.get();
        if (transaction.getStatus().equals(Status.PAID)) {
            throw new TransactionCanNotBeDeleted();
        }
        transactionGateway.deleteById(id);
        return null;
    }
}
