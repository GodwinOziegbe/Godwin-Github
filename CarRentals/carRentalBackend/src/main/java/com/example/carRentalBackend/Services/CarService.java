package com.example.carRentalBackend.Services;


import com.example.carRentalBackend.Entity.Car;
import com.example.carRentalBackend.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;


    public String addCar(Car car){
        carRepository.save(car);
        return("success");
    }

    public List<Car> getAvailables() {

        List<Car>mycarList=new ArrayList<>();

        for (Car value: carRepository.findAll()) {
              if (value.getIsAvailable()) {
                mycarList.add(value);

            }
        }
        return mycarList;
       // return carRepository.findAll();
    }

        public Boolean RemoveCar(Long car_id) {

        for(Car value: carRepository.findAll()){
            if(car_id.equals(value.getId())){
                value.setIsAvailable(false);//here we set availability to "no", so that the car can still be seen in the database, instead of the deleting the post
                carRepository.save(value);
                return true;
            }
        }

        return false;
    }

    public String UpdateCar(Long id, Car car) {

        for(Car value: carRepository.findAll()){

            if(id.equals(value.getId())){
                value.setIsAvailable(car.getIsAvailable());
                value.setDailyRentPrice(car.getDailyRentPrice());
                value.setName(car.getName());
                value.setModel(car.getModel());
                value.setType(car.getType());
                carRepository.save(value);
                return "success";
            }
        }

        return "no such car exists";
    }

    public Car getCar(Long id) {
        return null;
    }

    public String AddNewCar(Car newcar) {
        carRepository.save(newcar);
        return "success";
    }
}
