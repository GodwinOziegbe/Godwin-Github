package com.BrathLia.SEOAnalyzer.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "country")
@Setter
@Getter
@NoArgsConstructor
/**
 * Pojo class of Country entity
 */
public class Country  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private long countryId;

    private String name;




    @OneToMany (mappedBy = "country", cascade = CascadeType.ALL)
    private List<Direct> directList = new ArrayList<>();
    @OneToMany (mappedBy = "country", cascade = CascadeType.ALL)
    private List<Organic> organicList = new ArrayList<>();
    @OneToMany (mappedBy = "country", cascade = CascadeType.ALL)
    private List<Referral> referralList = new ArrayList<>();
    @OneToMany (mappedBy = "country", cascade = CascadeType.ALL)
    private List<CPC> cpcList = new ArrayList<>();
  /*  @OneToMany (mappedBy = "country", cascade = CascadeType.ALL)
    private List<GoogleData> googleDataList = new ArrayList<>();

   */

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", name='" + name + '\'' +
                '}';
    }
}
