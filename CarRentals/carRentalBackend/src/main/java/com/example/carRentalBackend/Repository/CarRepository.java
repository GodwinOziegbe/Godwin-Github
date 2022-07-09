package com.example.carRentalBackend.Repository;

import com.example.carRentalBackend.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Car, Long> {

}
