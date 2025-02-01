package dev.project.model;

import jakarta.persistence.*;
import org.yaml.snakeyaml.events.Event;

import javax.swing.text.StyledEditorKit;
import java.time.LocalDateTime;

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

}
