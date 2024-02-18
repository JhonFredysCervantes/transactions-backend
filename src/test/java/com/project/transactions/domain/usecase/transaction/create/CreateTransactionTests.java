package com.project.transactions.domain.usecase.transaction.create;

import com.project.transactions.domain.model.transaction.Amount;
import com.project.transactions.domain.model.transaction.ID;
import com.project.transactions.domain.model.transaction.Name;
import com.project.transactions.domain.model.transaction.Status;
import com.project.transactions.domain.model.transaction.Transaction;
import com.project.transactions.domain.model.transaction.create.CreateTransactionCommandMother;
import com.project.transactions.domain.model.transaction.create.ICreateTransaction;
import com.project.transactions.domain.model.transaction.exception.AmountValueCanNotBeLessThanZero;
import com.project.transactions.domain.model.transaction.exception.TransactionNameCanNotBeNullOrEmptyException;
import com.project.transactions.domain.model.transaction.gateway.ITransactionGateway;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CreateTransactionTests {
    @Mock
    private ITransactionGateway transactionGateway;
    @Captor
    private ArgumentCaptor<Transaction> transactionArgumentCaptor;
    private final ICreateTransaction createTransaction;


    CreateTransactionTests() {
        openMocks(this);
        this.createTransaction = new CreateTransaction(transactionGateway);
    }

    @Test
    void Create_Transaction_Successfully() {
        var command = CreateTransactionCommandMother.validCreateTransactionCommand();
        var transactionExpected = Transaction.build(new ID("UID"), new Name(command.getName()), Status.PENDING, new Amount(command.getAmount()));

        when(transactionGateway.persist(any(Transaction.class)))
                .thenReturn(transactionExpected);

        createTransaction.execute(command);

        verify(transactionGateway).persist(any(Transaction.class));
        verify(transactionGateway).persist(transactionArgumentCaptor.capture());

        var transactionCaptured = transactionArgumentCaptor.getValue();

        assertThat(transactionCaptured).isNotNull();
        assertThat(transactionCaptured.getId().getValue()).isNotNull();
        assertThat(transactionCaptured.getName().getValue()).isEqualTo(command.getName());
        assertThat(transactionCaptured.getAmount().getValue()).isEqualTo(command.getAmount());
        assertThat(transactionCaptured.getStatus()).isEqualTo(Status.PENDING);
    }

    @Test
    void Throw_Exception_When_Transaction_Have_Empty_Name() {
        var command = CreateTransactionCommandMother.createTransactionCommandWithEmptyName();

        var exception = assertThrows(TransactionNameCanNotBeNullOrEmptyException.class,
                () -> createTransaction.execute(command));
        var error = exception.getError();

        assertThat(error.getCode()).isEqualTo("TRAN-IA-001");
        assertThat(error.getName()).isEqualTo(TransactionNameCanNotBeNullOrEmptyException.class.getSimpleName());
    }

    @Test
    void Throw_Exception_When_Transaction_Have_Negative_Amount() {
        var command = CreateTransactionCommandMother.createTransactionCommandWithInvalidAmount();

        var exception = assertThrows(AmountValueCanNotBeLessThanZero.class,
                () -> createTransaction.execute(command));
        var error = exception.getError();

        assertThat(error.getCode()).isEqualTo("TRAN-IA-002");
        assertThat(error.getName()).isEqualTo(AmountValueCanNotBeLessThanZero.class.getSimpleName());
    }
}