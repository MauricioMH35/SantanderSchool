package com.school.santander.services.impls;

import com.school.santander.models.Customer;
import com.school.santander.repositories.CustomerRepository;
import com.school.santander.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public Customer findById(String id) {
        return null;
    }

    @Override
    public Page<Customer> findByNameContains(String name) {
        return null;
    }

    @Override
    public Customer findByUsername(String username) {
        return null;
    }

    @Override
    public Page<Customer> findByTag(String tag) {
        return null;
    }

    @Override
    public Page<Customer> findAll() {
        return null;
    }

    @Override
    public void updateById(String customerId, Customer customerReplace) {

    }

    @Override
    public void deleteById(String customerId) {

    }
}
