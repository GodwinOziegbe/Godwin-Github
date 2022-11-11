package com.BrathLia.SEOAnalyzer.ReportHandler;

import com.BrathLia.SEOAnalyzer.Entities.View;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.analyticsreporting.v4.AnalyticsReportingScopes;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;

import com.google.api.services.analyticsreporting.v4.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GoogleAnalyticsReport {
    private static final String APPLICATION_NAME = "Hello Analytics Reporting";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String KEY_FILE_LOCATION = "src/main/resources/vast-falcon-311309-97ebb8e3f836.json"; // YOUR FILE HERE
    // private static final String VIEW_ID = "62054068";   BABYFACE
    // private static final String VIEW_ID = "67337545";   BRATH


    /**
     * Initializes an Analytics Reporting API V4 service object.
     *
     * @return An authorized Analytics Reporting API V4 service object.
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public AnalyticsReporting initializeAnalyticsReporting() throws GeneralSecurityException, IOException {

        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GoogleCredential credential = GoogleCredential
                .fromStream(new FileInputStream(KEY_FILE_LOCATION))
                .createScoped(AnalyticsReportingScopes.all());

        // Construct the Analytics Reporting service object.
        return new AnalyticsReporting.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME).build();
    }

    /**
     * Queries the Analytics Reporting API V4.
     *
     * @param service An authorized Analytics Reporting API V4 service object.
     * @return GetReportResponse The Analytics Reporting API V4 response.
     * @throws IOException
     */
    public GetReportsResponse getReport(AnalyticsReporting service, View view) throws IOException {
        // Create the DateRange object.
        DateRange dateRange = new DateRange();
        dateRange.setStartDate("yesterday"); //Max limit without problems is currently 190DaysAgo
        dateRange.setEndDate("yesterday");

        // Create the Metrics object.
        Metric sessions = new Metric()
                .setExpression("ga:sessions")
                .setAlias("sessions");

        Metric revenue = new Metric()
                .setExpression("ga:transactionRevenue")
                .setAlias("revenue")
                .setFormattingType("float");

        Dimension country = new Dimension()
                .setName("ga:country");

        Dimension medium = new Dimension()
                .setName("ga:medium");

        Dimension date = new Dimension()
                .setName("ga:date");

        OrderBy descendingSessions = new OrderBy()
                .setFieldName("ga:sessions")
                .setSortOrder("descending");


        // Create the ReportRequest object.
        ReportRequest request = new ReportRequest()
                .setViewId(String.valueOf(view.getView()))
                .setDateRanges(Arrays.asList(dateRange))
                .setMetrics(Arrays.asList(sessions, revenue))
                .setDimensions(Arrays.asList(country, medium, date))
                .setOrderBys(Arrays.asList(descendingSessions))
                .setIncludeEmptyRows(true);

        ArrayList<ReportRequest> requests = new ArrayList<ReportRequest>();
        requests.add(request);

        // Create the GetReportsRequest object.
        GetReportsRequest getReport = new GetReportsRequest()
                .setReportRequests(requests);


        // Call the batchGet method.
        GetReportsResponse response = service.reports().batchGet(getReport).execute();

        // Return the response.
        return response;
    }
}