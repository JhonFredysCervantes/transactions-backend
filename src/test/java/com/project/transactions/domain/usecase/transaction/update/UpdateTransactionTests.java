package com.project.transactions.domain.usecase.transaction.update;

import com.project.transactions.domain.model.transaction.ID;
import com.project.transactions.domain.model.transaction.Transaction;
import com.project.transactions.domain.model.transaction.TransactionMother;
import com.project.transactions.domain.model.transaction.exception.TransactionNotFoundException;
import com.project.transactions.domain.model.transaction.gateway.ITransactionGateway;
import com.project.transactions.domain.model.transaction.update.IUpdateTransaction;
import com.project.transactions.domain.model.transaction.update.UpdateTransactionCommandMother;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UpdateTransactionTests {

    @Mock
    private ITransactionGateway transactionGateway;
    private final IUpdateTransaction updateTransaction;

    UpdateTransactionTests() {
        openMocks(this);
        this.updateTransaction = new UpdateTransaction(transactionGateway);
    }

    @Test
    void Update_Transaction_Successfully() {
        var updateCommand = UpdateTransactionCommandMother.buildValidUpdateTransactionCommand();
        var transaction = TransactionMother.buildValidTransaction();

        when(transactionGateway.findById(any(ID.class)))
                .thenReturn(Optional.of(transaction));
        when(transactionGateway.update(any(Transaction.class)))
                .thenReturn(transaction);

        updateTransaction.execute(updateCommand);

        verify(transactionGateway).findById(any(ID.class));
        verify(transactionGateway).update(any(Transaction.class));

    }

    @Test
    void Throw_Exception_When_Transaction_Was_Not_Found() {
        var updateCommand = UpdateTransactionCommandMother.buildValidUpdateTransactionCommand();

        when(transactionGateway.findById(any(ID.class)))
                .thenReturn(Optional.empty());

        var exception = assertThrows(TransactionNotFoundException.class,
                () -> updateTransaction.execute(updateCommand));
        var error = exception.getError();

        assertThat(error.getCode()).isEqualTo("TRAN-NF-001");
        assertThat(error.getName()).isEqualTo(TransactionNotFoundException.class.getSimpleName());

        verify(transactionGateway).findById(any(ID.class));
    }

}