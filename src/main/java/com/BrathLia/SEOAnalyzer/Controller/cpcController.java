package com.BrathLia.SEOAnalyzer.Controller;

import com.BrathLia.SEOAnalyzer.Entities.CPC;
import com.BrathLia.SEOAnalyzer.Service.CPCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v0.1/cpc")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500", "http://localhost:63342"})
public class cpcController {

    @Autowired
    private CPCService cpcService;

    /**
     * Returns a new CPC object with the complete total of
     * sessions and revenue for user id @uId
     *
     * @param uId
     * @return
     */
    @GetMapping("/{uId}")
    public CPC totalCPC(@PathVariable long uId) {

        try {
            CPC cpc = cpcService.total(uId);
            return cpc;
        } catch (Exception e) {
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
    public CPC totalCPCByCountry(@PathVariable long uId, @RequestParam long cId) {
        try {
            CPC cpc = cpcService.total(uId, cId);
            return cpc;
        } catch (Exception e) {
            System.out.println("User id or country doesn't exist");
        }
        return null;
    }

    /**
     * Returns an unsorted list of cpc objects belonging to user from specified country.
     *
     * @param uId
     * @param cId
     * @return
     */
    @GetMapping("/list/{uId}&{cId}")
    public List<CPC> getAllCPC(@PathVariable long uId, @PathVariable long cId) {
        return cpcService.listOfCPCByCountry(uId, cId);
    }

    /**
     * Returns an unsorted list of cpc objects belonging to user.
     *
     * @param uId
     * @return
     */
    @GetMapping("/list/{uId}")
    public List<CPC> getAllCPC(@PathVariable long uId) {
        return cpcService.listOfCPC(uId);
    }

    /**
     * Returns users cpc objects in specified time interval in yyyy-MM-dd fomat
     *
     * @param uId
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/{uId}/startDate={startDate}/endDate={endDate}")
    public List<CPC> cpcByDate(@PathVariable long uId, @PathVariable String startDate, @PathVariable String endDate) {
        return cpcService.cpcByDate(uId, startDate, endDate);
    }

    /**
     * Returns day by day average from all users in specified time interval
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/average/startDate={startDate}/endDate={endDate}")
    public List<CPC> cpcAverageByDate(@PathVariable String startDate, @PathVariable String endDate) {
        return cpcService.cpcAverageByDate(startDate, endDate);
    }


}

