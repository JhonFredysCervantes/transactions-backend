package com.project.transactions.infrastructure.entrypoints.rest.transaction.update;

import com.project.transactions.domain.model.shared.exception.Error;
import com.project.transactions.domain.model.transaction.update.IUpdateTransaction;
import com.project.transactions.domain.model.transaction.update.UpdateTransactionCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Update transaction controller
 */
@Tag(name = "Update", description = "Update transaction controller")
@RestController
public class UpdateTransactionController {
    private final IUpdateTransaction updateTransaction;

    /**
     * Constructor
     *
     * @param updateTransaction Update transactions instance
     */
    public UpdateTransactionController(IUpdateTransaction updateTransaction) {
        this.updateTransaction = updateTransaction;
    }

    @Operation(description = "Create Transactions",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateTransactionRequest.class))
            ))
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Transaction created",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateTransactionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @PutMapping(value = "/api/transactions/{id}", produces = "application/json")
    public ResponseEntity<UpdateTransactionResponse> updateTransaction(@PathVariable("id") String id,
                                                                       @RequestBody UpdateTransactionRequest updateTransactionRequest) {
        var command = new UpdateTransactionCommand(id, updateTransactionRequest.getName(), updateTransactionRequest.getAmount());
        var result = updateTransaction.execute(command);

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(UpdateTransactionResponse.from(result));
    }
}
