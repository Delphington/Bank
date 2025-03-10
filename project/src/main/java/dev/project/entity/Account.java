package dev.project.entity;

import dev.project.entity.enumerator.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "account")
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    private Client client;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "balance")
    private BigDecimal balance;

    //TODO: Добавить Created_at
}
