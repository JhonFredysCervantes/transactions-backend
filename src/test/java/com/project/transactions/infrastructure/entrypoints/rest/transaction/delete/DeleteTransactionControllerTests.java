package com.project.transactions.infrastructure.entrypoints.rest.transaction.delete;

import com.project.transactions.domain.model.transaction.exception.IllegalOperationException;
import com.project.transactions.infrastructure.adapters.persistence.transaction.ITransactionRepository;
import com.project.transactions.infrastructure.adapters.persistence.transaction.entity.TransactionEntityMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class DeleteTransactionControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ITransactionRepository transactionRepository;

    @BeforeEach
    void setup() {
        var transactionValid = TransactionEntityMother.validTransactionEntity();
        var transactionPaid = TransactionEntityMother.paidTransactionEntity();
        transactionRepository.saveAll(List.of(transactionValid, transactionPaid));
    }

    @Test
    void Delete_Transaction_Successfully() throws Exception {
        var transactionValid = TransactionEntityMother.validTransactionEntity();
        mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/api/transactions/%s", transactionValid.getId()))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void Expect_Conflict_When_Try_To_Delete_Paid_Transaction() throws Exception {
        var transactionPaid = TransactionEntityMother.paidTransactionEntity();
        mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/api/transactions/%s", transactionPaid.getId()))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isConflict())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value(IllegalOperationException.class.getSimpleName()));

    }

}