package com.school.santander.services.impls;

import com.school.santander.exceptions.BadRequestException;
import com.school.santander.exceptions.NotFoundException;
import com.school.santander.exceptions.ObjectTranscriptException;
import com.school.santander.models.Customer;
import com.school.santander.repositories.CustomerRepository;
import com.school.santander.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public Customer save(Customer customer) {
        if(repository.checkUniqueValues(customer) == null) {
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            String password = passwordEncoder.encode(customer.getPassword());
            String roles = "user";
            String tag = customer.getTag().toLowerCase(Locale.ROOT)
                    .replaceAll("\\s", "_");

            LocalDate assign = LocalDate.now();

            customer.setPassword(password);
            customer.setRoles(roles);
            customer.setTag(tag);
            customer.setAssign(assign);

        } else {
            throw new ObjectTranscriptException("Customer already registered, check if the data " +
                    "entered is correct.");
        }

        return repository.save(customer);
    }

    @Override
    public Customer findById(String id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "It was not possible to find the customer using the past data."));

        return customer;
    }

    @Override
    public Page<Customer> findByNameContains(String name) {
        String nameReplace = name.replaceAll("\\_", " ");
        List<Customer> customers = repository.findByNameContains(nameReplace);

        if(customers.isEmpty())
            throw new NotFoundException("It was not possible to query the customers" +
                    " that contain the last name.");

        return new PageImpl<>(customers);
    }

    @Override
    public Customer findByUsername(String username) {
        String usernameReplace = username.replaceAll("\\_", " ");
        Customer customer = repository.findByUsername(usernameReplace);

        if(customer == null)
            throw new NotFoundException("IIt was not possible to consult the customer with " +
                    "the username assigned to the survey.");

        return customer;
    }

    @Override
    public Page<Customer> findByTag(String tag) {
        String tagReplace = tag.replaceAll("\\_", " ").toLowerCase(Locale.ROOT);
        List<Customer> customers = repository.findByTag(tagReplace);

        if(customers.isEmpty())
            throw new NotFoundException("It was not possible to consult the customer with the tag " +
                    "assigned to the search.");

        return new PageImpl<>(customers);
    }

    @Override
    public Page<Customer> findAll() {
        List<Customer> customers = repository.findAll();

        if(customers.isEmpty())
            throw new NotFoundException("No results were found for the query.");

        return new PageImpl<>(customers);
    }

    @Override
    public void updateById(String customerId, Customer customerReplace) {
        if(customerId.isEmpty() || customerId.isBlank())
            throw new BadRequestException("It is not possible to perform the operation without " +
                    "providing the data required to perform this procedure.");

        repository.updateById(customerId, customerReplace);
    }

    @Override
    public void deleteById(String customerId) {
        repository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Unable to delete the customer, it was not found in " +
                        "the database."));

        repository.deleteById(customerId);
    }
}
