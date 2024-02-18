package com.project.transactions.infrastructure.entrypoints.rest.transaction.find;

import com.project.transactions.domain.model.shared.exception.Error;
import com.project.transactions.domain.model.transaction.find.FindTransactionsCommand;
import com.project.transactions.domain.model.transaction.find.IFindTransactions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Find transactions controller
 */
@Tag(name = "Find", description = "find transactions controller")
@RestController
public class FindTransactionsController {
    private final IFindTransactions useCase;

    /**
     * Constructor
     *
     * @param useCase Find transactions instance
     */
    public FindTransactionsController(IFindTransactions useCase) {
        this.useCase = useCase;
    }

    @Operation(description = "Find Transactions",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content =
            @Content(mediaType = "application/json")))
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Transactions found",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = FindTransactionsResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @GetMapping("/transactions")
    public ResponseEntity<List<FindTransactionsResponse>> findTransactions(@RequestParam(required = false) String name,
                                                                           @RequestParam(required = false) String status,
                                                                           @RequestParam(required = false) String fromDate,
                                                                           @RequestParam(required = false) String toDate) {
        var findTransactionsCommand = new FindTransactionsCommand(name, status, fromDate, toDate);
        var transactions = useCase.execute(findTransactionsCommand);
        var infoToResponse = transactions.parallelStream()
                .map(FindTransactionsResponse::toResponse)
                .toList();
        return ResponseEntity.ok(infoToResponse);
    }
}
