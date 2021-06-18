package com.school.santander.controllers;

import com.school.santander.models.Customer;
import com.school.santander.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/pb/santander")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @Operation(
            summary = "Register a new customer in the application",
            description = "Registers the customer in the application and returns the registered customer as a" +
                    " response, so you can view the registered data. A customer can be either a teacher who registers " +
                    "for a course after registering in the application, or it can also be a student to consume the " +
                    "course content.",
            tags = {"Customer / User School Santander "}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Successful Operation Create Customer"),
            @ApiResponse(responseCode = "409", description = "When where is conflict Customer already exists")
    })
    @PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> save(@RequestBody @Valid Customer customer) {
        return new ResponseEntity<>(
                service.save(customer), HttpStatus.CREATED
        );
    }

}
