package com.school.santander.repositories;

import com.school.santander.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    List<Customer> findByNameContains(String nameContains);
    Customer findByUsername(String username);
    List<Customer> findByTag(String tag);
}
