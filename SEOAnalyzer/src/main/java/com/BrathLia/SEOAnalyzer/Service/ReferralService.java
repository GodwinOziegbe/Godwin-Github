package com.BrathLia.SEOAnalyzer.Service;

import com.BrathLia.SEOAnalyzer.Entities.Referral;
import com.BrathLia.SEOAnalyzer.Repository.ReferralDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReferralService implements ServiceInterface<Referral> {

    @Autowired
    private ReferralDao referralDao;

    private List<Referral> referralList = new ArrayList<>();

    @Override
    public void store(Referral referral) {
        referralDao.save(referral);
    }

    @Override
    public void saveAll(List<Referral> referralList) {
        referralDao.saveAll(referralList);
    }


    public Referral total(long id) throws Exception{

        if(referralDao.existsByUserUserId(id)){
            Referral referral = new Referral(referralDao.totalSessions(id), referralDao.totalRevenue(id) );
            return referral;
        } else throw new Exception();
    }


    public Referral total(long uId, long cId) throws Exception{

        if(referralDao.existsByUserUserId(uId) && referralDao.existsByCountry_CountryId(cId)){
            Referral referral = new Referral(referralDao.totalSessions(uId, cId), referralDao.totalRevenue(uId, cId) );
            return referral;
        } else throw new Exception();
    }

    public List<Referral> listOfReferralByCountry(long uId, long cId){
        return referralDao.findAllByUser_UserIdAndCountry_CountryId(uId, cId);
    }

    public List<Referral> listOfReferral(long uId){
        return referralDao.findAllByUser_UserId(uId);
    }

    public List<Referral> referralByDate(long uId, String startDate, String endDate){
        //TODO Insert proper exception handling
        return referralDao.referralByDate(uId, startDate, endDate);
    }

    public List<Referral> referralAverageByDate(String startDate, String endDate) {
        //TODO Insert proper exception handling
        return referralDao.referralAverageByDate(startDate, endDate);
    }
}
