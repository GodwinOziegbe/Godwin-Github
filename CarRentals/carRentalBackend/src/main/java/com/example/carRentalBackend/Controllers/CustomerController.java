package com.example.carRentalBackend.Controllers;

import com.example.carRentalBackend.Entity.Customer;
import com.example.carRentalBackend.Services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @CrossOrigin
    @RequestMapping("/api/v1")
    public class CustomerController {

        private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
        @Autowired
        private CustomerService customerService;

        @GetMapping(path = "/login")
        public String userLogin () {
         return   customerService.login();

        }

        @GetMapping(path = "/admin/getUser")
        public String getAuthUser () {
            return   customerService.getCurrentUser();

        }
        @GetMapping("/admin/customers")
        public List<Customer> getCustomers() {
            logger.info("Admin viewed all customers");
            return customerService.getCustomerList();
        }
        @PutMapping("/admin/updatecustomer")
        public String CustomerUpdate(@RequestParam Long customer_id){
            String response=customerService.CancelCustomer(customer_id);
            logger.info("Customer cancelled order with customer_id:"+customer_id);
            return response;
        }

        @PutMapping("/admin/editcustomer")
        public String getCustomers(@RequestParam Long id, @RequestBody Customer customer) {
            logger.info("Admin edited customers");
            return customerService.editUserDetails(id, customer);
        }
        @CrossOrigin("*")
        @GetMapping("/getcustomer")
        public Customer getCustomer(@RequestParam Long id) {
            logger.info("customer requested customer detail");
            return customerService.getCustomer(id);
        }
        @CrossOrigin("*")
        @GetMapping("/getcustomerid")
        public Long  getCustomer(@RequestParam String username) {
            logger.info("customer requested customer detail");
            return customerService.getCustomerId(username);
        }
        @CrossOrigin("*")
        @PostMapping("/admin/addcustomer")
        public String addCustomer(@RequestBody Customer customer) {
            logger.info("Admin added customers:");
            return customerService.addCustomer(customer);
        }


    }
