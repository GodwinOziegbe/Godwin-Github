package com.example.carRentalBackend.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
    public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long id;

    @Column(name="FIRSTNAME")
    private String firstname;

    @Column(name="LASTNAME")
    private String lastname;


    private String email;


    private String username;


    private String password;

    public Customer() {
    }

    public Customer(Long id, String firstname, String lastname, String email, String username, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Customer(String firstname, String lastname, String email, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Customer(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String f_name) {
        this.firstname = f_name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String l_name) {
        this.lastname = l_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String e_mail) {
        this.email = e_mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRentalsList(List<Rental> rentalList) {
        this.rentalList = rentalList;
    }


    @OneToMany (mappedBy = "customer",cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private List<Rental> rentalList = new ArrayList<>();

    @JsonIgnore
    public List<Rental> getRentalsList() {
        return rentalList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
