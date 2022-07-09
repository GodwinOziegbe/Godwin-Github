package com.example.carRentalBackend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBL_CAR")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id", nullable = false)
    private Long id;
    @Column(name = "PRICE")
    private Double dailyRentPrice;

    @Column(name = "NAME")
    private String name;

    @Column(name="TYPE")
    private String type;

    @Column(name = "MODEL")
    private String model;

    @Column(name="availability")
    private Boolean isAvailable;

    @OneToMany(mappedBy = "car",cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private List<Rental> rentalList = new ArrayList<>();

    public Car(Double dailyRentPrice, String name, String model, String type,Boolean isAvailable) {
        this.dailyRentPrice = dailyRentPrice;
        this.name = name;
        this.model = model;
        this.isAvailable = isAvailable;
        this.type=type;
    }

    public Car(Long id, Double dailyRentPrice, String name, String model, String type,Boolean isAvailable) {
        this.id = id;
        this.dailyRentPrice = dailyRentPrice;
        this.name = name;
        this.model = model;
        this.type=type;
        this.isAvailable = isAvailable;
    }

    public Car() {
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getDailyRentPrice() {
        return dailyRentPrice;
    }

    public void setDailyRentPrice(Double dailyPrice) {
        this.dailyRentPrice = dailyPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public void setRentalList(List<Rental> rentalList) {
        this.rentalList = rentalList;
    }
    @JsonIgnore
    public List<Rental> getRentalList() {
        return rentalList;
    }
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", dailyRentPrice=" + dailyRentPrice +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
