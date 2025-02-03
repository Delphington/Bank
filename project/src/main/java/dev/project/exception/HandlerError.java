package dev.project.exception;

import dev.project.exception.response.TransactionErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class HandlerError {

    @ExceptionHandler
    public ResponseEntity<TransactionErrorResponse> handleException(NotFoundDataException e){
        TransactionErrorResponse err = new TransactionErrorResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<TransactionErrorResponse> handleException(DataConversionException e){
        TransactionErrorResponse err = new TransactionErrorResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<TransactionErrorResponse> handleException(ValidationException e){
        TransactionErrorResponse err = new TransactionErrorResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
