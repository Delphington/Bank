package dev.project.dto;

import dev.project.enumerator.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private Long id;

    private AccountType accountType;


    private BigDecimal balance;
}
