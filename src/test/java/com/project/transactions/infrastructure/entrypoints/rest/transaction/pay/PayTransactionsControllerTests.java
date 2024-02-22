package com.project.transactions.infrastructure.entrypoints.rest.transaction.pay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.transactions.domain.model.transaction.exception.AmountValueCanNotBeLessThanZero;
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
class PayTransactionsControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ITransactionRepository transactionRepository;
    private final ObjectMapper objectMapper;

    PayTransactionsControllerTests() {
        this.objectMapper = new ObjectMapper();
    }

    @BeforeEach
    void setup() {
        var transaction = TransactionEntityMother.validTransactionEntity();
        transactionRepository.save(transaction);
    }

    @Test
    void Pay_Transactions_Successfully() throws Exception {

        var transactionsToPay = PayTransactionRequestMother.validPayTransactionRequest();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions/pay")
                        .content(objectMapper.writeValueAsBytes(transactionsToPay))
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
    void Expect_Bad_Request_When_Try_To_Pay_with_Negative_Amount() throws Exception {

        var transactionsToPay = PayTransactionRequestMother.payTransactionRequestWithNegativeAmount();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions/pay")
                        .content(objectMapper.writeValueAsBytes(transactionsToPay))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value(AmountValueCanNotBeLessThanZero.class.getSimpleName()));
    }

}