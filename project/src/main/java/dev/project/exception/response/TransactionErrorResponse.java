package dev.project.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TransactionErrorResponse {
    private String message;
    private LocalDateTime time;
}
