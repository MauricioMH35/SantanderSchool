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

@RestController
@RequestMapping("/cs")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;


    @PostMapping("/register")
    public ResponseEntity<Customer> save(@RequestBody @Valid Customer customer) {
        return new ResponseEntity<>(
                service.save(customer), HttpStatus.CREATED
        );
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Customer> findByIdAuth(
            @PathVariable String id, @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(
                service.findById(id), HttpStatus.FOUND
        );
    }

    @GetMapping("/user/find/name")
    public ResponseEntity<Page<Customer>> findByNameContains(@RequestParam("search") String name) {
        return new ResponseEntity<>(
                service.findByNameContains(name), HttpStatus.FOUND
        );
    }

    @GetMapping("/user/find/username")
    public ResponseEntity<Customer> findByUsername(@RequestParam("search") String username) {
        return new ResponseEntity<>(
                service.findByUsername(username), HttpStatus.FOUND
        );
    }

    @GetMapping("/user/find/tag")
    public ResponseEntity<Page<Customer>> findByTag(@RequestParam("search") String tag) {
        return new ResponseEntity<>(
                service.findByTag(tag), HttpStatus.FOUND
        );
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Customer>> findAll() {
        return new ResponseEntity<>(
                service.findAll(), HttpStatus.FOUND
        );
    }

    @PutMapping("/user/update/{customerId}")
    public ResponseEntity updateById(
            @PathVariable String customerId, @RequestBody @Valid Customer customerReplace) {
        service.updateById(customerId, customerReplace);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/user/delete/{customerId}")
    public ResponseEntity deleteById(@PathVariable String customerId) {
        service.deleteById(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
