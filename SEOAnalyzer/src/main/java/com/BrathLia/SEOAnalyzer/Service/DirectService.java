package com.BrathLia.SEOAnalyzer.Service;

import com.BrathLia.SEOAnalyzer.Entities.Direct;
import com.BrathLia.SEOAnalyzer.Entities.Referral;
import com.BrathLia.SEOAnalyzer.Repository.DirectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DirectService implements ServiceInterface<Direct> {

    @Autowired
    private DirectDao directDao;

    @Override
    public void store(Direct direct) {
        directDao.save(direct);
    }

    @Override
    public void saveAll(List<Direct> directList) {
        directDao.saveAll(directList);
    }

    public Direct total(long id) throws Exception {

        if(directDao.existsByUserUserId(id)) {
            Direct direct = new Direct(directDao.totalSessions(id), directDao.totalRevenue(id));


            return direct;

        } else throw new Exception();
    }

    public Direct total(long uId, long cId) throws Exception{

        if(directDao.existsByUserUserId(uId) && directDao.existsByCountry_CountryId(cId)) {

            Direct direct = new Direct(directDao.totalSessions(uId, cId), directDao.totalRevenue(uId, cId));
            return direct;
        } else throw new Exception();


    }

    public List<Direct> listOfDirectByCountry(long uId, long cId){
        return directDao.findAllByUser_UserIdAndCountry_CountryId(uId, cId);
    }

    public List<Direct> listOfDirect(long uId){
        return directDao.findAllByUser_UserId(uId);
    }

    public List<Direct> directByDate(long uId, String startDate, String endDate){
        //TODO Insert proper exception handling
        return directDao.directByDate(uId, startDate, endDate);
    }

    public List<Direct> directAverageByDate(String startDate, String endDate) {
        //TODO Insert proper exception handling
        return directDao.directAverageByDate(startDate, endDate);
    }
}
