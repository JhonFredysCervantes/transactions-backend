package com.project.transactions.infrastructure.adapters.persistence.transaction;

import com.project.transactions.domain.model.transaction.FilterTransactionMother;
import com.project.transactions.domain.model.transaction.TransactionMother;
import com.project.transactions.domain.model.transaction.gateway.ITransactionGateway;
import com.project.transactions.infrastructure.adapters.persistence.transaction.entity.TransactionEntity;
import com.project.transactions.infrastructure.adapters.persistence.transaction.query.FilterByNameStatusAndDate;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class TransactionGatewayImpTests {

    @Mock
    private ITransactionRepository transactionRepository;
    @Captor
    private ArgumentCaptor<TransactionEntity> transactionEntityArgumentCaptor;
    private final ITransactionGateway transactionGateway;

    TransactionGatewayImpTests() {
        openMocks(this);
        transactionGateway = new TransactionGatewayImp(transactionRepository);
    }

    @Test
    void Persist_Transaction_Successfully() {
        var transaction = TransactionMother.buildValidTransaction();

        when(transactionRepository.save(any(TransactionEntity.class)))
                .thenReturn(TransactionEntity.toEntity(transaction));

        transactionGateway.persist(transaction);

        verify(transactionRepository).save(transactionEntityArgumentCaptor.capture());

        var valueCaptured = transactionEntityArgumentCaptor.getValue();

        assertThat(valueCaptured).isNotNull();
        assertThat(valueCaptured.getId()).isEqualTo(transaction.getId().getValue());
        assertThat(valueCaptured.getName()).isEqualTo(transaction.getName().getValue());
        assertThat(valueCaptured.getAmount()).isEqualTo(transaction.getAmount().getValue());
        assertThat(valueCaptured.getStatus()).isEqualTo(transaction.getStatus().name());
        assertThat(valueCaptured.getCreatedAt().toInstant()).isEqualTo(transaction.getCreatedAt().getValue().toInstant());
    }

    @Test
    void Find_Transactions_Successfully() {
        var filterTransaction = FilterTransactionMother.buildValidFilterTransaction();
        var transaction = TransactionMother.buildValidTransaction();
        var transactions = List.of(transaction);

        when(transactionRepository.findAll(any(FilterByNameStatusAndDate.class)))
                .thenReturn(transactions.parallelStream()
                        .map(TransactionEntity::toEntity)
                        .toList());

        var result = transactionGateway.find(filterTransaction);

        var transactionReturned = result.get(0);

        assertThat(result).isNotNull().hasSize(1);
        assertThat(transactionReturned.getId().getValue()).isEqualTo(transaction.getId().getValue());
        assertThat(transactionReturned.getName().getValue()).isEqualTo(transaction.getName().getValue());
        assertThat(transactionReturned.getAmount().getValue()).isEqualTo(transaction.getAmount().getValue());
        assertThat(transactionReturned.getStatus()).isEqualTo(transaction.getStatus());
        assertThat(transactionReturned.getCreatedAt().getValue().toInstant()).isEqualTo(transaction.getCreatedAt().getValue().toInstant());
    }

    @Test
    void FindById_Transaction_Successfully() {
        var transaction = TransactionMother.buildValidTransaction();
        var id = transaction.getId();

        when(transactionRepository.findById(id.getValue()))
                .thenReturn(Optional.of(TransactionEntity.toEntity(transaction)));

        var result = transactionGateway.findById(id);

        assertThat(result).isPresent();
        assertThat(result.get().getId().getValue()).isEqualTo(transaction.getId().getValue());
    }

    @Test
    void FindByIds_Transactions_Successfully() {
        var transaction = TransactionMother.buildValidTransaction();
        var id = transaction.getId();
        var ids = List.of(id);

        when(transactionRepository.findAllById(List.of(id.getValue())))
                .thenReturn(List.of(TransactionEntity.toEntity(transaction)));

        var result = transactionGateway.finByIds(ids);

        var transactionsReturned = result.get(0);

        assertThat(result).isNotNull().hasSize(1);
        assertThat(transactionsReturned.getId().getValue()).isEqualTo(transaction.getId().getValue());
        assertThat(transactionsReturned.getName().getValue()).isEqualTo(transaction.getName().getValue());
        assertThat(transactionsReturned.getAmount().getValue()).isEqualTo(transaction.getAmount().getValue());
        assertThat(transactionsReturned.getStatus()).isEqualTo(transaction.getStatus());
        assertThat(transactionsReturned.getCreatedAt().getValue().toInstant()).isEqualTo(transaction.getCreatedAt().getValue().toInstant());
    }

    @Test
    void Update_Transaction_Successfully() {
        var transaction = TransactionMother.buildValidTransaction();

        when(transactionRepository.save(any(TransactionEntity.class)))
                .thenReturn(TransactionEntity.toEntity(transaction));

        transactionGateway.update(transaction);

        verify(transactionRepository).save(transactionEntityArgumentCaptor.capture());

        var valueCaptured = transactionEntityArgumentCaptor.getValue();

        assertThat(valueCaptured).isNotNull();
        assertThat(valueCaptured.getId()).isEqualTo(transaction.getId().getValue());
        assertThat(valueCaptured.getName()).isEqualTo(transaction.getName().getValue());
        assertThat(valueCaptured.getAmount()).isEqualTo(transaction.getAmount().getValue());
        assertThat(valueCaptured.getStatus()).isEqualTo(transaction.getStatus().name());
        assertThat(valueCaptured.getCreatedAt().toInstant()).isEqualTo(transaction.getCreatedAt().getValue().toInstant());
    }

    @Test
    void Delete_Transaction_Successfully() {
        var id = TransactionMother.buildValidTransaction().getId();

        doNothing().when(transactionRepository).deleteById(id.getValue());

        transactionGateway.deleteById(id);

        verify(transactionRepository).deleteById(id.getValue());
    }

}