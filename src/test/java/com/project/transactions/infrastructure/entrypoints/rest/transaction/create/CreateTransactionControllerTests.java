package com.project.transactions.infrastructure.entrypoints.rest.transaction.create;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.transactions.domain.model.transaction.exception.AmountValueCanNotBeLessThanZero;
import com.project.transactions.domain.model.transaction.exception.TransactionNameCanNotBeNullOrEmptyException;
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
class CreateTransactionControllerTests {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    CreateTransactionControllerTests() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void Create_Transaction_Successfully() throws Exception {
        var request = CreateTransactionRequestMother.validCreateTransactionRequest();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
                        .content(objectMapper.writeValueAsBytes(request))
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
    void Expect_Exception_When_Transaction_Name_Is_Empty() throws Exception {
        var request = CreateTransactionRequestMother.emptyNameCreateTransactionRequest();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
                        .content(objectMapper.writeValueAsBytes(request))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isBadRequest())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value(TransactionNameCanNotBeNullOrEmptyException.class.getSimpleName()));
    }

    @Test
    void Expect_Exception_When_Transaction_Amount_Is_Negative() throws Exception {
        var request = CreateTransactionRequestMother.negativeAmountCreateTransactionRequest();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
                        .content(objectMapper.writeValueAsBytes(request))
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