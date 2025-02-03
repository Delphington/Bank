package dev.project.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter
@Builder
@Entity
@Table(name = "data_error_logs")
public class DataSourceErrorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "stack_trace")
    private String stackTraceMessage;
    @Column(name= "message")
    private String message;
    @Column(name = "signature_method")
    private String signatureMethod;
    @Column(name = "create_at")
    @CreationTimestamp //автоматически
    private LocalDateTime createdAt;
}

