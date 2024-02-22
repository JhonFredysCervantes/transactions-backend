package com.project.transactions.infrastructure.entrypoints.rest;

import com.project.transactions.domain.model.shared.exception.Error;
import com.project.transactions.domain.model.shared.exception.TransactionExceptionBase;
import com.project.transactions.domain.model.transaction.exception.AmountValueCanNotBeLessThanZero;
import com.project.transactions.domain.model.transaction.exception.IllegalOperationException;
import com.project.transactions.domain.model.transaction.exception.TransactionIdCanNotBeNullOrEmptyException;
import com.project.transactions.domain.model.transaction.exception.TransactionNameCanNotBeNullOrEmptyException;
import com.project.transactions.domain.model.transaction.exception.TransactionNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Exception handler
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({TransactionNotFoundException.class})
    public ResponseEntity<Error> handleNotFoundExceptions(TransactionExceptionBase transactionExceptionBase) {
        return new ResponseEntity<>(transactionExceptionBase.getError(), NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({TransactionNameCanNotBeNullOrEmptyException.class,
            AmountValueCanNotBeLessThanZero.class, TransactionIdCanNotBeNullOrEmptyException.class})
    public ResponseEntity<Error> handleBadRequestExceptions(TransactionExceptionBase transactionExceptionBase) {
        return new ResponseEntity<>(transactionExceptionBase.getError(), org.springframework.http.HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({IllegalOperationException.class})
    public ResponseEntity<Error> handleBadConflictExceptions(TransactionExceptionBase transactionExceptionBase) {
        return new ResponseEntity<>(transactionExceptionBase.getError(), HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
    public ResponseEntity<Error> handleUntreatedExceptions(Exception exception) {
        log.error("### Error: {} ##", exception, exception);
        return new ResponseEntity<>(new Error("DEF-500", "GeneralException", "General Exception"), INTERNAL_SERVER_ERROR);
    }
}
