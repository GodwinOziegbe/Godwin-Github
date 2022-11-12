package com.BrathLia.SEOAnalyzer.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "cpc")
@Setter
@Getter
@NoArgsConstructor

/**
 * Pojo class of CPC entity
 */
public class CPC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "cpc_id")
    private long cpcId;
    private int sessions;
    @Column(columnDefinition = "Decimal(10,4)")
    private double revenue;
    private LocalDateTime dateTime;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public CPC(int sessions, double revenue) {
        this.sessions = sessions;
        this.revenue = revenue;
    }

    public CPC(double revenue) {
        this.revenue = revenue;
    }

    @JsonIgnore
    public Country getCountry() {
        return country;
    }

    @JsonIgnore
    public User getUser(){
        return user;
    }
}

