package com.lia.customersupportservice.services;


import com.lia.customersupportservice.Entities.Customer;

import com.lia.customersupportservice.Repository.CustomerRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CustomerService {



    private CustomerRepository customerRepository;

        public List<Customer> getAllCustomers() {

            return customerRepository.findAll();
        }

        public String addCustomer(Customer customer) {
            customerRepository.save(customer);
            return "success";
        }


}
