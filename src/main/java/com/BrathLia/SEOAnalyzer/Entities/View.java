package com.BrathLia.SEOAnalyzer.Entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter

/**
 * Pojo class of View entity
 * Seperate class allows for several views per user
 */
public class View {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long view;

    @ManyToOne
    private User user;


}
