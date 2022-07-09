package com.example.carRentalBackend.Controllers;

import com.example.carRentalBackend.Entity.Car;
import com.example.carRentalBackend.Services.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class CarController {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

    @CrossOrigin("*")
    @GetMapping(path = "/car/id")
    public Car getCar(Long id) {
        logger.info("Customer listed call cars");
        return carService.getCar(id);
    }

    @CrossOrigin("*")
    @GetMapping(path = "/cars")
    //@CrossOrigin(origins = {"/*","http://127.0.0.1:5500", "http://localhost:5500", "http://localhost:63342"})
    public List<Car> getCars() {
        // UserDetailsService UserTomas= new
        logger.info("Customer listed call cars");
        return carService.getAvailables();
    }

    @CrossOrigin("*")
    @PostMapping("/admin/addcar")

        public String addCar (@RequestBody Car car){
            logger.info("Admin added a car" + car);
            return carService.addCar(car);

            // return "success";
        }
        @PostMapping("/admin/addnewcar")
        public String CarNewCar (@RequestParam Double dailyRentPrice,
                                 @RequestParam String name, @RequestParam String car_type,
                                 @RequestParam String car_model,@RequestParam Boolean isAvailable){
            Car newcar= new Car(dailyRentPrice,name,car_type,car_model,isAvailable);
            String response = carService.AddNewCar(newcar);
            logger.info("Admin added a car:" + newcar);
            return response;
        }

        @DeleteMapping("/admin/deletecar")
        public ResponseEntity<Long> RemoveCar (@RequestParam Long car_id){
            boolean isRemoved = carService.RemoveCar(car_id);
            if (isRemoved) {
                logger.info("Admin removed a car with id:" + car_id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @PutMapping("/admin/updatecar")
        public String CarUpdate (@RequestParam Long car_id,@RequestParam Double dailyRentPrice,
                                 @RequestParam String name, @RequestParam String type,
                                 @RequestParam String model,@RequestParam Boolean isAvailable){
        Car newcar= new Car(dailyRentPrice,name,type,model,isAvailable);
            String response = carService.UpdateCar(car_id,newcar);
            logger.info("Admin updated a car:" + newcar);
            return response;
        }
    }
