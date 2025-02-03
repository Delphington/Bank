package dev.project.model;

import dev.project.model.enumerator.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


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
}
