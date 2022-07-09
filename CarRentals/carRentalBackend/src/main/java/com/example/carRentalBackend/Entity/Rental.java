package com.example.carRentalBackend.Entity;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "TBL_RENTALS")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id", nullable = false)
    private Long id;

    @Column(name="order_date")
    private LocalDate orderDate;

    @Column(name="RETURN_DATE")
    private LocalDate returnDate;


    @Column(name="RENTED_DATE")
    private LocalDate rentedDate;

    @Column(name="TOTAL_PRICE")
    private Double totalPrice;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Rental(LocalDate returnDate, LocalDate rentedDate, Double totalPrice, Customer customer, Car car) {
        this.returnDate = returnDate;
        this.rentedDate = rentedDate;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.car = car;
    }

    public Rental(Long id, LocalDate returnDate, LocalDate rentedDate, Double totalPrice, Car car, Customer customer) {
        this.id = id;
        this.returnDate = returnDate;
        this.rentedDate = rentedDate;
        this.totalPrice = totalPrice;
        this.car = car;
        this.customer = customer;
    }

    public Rental() {
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(LocalDate rentedDate) {
        this.rentedDate = rentedDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getUser() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", returnDate=" + returnDate +
                ", rentedDate=" + rentedDate +
                ", totalPrice=" + totalPrice +
                ", orderDate=" + orderDate +
                ", car=" + car +
                ", customer=" + customer +
                '}';
    }
}
