
package com.example.carRentalBackend.Services;

import com.example.carRentalBackend.Entity.Car;
import com.example.carRentalBackend.Entity.Customer;

import com.example.carRentalBackend.Entity.Rental;
import com.example.carRentalBackend.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {



@Autowired
 private CustomerRepository customerRepository;

@Autowired
private PasswordEncoder passwordEncoder;


    public List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }

    public String editUserDetails(Long id, Customer customer) {
        for (Customer value : getCustomerList()) {

            if (id.equals(value.getId())) {

                value.setUsername(customer.getUsername());
                value.setPassword( passwordEncoder.encode(customer.getPassword()));

                customerRepository.save(value);
                return "success";
            }

        }
        return null;
    }
        public String getCurrentUser () {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();
                //Object userdet = authentication.getDetails();
                //String password=
                return currentUserName;
            }
            return null;
        }

    public String login (){
        return "success";
        }



        public Customer getMyRentalList (Long user_id){
            for (Customer value : customerRepository.findAll()) {
                if (user_id.equals(value.getId())) {
                    return value;
                }
            }
            return null;
        }


        public String addCustomer (Customer customer){

//            for (int i = 0; i < 3; i++) {
//                Faker faker = new Faker();
//                Customer customer = new Customer();
//                customer.setFirstname(faker.name().firstName());
//                customer.setLastname(faker.name().lastName());
//                customer.setEmail(faker.pokemon().name() + "@gmail.com");
//                customer.setUsername(faker.name().username());
//                //customer.setPassword(passwordConfig.passwordEncoder().encode("5678"));
//
           customer.setPassword( passwordEncoder.encode(customer.getPassword()));
            customerRepository.save(customer);


            return "success";
        }
    public Customer getCustomer (long id){
        for (Customer value : getCustomerList()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }

        return null;
    }

        public Long getCustomerId (String username){
            for (Customer value : getCustomerList()) {
                if (value.getUsername().equals(username)) {
                    return value.getId();
                }
            }

            return null;
        }

    public String CancelCustomer(Long customer_id) {


            for(Customer value: getCustomerList()){
                if(customer_id.equals(value.getId())){
                    customerRepository.delete(value);
                    return "success";
                }
            }

            return "no such rental id found!";
        }

    }


