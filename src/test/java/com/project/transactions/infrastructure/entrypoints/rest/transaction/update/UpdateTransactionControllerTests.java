package com.project.transactions.infrastructure.entrypoints.rest.transaction.update;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@SpringBootTest
@AutoConfigureMockMvc
class UpdateTransactionControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ITransactionRepository transactionRepository;
    private final ObjectMapper objectMapper;

    UpdateTransactionControllerTests() {
        this.objectMapper = new ObjectMapper();
    }

    @BeforeEach
    void setup() {
        transactionRepository.save(TransactionEntityMother.validTransactionEntity());
    }

    @Test
    void Pay_Transaction_Successfully() throws Exception {
        var request = UpdateTransactionRequestMother.validUpdateTransactionRequest();

        mockMvc.perform(MockMvcRequestBuilders.put(String.format("/api/transactions/%s", request.getId()))
                        .content(objectMapper.writeValueAsBytes(request))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value(request.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount")
                        .value(String.valueOf(request.getAmount())));
    }
}