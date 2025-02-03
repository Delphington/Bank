package dev.project.dto;

import dev.project.model.enumerator.AccountType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private Long id;

    @NotNull(message = "Может быть выбран либо DEBIT, либо CREDIT")
    private AccountType accountType;

    @NotNull(message = "Баланс обязателен для заполнения")
    @Positive
    private BigDecimal balance;
}
