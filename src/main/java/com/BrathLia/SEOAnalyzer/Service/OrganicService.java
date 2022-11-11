package com.BrathLia.SEOAnalyzer.Service;


import com.BrathLia.SEOAnalyzer.Entities.CPC;
import com.BrathLia.SEOAnalyzer.Entities.Organic;
import com.BrathLia.SEOAnalyzer.Repository.OrganicDao;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrganicService implements ServiceInterface<Organic>{

    @Autowired
    private OrganicDao organicDao;

    private List<Organic> organicList = new ArrayList<>();

    @Override
    public void store(Organic organic) {
        organicDao.save(organic);
    }


    @Override
    public void saveAll(List<Organic> organicList) {

        organicDao.saveAll(organicList);
    }

   public Organic total(long id) throws Exception{

        if(organicDao.existsByUserUserId(id)){
            Organic organic = new Organic(organicDao.totalSessions(id), organicDao.totalRevenue(id) );
            return organic;
        } else throw new Exception();

   }

    public Organic total(long uÍd, long cId) throws Exception{

        if(organicDao.existsByUserUserId(uÍd) && organicDao.existsByCountry_CountryId(cId)){
            Organic organic = new Organic(organicDao.totalSessions(uÍd, cId), organicDao.totalRevenue(uÍd, cId) );
            return organic;
        } else throw new Exception();

    }

    public List<Organic> listOfOrganicByCountry(long uId, long cId){
        return organicDao.findAllByUser_UserIdAndCountry_CountryId(uId, cId);
    }

    public List<Organic> listOfOrganic(long uId){

        return organicDao.findAllByUser_UserId(uId);
    }

    public List<Organic> organicByDate(long uId, String startDate, String endDate){
        //TODO Insert proper exception handling
        return organicDao.organicByDate(uId, startDate, endDate);
    }
    public List<Organic> organicAverageByDate(String startDate, String endDate) {
        //TODO Insert proper exception handling
        return organicDao.organicAverageByDate(startDate, endDate);
    }
}
