package com.school.santander.repositories.impls;

import com.school.santander.exceptions.NotFoundException;
import com.school.santander.models.Customer;
import com.school.santander.repositories.CustomerRepositoryCustom;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
@NoArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    private MongoOperations mongoOperations;

    @Override
    public Customer checkUniqueValues(Customer customer) {
        Query query = new Query();
        List<Criteria> criterias = new ArrayList<>();

        String username = customer.getUsername();
        String email = customer.getEmail();
        String cpf = customer.getCpf();

        if(!username.isBlank() || !username.isEmpty())
            criterias.add(Criteria.where("username").is(username));
        else if(!email.isBlank() || !email.isEmpty())
            criterias.add(Criteria.where("email").is(email));
        else if(!cpf.isBlank() || !cpf.isEmpty())
            criterias.add(Criteria.where("cpf").is(cpf));

        if(!criterias.isEmpty())
            query.addCriteria(new Criteria().andOperator(criterias));

        return mongoTemplate.findOne(query, Customer.class);
    }

    @Override
    public void updateById(String customerId, Customer customerReplace) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(customerId));
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        Customer currentCustomer = mongoTemplate.findOne(query, Customer.class);

        if(currentCustomer == null)
            throw new NotFoundException("It was not possible to find the customer based on the " +
                "data provided for the query.");

        if(!customerReplace.getName().isEmpty() || !customerReplace.getName().isBlank())
            customerReplace.setName(customerReplace.getName());

        if(!customerReplace.getCpf().isEmpty() || !customerReplace.getCpf().isBlank())
            customerReplace.setCpf(customerReplace.getCpf());

        if(!customerReplace.getEmail().isEmpty() || !customerReplace.getEmail().isBlank())
            customerReplace.setEmail(customerReplace.getEmail());

        if(!customerReplace.getUsername().isEmpty() || !customerReplace.getUsername().isBlank())
            customerReplace.setUsername(customerReplace.getUsername());

        if(!customerReplace.getPassword().isEmpty() || !customerReplace.getPassword().isBlank())
            customerReplace.setPassword(passwordEncoder.encode(customerReplace.getPassword()));

        if(!customerReplace.getRoles().isEmpty() || !customerReplace.getRoles().isBlank())
            customerReplace.setRoles(customerReplace.getRoles().toLowerCase(Locale.ROOT));

        if(!customerReplace.getTag().isEmpty() || !customerReplace.getTag().isBlank())
            customerReplace.setTag(currentCustomer.getTag().replaceAll("\\s", "_")
                    .toLowerCase(Locale.ROOT));

        if(!currentCustomer.getAssign().equals(customerReplace.getAssign()))
            customerReplace.setAssign(currentCustomer.getAssign());

        if(customerReplace.getRegistration() != null)
            currentCustomer.setRegistration(customerReplace.getRegistration());

        customerReplace.setId(customerId);
        mongoTemplate.save(customerReplace);
    }
}
