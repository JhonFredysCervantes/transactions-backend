package com.project.transactions.application.configuration;

import com.project.transactions.domain.model.transaction.create.ICreateTransaction;
import com.project.transactions.domain.model.transaction.find.IFindTransactions;
import com.project.transactions.domain.model.transaction.gateway.ITransactionGateway;
import com.project.transactions.domain.model.transaction.pay.IPayTransactions;
import com.project.transactions.domain.usecase.transaction.create.CreateTransaction;
import com.project.transactions.domain.usecase.transaction.find.FindTransactions;
import com.project.transactions.domain.usecase.transaction.pay.PayTransactions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration use case beans
 */
@Configuration
public class UseCasesConf {

    @Bean
    public ICreateTransaction createTransactionBean(ITransactionGateway transactionGateway) {
        return new CreateTransaction(transactionGateway);
    }

    @Bean
    public IFindTransactions findTransactionsBean(ITransactionGateway transactionGateway) {
        return new FindTransactions(transactionGateway);
    }

    @Bean
    public IPayTransactions payTransactionBean(ITransactionGateway transactionGateway) {
        return new PayTransactions(transactionGateway);
    }

}
