package com.BrathLia.SEOAnalyzer.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Setter
@Getter
@NoArgsConstructor

/**
 * Pojo class of User entity
 */
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;



    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<SistrixData> sistrixDataList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CPC> cpcList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Direct> directList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Organic> organicList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Referral> referralList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<View> viewList = new ArrayList<>();



    @JsonIgnore
    public long getUserId() {
        return userId;
    }
    @JsonIgnore
    public List<SistrixData> getSistrixDataList() {
        return sistrixDataList;
    }
    @JsonIgnore
    public List<CPC> getCpcList() {
        return cpcList;
    }
    @JsonIgnore
    public List<Direct> getDirectList() {
        return directList;
    }
    @JsonIgnore
    public List<Organic> getOrganicList() {
        return organicList;
    }
    @JsonIgnore
    public List<Referral> getReferralList() {
        return referralList;
    }
    @JsonIgnore
    public List<View> getViewList() {
        return viewList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}' + "\n";
    }
}
