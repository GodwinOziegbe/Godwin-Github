package com.example.carRentalBackend.Services;

import com.example.carRentalBackend.Entity.Rental;
import com.example.carRentalBackend.Repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CarService carService;

    public List<Rental> getAllRentalList() {
        return rentalRepository.findAll();
    }


    public String rentACar(Rental rental){
        rentalRepository.save(rental);
        return "rental posted";
    }

    public List<Rental> getMyRentalList(String user_name) {
        List<Rental> myRentalList = new ArrayList<>();
        for (Rental value : getAllRentalList()) {
            if (user_name.equals(value.getUser().getUsername())) {
               // long user_id=value.getUser().getId();
                myRentalList.add(value);
            }
        }
            return myRentalList;

    }

    public Rental getRental(Long rental_id) {

        for (Rental value : getAllRentalList()) {
            if (value.getId().equals(rental_id)) {
               return value;

            }
        }

        return null;

    }

    public String CancelRental(Long rental_id) {

            for(Rental value: getAllRentalList()){
            if(rental_id.equals(value.getId())){
                rentalRepository.delete(value);
               return "success";
            }
        }

        return "no such rental id found!";
    }
}
