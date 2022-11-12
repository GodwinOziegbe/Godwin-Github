package com.BrathLia.SEOAnalyzer.Controller;

import com.BrathLia.SEOAnalyzer.Auth.AuthenticatedUser;
import com.BrathLia.SEOAnalyzer.Entities.*;

import com.BrathLia.SEOAnalyzer.ReportHandler.RetrieveGoogleData;
import com.BrathLia.SEOAnalyzer.Repository.UserDao;
import com.BrathLia.SEOAnalyzer.Service.*;
import com.google.api.client.json.webtoken.JsonWebToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v0.1")
@CrossOrigin(origins = {"http://127.0.0.1:5500","http://localhost:5500", "http://localhost:63342"})

public class MainController {

    @Autowired
    private RetrieveGoogleData retrieveGoogleData;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CPCService cpcService;

    @Autowired
    private DirectService directService;

    @Autowired
    private ReferralService referralService;

    @Autowired
    private OrganicService organicService;


    /**
     * A quick hack to retrieve data from google via API
     * This process should be refactored, automated and not run inside the api
     * @return
     */
    @GetMapping("/getGoogle")
    public @ResponseBody String runGoogleData(){
        try{
            retrieveGoogleData.run();
        } catch (Exception e){
           return "Retreive google data failed" + e;
        }
        return "google data retrieved";
    }


    /**
     * Depricated
     * Simply returns a list of user in database
     * @return
     */
    @GetMapping("/userList")
    public List<User> userList(){
        return userService.userList();
    }

    /**
     * A security hack to retrieve id from currently logged in user
     * Should definitely not be available through api
     * @return
     */
    @GetMapping("/token")
    public long token(){
        return userService.getAuthenticatedUser().getId();
    }


}
