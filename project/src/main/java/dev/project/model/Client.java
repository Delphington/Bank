package dev.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "blocked_for")
    private Boolean blockedFor;

    @Column(name = "blocked_whom")
    private String blockedWhom;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    @ToString.Exclude
    @JsonIgnore
    private List<Account> accountList;

}
