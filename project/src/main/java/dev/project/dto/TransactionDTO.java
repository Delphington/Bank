package dev.project.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long id;

    private BigDecimal sum;

    private LocalDateTime transactionTime;
}
