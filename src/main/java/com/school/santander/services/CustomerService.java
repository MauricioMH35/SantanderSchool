package com.school.santander.services;

import com.school.santander.models.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {
    Customer save(Customer customer);
    Customer findById(String id);
    Page<Customer> findByNameContains(String name);
    Customer findByUsername(String username);
    Page<Customer> findByTag(String tag);
    Page<Customer> findAll();
    void updateById(String customerId, Customer customerReplace);
    void deleteById(String customerId);
}
