package com.project.transactions.infrastructure.entrypoints.rest.transaction.delete;

import com.project.transactions.domain.model.shared.exception.Error;
import com.project.transactions.domain.model.transaction.delete.DeleteTransactionCommand;
import com.project.transactions.domain.model.transaction.delete.IDeleteTransaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Delete transaction controller
 */
@Tag(name = "Delete", description = "Delete transaction controller")
@RestController
public class DeleteTransactionController {
    private final IDeleteTransaction deleteTransaction;

    /**
     * Constructor
     *
     * @param deleteTransaction Delete transactions instance
     */
    public DeleteTransactionController(IDeleteTransaction deleteTransaction) {
        this.deleteTransaction = deleteTransaction;
    }

    @Operation(description = "Delete Transactions")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Transaction deleted",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @DeleteMapping(value = "/api/transactions/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteTransaction(@PathVariable("id") String id) {
        var command = new DeleteTransactionCommand(id);
        deleteTransaction.execute(command);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

}
