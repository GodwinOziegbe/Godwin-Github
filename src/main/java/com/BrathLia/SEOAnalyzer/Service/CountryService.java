package com.BrathLia.SEOAnalyzer.Service;


import com.BrathLia.SEOAnalyzer.Entities.Country;
import com.BrathLia.SEOAnalyzer.Repository.CountryDao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class CountryService implements ServiceInterface<Country> {


    @Autowired
    private CountryDao countryDao;


    private List<Country> countryList;


    @Override
    public void store(Country country) {

        countryDao.save(country);
    }

    @Override
    public void saveAll(List<Country> t) {

    }

    /**
     * Method returns a country from local list initially loaded from database
     * if the country doesn't exist in list(database) a new
     * country is created and saved to the database
     * @param name
     * @return
     */
    public Country getByName(String name) {



       for(Country c : countryList) {
           if (c.getName().equalsIgnoreCase(name)) {
               return c;
           }

       }
        Country country = new Country();
        country.setName(name);
        countryDao.save(country);
        countryList.add(country);
        return country;
    }

    /**
     * Load all countries from database to a local list.
     */
    public void setLocalList(){
        countryList = new ArrayList<>();
        countryList = countryDao.findAll();
    }
}
