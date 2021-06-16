package com.school.santander.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    private String id;
    @NotEmpty(message = "The name cannot be an empty field.")
    private String name;
    @NotEmpty(message = "The CPF cannot be an empty field.")
    private String cpf;
    @NotEmpty(message = "The email cannot be an empty field.")
    private String email;
    @NotEmpty(message = "The username cannot be an empty field.")
    private String username;
    @NotEmpty(message = "The password cannot be an empty field.")
    private String password;
    private String roles;
    private String tag;
    private LocalDate assign;
    @DBRef private Course registration;
}
