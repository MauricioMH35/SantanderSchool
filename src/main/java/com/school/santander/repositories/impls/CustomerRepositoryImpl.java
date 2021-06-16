package com.school.santander.repositories.impls;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.mongodb.client.result.UpdateResult;
import com.school.santander.exceptions.BadRequestException;
import com.school.santander.exceptions.NotFoundException;
import com.school.santander.models.Customer;
import com.school.santander.repositories.CustomerRepositoryCustom;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDate;
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
        Update update = new Update();
        query.addCriteria(Criteria.where("_id").is(customerId));
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        Customer currentCustomer = mongoTemplate.findOne(query, Customer.class);

        if(currentCustomer == null)
            throw new NotFoundException("It was not possible to find the customer based on the " +
                "data provided for the query.");

//        String name = currentCustomer.getName();
//        String cpf = currentCustomer.getCpf();
//        String email = currentCustomer.getEmail();
//        String username = currentCustomer.getUsername();
//        String password = currentCustomer.getPassword();
//        String roles = currentCustomer.getRoles();
//        String tag = currentCustomer.getTag();
//        LocalDate assign = currentCustomer.getAssign();
//        Course registration = currentCustomer.getRegistration();

        if(!customerReplace.getName().isEmpty() || !customerReplace.getName().isBlank())
            customerReplace.setName(customerReplace.getName());
//            name = customerReplace.getName();

        if(!customerReplace.getCpf().isEmpty() || !customerReplace.getCpf().isBlank())
            customerReplace.setCpf(customerReplace.getCpf());
//            cpf = customerReplace.getCpf();

        if(!customerReplace.getEmail().isEmpty() || !customerReplace.getEmail().isBlank())
            customerReplace.setEmail(customerReplace.getEmail());
//            email = customerReplace.getEmail();

        if(!customerReplace.getUsername().isEmpty() || !customerReplace.getUsername().isBlank())
            customerReplace.setUsername(customerReplace.getUsername());
//            username = customerReplace.getUsername();

        if(!customerReplace.getPassword().isEmpty() || !customerReplace.getPassword().isBlank())
            customerReplace.setPassword(passwordEncoder.encode(customerReplace.getPassword()));
//            password = passwordEncoder.encode(customerReplace.getPassword());
        else if(customerReplace.getPassword() == null)
            customerReplace.setPassword(
                    passwordEncoder.encode("not_provide_password"));

        if(!customerReplace.getRoles().isEmpty() || !customerReplace.getRoles().isBlank())
            customerReplace.setRoles(customerReplace.getRoles().toLowerCase(Locale.ROOT));
//            roles = customerReplace.getRoles().toLowerCase(Locale.ROOT);

        if(!customerReplace.getTag().isEmpty() || !customerReplace.getTag().isBlank())
            customerReplace.setTag(currentCustomer.getTag().replaceAll("\\s", "_").toLowerCase(Locale.ROOT));
//            tag = customerReplace.getTag().replaceAll("\\s", "_").toLowerCase(Locale.ROOT);

        if(!currentCustomer.getAssign().equals(customerReplace.getAssign()))
            customerReplace.setAssign(currentCustomer.getAssign());
//            assign = currentCustomer.getAssign();

        if(customerReplace.getRegistration() != null)
            currentCustomer.setRegistration(customerReplace.getRegistration());
//            registration = currentCustomer.getRegistration();

//        update.set("name", name)
//                .addToSet("cpf", cpf)
//                .addToSet("email", email)
//                .addToSet("username", username)
//                .addToSet("password", password)
//                .addToSet("roles", roles)
//                .addToSet("tag", tag)
//                .addToSet("assign", assign)
//                .addToSet("registration", registration);

        customerReplace.setId(customerId);
        mongoTemplate.save(customerReplace);
//            mongoOperations.updateFirst(query, update, Customer.class);
    }
}
