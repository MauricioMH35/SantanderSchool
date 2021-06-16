package com.school.santander.repositories;

import com.school.santander.models.Customer;


public interface CustomerRepositoryCustom {
    Customer checkUniqueValues(Customer customerCheck);
    void updateById(String id, Customer customerReplace);
}
