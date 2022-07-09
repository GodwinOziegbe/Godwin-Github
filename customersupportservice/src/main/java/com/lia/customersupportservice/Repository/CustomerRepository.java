package com.lia.customersupportservice.Repository;

import com.lia.customersupportservice.Entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {


}
