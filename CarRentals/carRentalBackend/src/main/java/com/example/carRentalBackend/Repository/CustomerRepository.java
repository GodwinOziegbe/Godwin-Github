package com.example.carRentalBackend.Repository;


import com.example.carRentalBackend.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {


}
