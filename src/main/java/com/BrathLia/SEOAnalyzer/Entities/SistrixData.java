package com.BrathLia.SEOAnalyzer.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sistrix_data")
@Setter
@Getter
@NoArgsConstructor

/**
 * Pojo class of Sistrix entity
 * Not currently used
 */
public class SistrixData {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "sistrix_data_id")
    private long sistrixDataId;

    private int score;



    @ManyToOne
    @JoinColumn (name ="user_id")
    private User user;

    @Override
    public String toString() {
        return "SistrixData{" +
                "sistrixDataId=" + sistrixDataId +
                ", score=" + score +
                '}';
    }
}
