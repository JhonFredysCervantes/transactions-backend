package com.project.transactions.domain.usecase.transaction.pay;

import com.project.transactions.domain.model.transaction.Amount;
import com.project.transactions.domain.model.transaction.ID;
import com.project.transactions.domain.model.transaction.Name;
import com.project.transactions.domain.model.transaction.Status;
import com.project.transactions.domain.model.transaction.Transaction;
import com.project.transactions.domain.model.transaction.exception.AmountValueCanNotBeLessThanZero;
import com.project.transactions.domain.model.transaction.exception.TransactionIdCanNotBeNullOrEmptyException;
import com.project.transactions.domain.model.transaction.exception.TransactionNotFoundException;
import com.project.transactions.domain.model.transaction.gateway.ITransactionGateway;
import com.project.transactions.domain.model.transaction.pay.IPayTransactions;
import com.project.transactions.domain.model.transaction.pay.PayTransactionsCommandMother;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class PayTransactionsTests {

    @Mock
    private ITransactionGateway transactionGateway;
    @Captor
    private ArgumentCaptor<Transaction> transactionArgumentCaptor;
    private final IPayTransactions payTransactions;

    PayTransactionsTests() {
        openMocks(this);
        this.payTransactions = new PayTransactions(transactionGateway);
    }

    @Test
    void Pay_Transactions_Successfully() {
        var command = PayTransactionsCommandMother.validPayTransactionsCommand();
        var transactionsExpected = command.getTransactionIDs().parallelStream()
                .map(id -> Transaction.build(new ID(id),
                        new Name(id),
                        Status.PENDING,
                        new Amount(command.getTotalToPay() / command.getTransactionIDs().size())))
                .toList();

        when(transactionGateway.finByIds(anyList()))
                .thenReturn(transactionsExpected);
        when(transactionGateway.persist(any(Transaction.class)))
                .thenReturn(null);

        payTransactions.execute(command);

        verify(transactionGateway).finByIds(anyList());
        verify(transactionGateway, times(command.getTransactionIDs().size())).persist(transactionArgumentCaptor.capture());

        var valuesCaptured = transactionArgumentCaptor.getAllValues();

        boolean allTransactionsPaid = valuesCaptured.stream()
                .allMatch(transaction -> transaction.getStatus() == Status.PAID);

        assertThat(allTransactionsPaid).isTrue();

    }

    @Test
    void Throw_Exception_When_Transaction_ID_IS_Empty() {
        var command = PayTransactionsCommandMother.emptyIdPayTransactionsCommand();

        var exception = assertThrows(TransactionIdCanNotBeNullOrEmptyException.class,
                () -> payTransactions.execute(command));
        var error = exception.getError();

        assertThat(error.getCode()).isEqualTo("TRAN-IA-003");
        assertThat(error.getName()).isEqualTo(TransactionIdCanNotBeNullOrEmptyException.class.getSimpleName());
    }

    @Test
    void TThrow_Exception_When_Transaction_Have_Negative_Amount() {
        var command = PayTransactionsCommandMother.invalidAmountPayTransactionsCommand();

        var exception = assertThrows(AmountValueCanNotBeLessThanZero.class,
                () -> payTransactions.execute(command));
        var error = exception.getError();

        assertThat(error.getCode()).isEqualTo("TRAN-IA-002");
        assertThat(error.getName()).isEqualTo(AmountValueCanNotBeLessThanZero.class.getSimpleName());
    }

    @Test
    void TThrow_Exception_When_Transaction_Was_Not_Found() {
        var command = PayTransactionsCommandMother.invalidAmountPayTransactionsCommand();
        var emptyList = new ArrayList<Transaction>();

        when(transactionGateway.finByIds(anyList()))
                .thenReturn(emptyList);

        var exception = assertThrows(TransactionNotFoundException.class,
                () -> payTransactions.execute(command));
        var error = exception.getError();

        assertThat(error.getCode()).isEqualTo("TRAN-NF-001");
        assertThat(error.getName()).isEqualTo(TransactionNotFoundException.class.getSimpleName());
    }
}