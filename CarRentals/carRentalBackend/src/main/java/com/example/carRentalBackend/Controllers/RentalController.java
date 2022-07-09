package com.example.carRentalBackend.Controllers;


import com.example.carRentalBackend.Entity.Car;
import com.example.carRentalBackend.Entity.Rental;
import com.example.carRentalBackend.Entity.Customer;
import com.example.carRentalBackend.Services.CarService;
import com.example.carRentalBackend.Services.RentalService;
import com.example.carRentalBackend.Services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class RentalController {
    private static final Logger logger = LoggerFactory.getLogger(RentalController.class);
    Car car= new Car();
    Customer customer = new Customer();
    @Autowired
    private RentalService rentalService;
    @Autowired
    private CarService carService;
    @Autowired
    private CustomerService customerService;

    @CrossOrigin("*")
    @GetMapping("/rental/myorders")
    public List<Rental> getMyOrders() {
        logger.info("Customer viewed own orders. Customer id:" );

        String user_name=customerService.getCurrentUser();
        return rentalService.getMyRentalList(user_name);
    }
    @GetMapping("/admin/allorders")
    public List<Rental> getAllOrders() {
        logger.info("Admin viewed all orders");
        return rentalService.getAllRentalList();
    }

    @PutMapping("/rental/updateorder")
    public String OrderUpdate(@RequestParam Long rental_id){
        String response=rentalService.CancelRental(rental_id);
        logger.info("Customer cancelled order with order_id:"+rental_id);
        return response;
    }
    @CrossOrigin("*")
    @PostMapping("/rental/ordercar")
    public String OrderCar(@RequestParam Integer rentDays, Double totalPrice, String user_id, String car_id){
        Long id_car=Long.parseLong(car_id);
        Long id=Long.parseLong(user_id);
        System.out.println("id="+id);
          for(Customer value: customerService.getCustomerList()){
            if(id.equals(value.getId())){
                customer = value;
                break;
            }
        }
        for(Car value: carService.getAvailables()){
            if(id_car.equals(value.getId())){
                car= value;
                break;
            }
        }
        LocalDate date = LocalDate.now();
        LocalDate returnDate=date.plusDays(rentDays);
        Rental rental= new Rental(returnDate, date,totalPrice, customer,car);
        logger.info("new car order:"+rental);
      return rentalService.rentACar(rental);

    }
}
