package com.example.carRentalBackend.Repository;


import com.example.carRentalBackend.Entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental,Long> {
}
