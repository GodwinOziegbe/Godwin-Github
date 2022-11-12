package com.BrathLia.SEOAnalyzer.ReportHandler;

import com.BrathLia.SEOAnalyzer.Entities.*;
import com.BrathLia.SEOAnalyzer.Entities.User;

import com.BrathLia.SEOAnalyzer.Repository.CountryDao;
import com.BrathLia.SEOAnalyzer.Service.*;
import com.github.javafaker.Faker;
import com.google.api.services.analyticsreporting.v4.model.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class parses the results from Google Analytics report.
 *
 */

@Component
public class GoogleAnalyticsFilter {

    @Autowired
    private CountryService countryService;
    private List<String> dimensions = new ArrayList<>();
    private List<DateRangeValues> metrics = new ArrayList<>();
    private HashMap<List<String>, List<DateRangeValues>> reportMap = new HashMap<>();
    private List<Organic> organicList = new ArrayList<>();
    private List<CPC> cpcList = new ArrayList<>();
    private List<Referral> referralList = new ArrayList<>();
    private List<Direct> directList= new ArrayList<>();
    private List<List> serviceList = new ArrayList<>();


    /**
     * This method sorts out the dimensions and metrics into reportMap
     * @param response
     */
    public void sort(GetReportsResponse response) {


        for (Report report : response.getReports()) {

            List<ReportRow> rows = report.getData().getRows();

            if (rows == null) {
                System.out.println("No data found for ");
                return;
            }

            for (ReportRow row : rows) {
                dimensions = row.getDimensions();
                metrics = row.getMetrics();

                reportMap.put(dimensions, metrics);


            }
        }
    }

    /**
     * This method filters out the reportMap and separates each entity(country, cpc etc.)
     * and finally stores them in database
     * @param user
     */
    public void filter(User user){

        for(Map.Entry<List<String>, List<DateRangeValues>> map : reportMap.entrySet()) {

            //Use this method instead if you want to store hours as well
            //LocalDateTime responseDateTime = LocalDateTime.parse(map.getKey().get(2), DateTimeFormatter.ofPattern("yyyyMMddHH"));

            LocalDate responseDate = LocalDate.parse(map.getKey().get(2), DateTimeFormatter.ofPattern("yyyyMMdd"));


            Country country = countryService.getByName(map.getKey().get(0));


            switch (map.getKey().get(1)) {
                case "organic" -> {


                    Organic organic = new Organic();
                    organic.setDate(responseDate);
                    organic.setCountry(country);
                    organic.setSessions(Integer.parseInt(map.getValue().get(0).getValues().get(0)));
                    organic.setRevenue(Double.parseDouble(map.getValue().get(0).getValues().get(1)));
                    organic.setUser(user);

                    organicList.add(organic);


                }
                case "cpc" -> {


                    CPC cpc = new CPC();
                    cpc.setDate(responseDate);
                    cpc.setCountry(country);
                    cpc.setSessions(Integer.parseInt(map.getValue().get(0).getValues().get(0)));
                    cpc.setRevenue(Double.parseDouble(map.getValue().get(0).getValues().get(1)));
                    cpc.setUser(user);

                    cpcList.add(cpc);
                }
                case "referral" -> {


                    Referral referral = new Referral();
                    referral.setDate(responseDate);
                    referral.setCountry(country);
                    referral.setSessions(Integer.parseInt(map.getValue().get(0).getValues().get(0)));
                    referral.setRevenue(Double.parseDouble(map.getValue().get(0).getValues().get(1)));
                    referral.setUser(user);

                    referralList.add(referral);

                }
                case "(none)" -> {


                    Direct direct = new Direct();
                    direct.setDate(responseDate);
                    direct.setCountry(country);
                    direct.setSessions(Integer.parseInt(map.getValue().get(0).getValues().get(0)));
                    direct.setRevenue(Double.parseDouble(map.getValue().get(0).getValues().get(1)));
                    direct.setUser(user);

                    directList.add(direct);

                }


            }
        }
        


    }


    public List<List> getServiceList(){
        serviceList.add(organicList);
        serviceList.add(cpcList);
        serviceList.add(referralList);
        serviceList.add(directList);

        return serviceList;
    }


}