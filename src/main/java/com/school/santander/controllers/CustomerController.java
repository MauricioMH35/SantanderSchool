package com.school.santander.controllers;

import com.school.santander.models.Customer;
import com.school.santander.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping("/customers/registry")
    public ResponseEntity<Customer> save(@RequestBody @Valid Customer customer) {
        return new ResponseEntity<>(
                service.save(customer), HttpStatus.CREATED
        );
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> findById(@PathVariable String id) {
        return new ResponseEntity<>(
                service.findById(id), HttpStatus.FOUND
        );
    }

    @GetMapping("/customers/find/name")
    public ResponseEntity<Page<Customer>> findByNameContains(@RequestParam String name) {
        return new ResponseEntity<>(
                service.findByNameContains(name), HttpStatus.FOUND
        );
    }

    @GetMapping("/customers/find/username")
    public ResponseEntity<Customer> findByUsername(@RequestParam String username) {
        return new ResponseEntity<>(
                service.findByUsername(username), HttpStatus.FOUND
        );
    }

    @GetMapping("/customers/find/tag")
    public ResponseEntity<Page<Customer>> findByTag(@RequestParam String tag) {
        return new ResponseEntity<>(
                service.findByTag(tag), HttpStatus.FOUND
        );
    }

    @GetMapping("/customers/find/all")
    public ResponseEntity<Page<Customer>> findAll() {
        return new ResponseEntity<>(
                service.findAll(), HttpStatus.FOUND
        );
    }

    @PutMapping("/customers/update/{customerId}")
    public ResponseEntity updateById(
            @PathVariable String customerId, @RequestBody @Valid Customer customerReplace) {
        service.updateById(customerId, customerReplace);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/customers/delete/{customerId}")
    public ResponseEntity deleteById(@PathVariable String customerId) {
        service.deleteById(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
