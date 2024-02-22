package com.project.transactions.infrastructure.entrypoints.rest.transaction.create;

import com.project.transactions.domain.model.shared.exception.Error;
import com.project.transactions.domain.model.transaction.create.CreateTransactionCommand;
import com.project.transactions.domain.model.transaction.create.ICreateTransaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * Create transaction controller
 */
@Tag(name = "Create", description = "Create transaction controller")
@RestController
public class CreateTransactionController {
    private final ICreateTransaction useCase;

    /**
     * Constructor
     *
     * @param createTransaction Create transactions instance
     */
    public CreateTransactionController(ICreateTransaction createTransaction) {
        this.useCase = createTransaction;
    }

    @Operation(description = "Create Transactions",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateTransactionRequest.class))
            ))
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Transaction created",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @PostMapping("/api/transactions")
    public ResponseEntity<Void> createTransaction(@RequestBody CreateTransactionRequest createTransactionRequest) {
        var createTransactionCommand = CreateTransactionCommand.builder()
                .name(createTransactionRequest.getName())
                .amount(createTransactionRequest.getAmount())
                .build();
        var id = useCase.execute(createTransactionCommand);
        return ResponseEntity
                .created(URI.create(String.format("/transactions/%s", id.getValue())))
                .build();
    }


}
