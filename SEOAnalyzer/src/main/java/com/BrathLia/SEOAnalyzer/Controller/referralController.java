package com.BrathLia.SEOAnalyzer.Controller;
import com.BrathLia.SEOAnalyzer.Entities.Referral;
import com.BrathLia.SEOAnalyzer.Service.ReferralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v0.1/referral")
@CrossOrigin(origins = {"http://127.0.0.1:5500","http://localhost:5500", "http://localhost:63342"})
public class referralController {

    @Autowired
    private ReferralService referralService;


    /**
     * Returns a new referral object with the complete total of
     * sessions and revenue for user id @uId
     *
     * @param uId
     * @return
     */
    @GetMapping("/{uId}")
    public Referral totalReferral(@PathVariable long uId){

        try{
            Referral referral = referralService.total(uId);
            return referral;
        } catch (Exception e){
            System.out.println("User doesn't exist" + e);
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
    public Referral totalReferralByCountry(@PathVariable long uId, @RequestParam long cId){

        try {
            Referral referral = referralService.total(uId, cId);
            return referral;
        } catch (Exception e){
            System.out.println("User id or country doesn't exist");
        }

        return null;
    }

    /**
     * Returns an unsorted list of referral objects belonging to user from specified country.
     *
     * @param uId
     * @param cId
     * @return
     */
    @GetMapping("/list/{uId}&{cId}")
    public List<Referral> getAllReferral(@PathVariable long uId, @PathVariable long cId ){

        return referralService.listOfReferralByCountry(uId, cId);

    }

    /**
     * Returns an unsorted list of referral objects belonging to user.
     *
     * @param uId
     * @return
     */
    @GetMapping("/list/{uId}")
    public List<Referral> getAllReferral(@PathVariable long uId){

        return referralService.listOfReferral(uId);
    }

    /**
     * Returns users referral objects in specified time interval in yyyy-MM-dd fomat
     *
     * @param uId
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping ("/{uId}/startDate={startDate}/endDate={endDate}")
    public List<Referral> referralByDate(@PathVariable long uId, @PathVariable String startDate, @PathVariable String endDate){
        return referralService.referralByDate(uId, startDate, endDate);

    }

    /**
     * Returns day by day average from all users in specified time interval
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping ("/average/startDate={startDate}/endDate={endDate}")
    public List<Referral> referralAverageByDate(@PathVariable String startDate, @PathVariable String endDate){
        return referralService.referralAverageByDate(startDate, endDate);
    }

}
