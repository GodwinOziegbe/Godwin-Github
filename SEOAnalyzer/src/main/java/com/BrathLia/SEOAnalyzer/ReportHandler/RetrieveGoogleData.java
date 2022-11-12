package com.BrathLia.SEOAnalyzer.ReportHandler;


import com.BrathLia.SEOAnalyzer.Entities.View;
import com.BrathLia.SEOAnalyzer.ReportHandler.GoogleAnalyticsFilter;
import com.BrathLia.SEOAnalyzer.ReportHandler.GoogleAnalyticsReport;
import com.BrathLia.SEOAnalyzer.Service.*;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RetrieveGoogleData  {

    @Autowired
    private GoogleAnalyticsReport googleAnalyticsReport;

    @Autowired
    private GoogleAnalyticsFilter googleAnalyticsFilter;

    @Autowired
    private UserService userService;

    @Autowired
    private ViewService viewService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CPCService cpcService;

    @Autowired
    private DirectService directService;

    @Autowired
    private ReferralService referralService;

    @Autowired
    private OrganicService organicService;


    /**
     * This method runs through the GoogleReport.
     * This should be automated.
     * @throws Exception
     */
    @Scheduled(cron = "0 0/10 * * * *")
   public void run() throws Exception{

        fillAllLists(); //Loads data from database and stores it locally.

        //TODO remove mockup data(users)
       //Iterate through all the users and run one report per current user
        for(int i = 0; i < userService.getUserList().size(); i++) {

        View view = viewService.findByUser_UserId(userService.getUserList().get(i).getUserId());
           try {
               //Initialises the reporting service
                AnalyticsReporting service = googleAnalyticsReport.initializeAnalyticsReporting();
                // Handles the report queries and returns a response
                GetReportsResponse response = googleAnalyticsReport.getReport(service, view);
                // Parses the response
                googleAnalyticsFilter.sort(response);
                // Extracts data from response
                googleAnalyticsFilter.filter(userService.getUserList().get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }



           System.out.println("round "  + i);  //Logging

        }

       /**
        * Stores all extracted data to database
        */
       saveLists(googleAnalyticsFilter.getServiceList());
       }


    private void saveLists(List<List> serviceList) {
        organicService.saveAll(serviceList.get(0));
        cpcService.saveAll(serviceList.get(1));
        referralService.saveAll(serviceList.get(2));
        directService.saveAll(serviceList.get(3));

    }


    private void fillAllLists() {
        userService.setLocalList(viewService);
        countryService.setLocalList();

    }



}
