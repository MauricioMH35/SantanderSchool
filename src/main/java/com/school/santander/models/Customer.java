package com.school.santander.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
public class Customer {
    @Id private String id; // (find - sim)
    @NotEmpty private String name; // (find - sim)
    @NotEmpty private String cpf; // usar validation pattern (find - não)
    @NotEmpty private String email; // usar validation pattern  (find-não)
    @NotEmpty private String username; // (find - sim)
    @NotEmpty private String password; // (find - não)
    private String roles; // user, admin (find -não)
    private String tag; // student, teacher (find -sim)
    private LocalDate assign; // data de registro no sistema (find - não)
}
