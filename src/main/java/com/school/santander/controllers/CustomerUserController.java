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
@RequestMapping("/users/santander/customers")
@RequiredArgsConstructor
public class CustomerUserController {

    private final CustomerService service;

    @Operation(
            summary = "Authentication Principal by Id",
            description = "Search by identifier (id)",
            tags = {"Customer / User School Santander "}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "When you are successful in finding"),
            @ApiResponse(responseCode = "404", description = "When not successful to find")
    })
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> findByIdAuth(
            @PathVariable String id, @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(
                service.findById(id), HttpStatus.FOUND
        );
    }

    @Operation(
            summary = "Search the customer by name",
            description = "To query customers who have the name specified by the request.",
            tags = {"Customer / User School Santander "}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "When you are successful in finding"),
            @ApiResponse(responseCode = "404", description = "When not successful to find")
    })
    @GetMapping(value = "/find/name", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Customer>> findByNameContains(@RequestParam("search") String name) {
        return new ResponseEntity<>(
                service.findByNameContains(name), HttpStatus.FOUND
        );
    }

    @Operation(
            summary = "Search the customer by username",
            description = "To query the customer with the username specified by the request.",
            tags = {"Customer / User School Santander "}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "When you are successful in finding"),
            @ApiResponse(responseCode = "404", description = "When not successful to find")
    })
    @GetMapping(value = "/find/username", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> findByUsername(@RequestParam("search") String username) {
        return new ResponseEntity<>(
                service.findByUsername(username), HttpStatus.FOUND
        );
    }

    @Operation(
            summary = "Search the customer by tag",
            description = "To query customers with the representation/tag of their record, they can switch between Teacher and Student, where specified by the request.",
            tags = {"Customer / User School Santander "}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "When you are successful in finding"),
            @ApiResponse(responseCode = "404", description = "When not successful to find")
    })
    @GetMapping(value = "/find/tag", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Customer>> findByTag(@RequestParam("search") String tag) {
        return new ResponseEntity<>(
                service.findByTag(tag), HttpStatus.FOUND
        );
    }

    @Operation(
            summary = "Search all customers",
            description = "Search for all customers in the system using pager to organize the request response.",
            tags = {"Customer / User School Santander "}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "When you are successful in finding"),
            @ApiResponse(responseCode = "404", description = "When not successful to find")
    })
    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Customer>> findAll() {
        return new ResponseEntity<>(
                service.findAll(), HttpStatus.FOUND
        );
    }

    @Operation(
            summary = "Update of customer registration data",
            description = "Updates the customer's registration data by making a query through the identifier, so that it can determine if this customer really exists in the database, as well as the data that are not being changed will be kept.",
            tags = {"Customer / User School Santander "}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "When you are successful in finding"),
            @ApiResponse(responseCode = "404", description = "When not successful to find")
    })
    @PutMapping(value = "/update/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity updateById(@PathVariable String id,
                                     @RequestBody @Valid Customer customerReplace) {
        service.updateById(id, customerReplace);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Delete the customer registered in the database",
            description = "Checks if the customer exists in the database using its identifier to facilitate the location by means of a single data in order to remove it.",
            tags = {"Customer / User School Santander "}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "When you are successful in finding"),
            @ApiResponse(responseCode = "404", description = "When not successful to find")
    })
    @DeleteMapping(value = "/delete/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity deleteById(@PathVariable String id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
