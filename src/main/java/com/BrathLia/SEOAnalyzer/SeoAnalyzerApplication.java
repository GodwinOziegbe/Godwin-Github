package com.BrathLia.SEOAnalyzer;


import com.BrathLia.SEOAnalyzer.ReportHandler.GoogleAnalyticsReport;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.BrathLia.SEOAnalyzer.Controller",
		"com.BrathLia.SEOAnalyzer.Service",
		"com.BrathLia.SEOAnalyzer.Repository",
		"com.BrathLia.SEOAnalyzer.ReportHandler",
		"com.BrathLia.SEOAnalyzer"})
public class SeoAnalyzerApplication {

	public static void main(String[] args) {


		SpringApplication.run(SeoAnalyzerApplication.class, args);
	}

}
