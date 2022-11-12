package com.BrathLia.SEOAnalyzer.Controller;

import com.BrathLia.SEOAnalyzer.Entities.Organic;
import com.BrathLia.SEOAnalyzer.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v0.1/organic")
@CrossOrigin(origins = {"http://127.0.0.1:5500","http://localhost:5500", "http://localhost:63342"})
public class organicController {

    @Autowired
    private OrganicService organicService;

    /**
     * Returns a new organic object with the complete total of
     * sessions and revenue for user id @uId
     *
     * @param uId
     * @return
     */
    @GetMapping("/{uId}")
    public Organic totalOrganic(@PathVariable long uId){
        try{
            return organicService.total(uId);
        } catch (Exception e){
        }
        return null;
    }

    /**
     * Same as above but with specified country "/1/?cId=1"
     *
     * @param uId
     * @param cId
     * @return
     */
    @GetMapping("/{uId}/")
    public Organic totalOrganicByCountry(@PathVariable long uId, @RequestParam long cId){
        try {
            return organicService.total(uId, cId);
        } catch (Exception e){
        }
        return null;
    }

    /**
     * Returns an unsorted list of organic objects belonging to user from specified country.
     *
     * @param uId
     * @param cId
     * @return
     */
    @GetMapping("/list/{uId}&{cId}")
    public List<Organic> getAllOrganic(@PathVariable long uId, @PathVariable long cId ){
        return organicService.listOfOrganicByCountry(uId, cId);
    }

    /**
     * Returns an unsorted list of organic objects belonging to user.
     *
     * @param uId
     * @return
     */
    @GetMapping("/list/{uId}")
    public List<Organic> getAllOrganic(@PathVariable long uId){
        return organicService.listOfOrganic(uId);
    }

    /**
     * Returns users organic objects in specified time interval in yyyy-MM-dd fomat
     *
     * @param uId
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping ("/{uId}/startDate={startDate}/endDate={endDate}")
    public List<Organic> organicByDate(@PathVariable long uId, @PathVariable String startDate, @PathVariable String endDate){
        return organicService.organicByDate(uId, startDate, endDate);
    }

    /**
     * Returns day by day average from all users in specified time interval
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping ("/average/startDate={startDate}/endDate={endDate}")
    public List<Organic> organicAverageByDate( @PathVariable String startDate, @PathVariable String endDate){
        return organicService.organicAverageByDate(startDate, endDate);
    }



}
