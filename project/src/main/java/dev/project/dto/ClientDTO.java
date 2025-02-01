package dev.project.dto;

import lombok.Data;

@Data
public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Boolean blockedFor;
    private String blockedWhom;
}
