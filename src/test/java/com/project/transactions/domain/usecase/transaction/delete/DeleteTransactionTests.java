package com.project.transactions.domain.usecase.transaction.delete;

import com.project.transactions.domain.model.transaction.ID;
import com.project.transactions.domain.model.transaction.TransactionMother;
import com.project.transactions.domain.model.transaction.delete.DeleteTransactionCommandMother;
import com.project.transactions.domain.model.transaction.delete.IDeleteTransaction;
import com.project.transactions.domain.model.transaction.exception.IllegalOperationException;
import com.project.transactions.domain.model.transaction.exception.TransactionCanNotBeDeleted;
import com.project.transactions.domain.model.transaction.gateway.ITransactionGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class DeleteTransactionTests {

    @Mock
    private ITransactionGateway transactionGateway;
    private final IDeleteTransaction deleteTransaction;

    DeleteTransactionTests() {
        openMocks(this);
        this.deleteTransaction = new DeleteTransaction(transactionGateway);
    }

    @Test
    void Delete_Pending_Transaction_Successfully() {
        var deleteCommand = DeleteTransactionCommandMother.buildValidDeleteTransactionCommand();
        var transaction = TransactionMother.buildValidTransaction();

        when(transactionGateway.findById(any(ID.class)))
                .thenReturn(Optional.of(transaction));
        doNothing().when(transactionGateway).deleteById(any(ID.class));

        deleteTransaction.execute(deleteCommand);

        verify(transactionGateway).findById(any(ID.class));
        verify(transactionGateway).deleteById(any(ID.class));
    }

    @Test
    void Delete_Transaction_Successfully_When_Was_Not_Found() {
        var deleteCommand = DeleteTransactionCommandMother.buildValidDeleteTransactionCommand();

        when(transactionGateway.findById(any(ID.class)))
                .thenReturn(Optional.empty());
        doNothing().when(transactionGateway).deleteById(any(ID.class));

        deleteTransaction.execute(deleteCommand);

        verify(transactionGateway).findById(any(ID.class));
    }

    @Test
    void Throw_Exception_When_Trying_To_Delete_Paid_Transaction() {
        var deleteCommand = DeleteTransactionCommandMother.buildValidDeleteTransactionCommand();
        var transaction = TransactionMother.buildPaidTransaction();
        transaction.pay();

        when(transactionGateway.findById(any(ID.class)))
                .thenReturn(Optional.of(transaction));

        var exception = assertThrows(TransactionCanNotBeDeleted.class,
                () -> deleteTransaction.execute(deleteCommand));
        var error = exception.getError();

        assertThat(error.getCode()).isEqualTo("TRAN-IO-001");
        assertThat(error.getName()).isEqualTo(IllegalOperationException.class.getSimpleName());

        verify(transactionGateway).findById(any(ID.class));
    }


}
