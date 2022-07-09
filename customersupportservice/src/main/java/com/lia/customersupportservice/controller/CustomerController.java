package com.lia.customersupportservice.controller;

import com.lia.customersupportservice.Entities.Customer;

import com.lia.customersupportservice.services.CustomerService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class CustomerController {


        private CustomerService customerService;

        @GetMapping("/getcustomers")
        public List<Customer> fetchAllCustomers() {

            return customerService.getAllCustomers();
        }

        @PostMapping("/addcustomer")
        public String addingCustomer(@RequestBody Customer customer){

            return customerService.addCustomer(customer);
        }
}
