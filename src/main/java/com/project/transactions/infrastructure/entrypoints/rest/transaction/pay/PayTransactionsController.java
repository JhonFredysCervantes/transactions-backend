package com.project.transactions.infrastructure.entrypoints.rest.transaction.pay;

import com.project.transactions.domain.model.shared.exception.Error;
import com.project.transactions.domain.model.transaction.pay.IPayTransactions;
import com.project.transactions.domain.model.transaction.pay.PayTransactionsCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Pay transactions controller
 */
@Tag(name = "Pay", description = "Pay transactions controller")
@RestController
public class PayTransactionsController {
    private final IPayTransactions useCase;

    /**
     * Constructor
     *
     * @param useCase Pay transactions instance
     */
    public PayTransactionsController(IPayTransactions useCase) {
        this.useCase = useCase;
    }

    @Operation(description = "Pay Transactions",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content =
            @Content(mediaType = "application/json")))
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success operation",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = PayTransactionResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @PostMapping("/transactions/pay")
    public ResponseEntity<List<PayTransactionResponse>> payTransactions(@RequestBody PayTransactionRequest payTransactionRequest) {
        var useCaseCommand = PayTransactionsCommand.builder()
                .transactionIDs(payTransactionRequest.getIds())
                .totalToPay(payTransactionRequest.getAmount())
                .build();
        var transactions = useCase.execute(useCaseCommand);
        var infoToResponse = transactions.parallelStream()
                .map(PayTransactionResponse::toResponse)
                .toList();
        return ResponseEntity.ok(infoToResponse);
    }
}
