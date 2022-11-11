package com.BrathLia.SEOAnalyzer.Controller;

import com.BrathLia.SEOAnalyzer.Entities.Direct;
import com.BrathLia.SEOAnalyzer.Service.DirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v0.1/direct")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500", "http://localhost:63342"})
public class directController {

    @Autowired
    private DirectService directService;

    /**
     * Returns a new Direct object with the complete total of
     * sessions and revenue for user id @uId
     *
     * @param uId
     * @return
     */
    @GetMapping("/{uId}")
    public Direct totalDirect(@PathVariable long uId) {

        try {
            Direct direct = directService.total(uId);
            return direct;
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
    public Direct totalDirectByCountry(@PathVariable long uId, @RequestParam long cId) {

        try {
            Direct direct = directService.total(uId, cId);
            return direct;
        } catch (Exception e) {
            System.out.println("User id or country doesn't exist");
        }

        return null;
    }

    /**
     * Returns an unsorted list of direct objects belonging to user from specified country.
     *
     * @param uId
     * @param cId
     * @return
     */
    @GetMapping("/list/{uId}&{cId}")
    public List<Direct> getAllDirect(@PathVariable long uId, @PathVariable long cId) {

        return directService.listOfDirectByCountry(uId, cId);

    }
    /**
     * Returns an unsorted list of direct objects belonging to user.
     *
     * @param uId
     * @return
     */
    @GetMapping("/list/{uId}")
    public List<Direct> getAllDirect(@PathVariable long uId) {

        return directService.listOfDirect(uId);
    }

    /**
     * Returns users direct object in specified time interval in yyyy-MM-dd format
     *
     * @param uId
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/{uId}/startDate={startDate}/endDate={endDate}")
    public List<Direct> directByDate(@PathVariable long uId, @PathVariable String startDate, @PathVariable String endDate) {
        return directService.directByDate(uId, startDate, endDate);

    }
    /**
     * Returns day by day average from all users in specified time interval
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/average/startDate={startDate}/endDate={endDate}")
    public List<Direct> directAverageByDate(@PathVariable String startDate, @PathVariable String endDate) {

        return directService.directAverageByDate(startDate, endDate);
    }




}
