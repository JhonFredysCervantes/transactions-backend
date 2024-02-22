package com.project.transactions.domain.usecase.transaction.find;


import com.project.transactions.domain.model.transaction.FilterTransaction;
import com.project.transactions.domain.model.transaction.TransactionMother;
import com.project.transactions.domain.model.transaction.find.FindTransactionsCommandMother;
import com.project.transactions.domain.model.transaction.find.IFindTransactions;
import com.project.transactions.domain.model.transaction.gateway.ITransactionGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class FindTransactionsTests {

    @Mock
    private ITransactionGateway transactionGateway;
    private final IFindTransactions findTransactions;

    FindTransactionsTests() {
        openMocks(this);
        this.findTransactions = new FindTransactions(transactionGateway);
    }

    @Test
    void Find_Transactions_Successfully() {
        var findCommand = FindTransactionsCommandMother.buildValidFindTransactionsCommand();
        var transactionsToResponse = List.of(TransactionMother.buildValidTransaction());

        when(transactionGateway.find(any(FilterTransaction.class)))
                .thenReturn(transactionsToResponse);

        var transactions = findTransactions.execute(findCommand);

        assertThat(transactions).isNotNull().isNotEmpty();

        verify(transactionGateway).find(any(FilterTransaction.class));
    }

}