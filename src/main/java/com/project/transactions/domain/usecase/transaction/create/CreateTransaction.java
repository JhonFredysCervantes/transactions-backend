package com.project.transactions.domain.usecase.transaction.create;

import com.project.transactions.domain.model.transaction.Amount;
import com.project.transactions.domain.model.transaction.ID;
import com.project.transactions.domain.model.transaction.Name;
import com.project.transactions.domain.model.transaction.Status;
import com.project.transactions.domain.model.transaction.Transaction;
import com.project.transactions.domain.model.transaction.create.CreateTransactionCommand;
import com.project.transactions.domain.model.transaction.create.ICreateTransaction;
import com.project.transactions.domain.model.transaction.gateway.ITransactionGateway;

import java.util.UUID;

/**
 * Create transaction use case
 */
public class CreateTransaction implements ICreateTransaction {
    private final ITransactionGateway transactionGateway;

    /**
     * Constructor
     *
     * @param transactionGateway Transaction gateway
     */
    public CreateTransaction(ITransactionGateway transactionGateway) {
        this.transactionGateway = transactionGateway;
    }

    @Override
    public ID execute(CreateTransactionCommand createTransactionCommand) {
        var name = new Name(createTransactionCommand.getName());
        var amount = new Amount(createTransactionCommand.getAmount());
        var id = new ID(UUID.randomUUID().toString());
        var transaction = Transaction.build(id, name, Status.PENDING, amount);

        var result = transactionGateway.persist(transaction);

        return result.getId();
    }
}
