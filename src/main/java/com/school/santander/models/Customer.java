package com.school.santander.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer implements UserDetails {
    @Schema(description = "This is the customer identifier",
            example = "String of characters that make up the sequence")
    @Id
    private String id;

    @Schema(description = "This is the customer's name.",
            example = "It serves to, among others, determine a comparison attribute to verify the customer's " +
                    "authenticity.")
    @NotEmpty(message = "The name cannot be an empty field.")
    private String name;

    @Schema(description = "This is the CPF-Individual Taxpayer Registration number",
            example = "This value, as well as the name, will serve to identify the customer and mainly to validate " +
                    "the authenticity of the registration information and are consistent with that person's data. An " +
                    "attribute that must be kept in string/String format, as it has properties not only of numbers " +
                    "but of special characters such as dot(.) which, for eventual validations or queries, will be " +
                    "useful to determine the type of value.")
    @NotEmpty(message = "The CPF cannot be an empty field.")
    private String cpf;

    @Schema(description = "This is the email",
            example = "An attribute that can be used for queries and verification of customer registration in the " +
                    "system. This is data that must be stored in string format to facilitate queries and " +
                    "validations, as well as their handling.")
    @NotEmpty(message = "The email cannot be an empty field.")
    private String email;

    @Schema(description = "This is the username",
            example = "An attribute that will primarily serve to validate the client's authentication of the client, " +
                    "but which can facilitate queries, as it is an understandable value to the common human being and " +
                    "unique to the database.")
    @NotEmpty(message = "The username cannot be an empty field.")
    private String username;

    @Schema(description = "This is the password",
            example = "The password will only be used for the authorization of the client between the Escola " +
                    "Santander application.")
    @NotEmpty(message = "The password cannot be an empty field.")
    private String password;

    @Schema(description = "This is the function, or roles",
            example = "Its main role is to determine whether the client has sufficient authorization to access " +
                    "certain points of the application. This is an attribute that must be kept in lower case and " +
                    "separated the functions by comma(,).")
    private String roles;

    @Schema(description = "This is the function, or roles",
            example = "It serves primarily to define the type of customer, if it is a student or professor, this is " +
                    "a value that must be stored in lower case and the words separated by underscore(_).")
    private String tag;

    @Schema(description = "This is the registration date signature.",
            example = "This information serves to save the date of registration to the system by the user, it is not " +
                    "a changeable value.")
    private LocalDate assign;

    @Schema(description = "This is the course enrollment",
            example = "Whether you are a student or professor, registration for the course is for the client to " +
                    "verify their enrollment.")
    @DBRef private Course registration;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
