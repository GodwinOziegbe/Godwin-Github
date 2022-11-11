package com.BrathLia.SEOAnalyzer.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "direct")
@Setter
@Getter
@NoArgsConstructor

/**
 * Pojo class of Direct entity
 */
public class Direct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "directId")
    private long directId;

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

    public Direct(int sessions, double revenue) {
        this.sessions = sessions;
        this.revenue = revenue;
    }

    public Direct(double revenue) {

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
