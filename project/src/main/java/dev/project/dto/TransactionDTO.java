package dev.project.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long id;

    @NotNull(message = "Сумма обязательна для заполнения")
    @Positive
    private BigDecimal sum;


    private LocalDateTime transactionTime;
}
