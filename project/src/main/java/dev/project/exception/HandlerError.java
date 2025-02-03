package dev.project.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.project.exception.response.TransactionErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class HandlerError {

    @ExceptionHandler
    public ResponseEntity<ApiError> handleException(final NotFoundDataException e){
        log.info("404 {}", e.getMessage(), e);
        return new ResponseEntity<>(new ApiError("NOT_FOUND", e.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleException(final DataConversionException e){
        log.info("400 {}", e.getMessage(), e);
        return new ResponseEntity<>(new ApiError("BAD_REQUEST", e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<ApiError> handleException(final ValidationException e){
        log.info("400 {}", e.getMessage(), e);
        return new ResponseEntity<>(new ApiError("BAD_REQUEST", e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleThrowableExceptions(final Exception e) {
        log.info("500 {}", e.getMessage(), e);
        return new ApiError("INTERNAL_SERVER_ERROR", e.getMessage(),
                LocalDateTime.now());
    }


    private record ApiError(String status, String message,
                           @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime timestamp) {
        public ApiError(String status, String message, LocalDateTime timestamp) {
            this.status = status;
            this.message = message;
            this.timestamp = timestamp;
        }
    }
}
