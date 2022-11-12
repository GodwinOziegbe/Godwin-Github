package com.BrathLia.SEOAnalyzer.Service;

import com.BrathLia.SEOAnalyzer.Entities.CPC;
import com.BrathLia.SEOAnalyzer.Repository.CPCDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CPCService implements ServiceInterface<CPC> {

    @Autowired
    private CPCDao cpcDao;

    private List<CPC> cpcList = new ArrayList<>();

    @Override
    public void store(CPC cpc) {
        cpcDao.save(cpc);
    }

    @Override
    public void saveAll(List<CPC> cpcList) {
        cpcDao.saveAll(cpcList);
    }

    /**
     * Creates a new CPC object containing users total sessions and revenue.
     * Throws exception if user it not found
     *
     * Not an optimal way to handle this, use Optional<CPC> instead.
     *
     * Same for other service-classes
     * @param id
     * @return
     * @throws Exception
     */
    public CPC total(long id) throws Exception  {

        if(cpcDao.existsByUserUserId(id)) {

            CPC cpc = new CPC(cpcDao.totalSessions(id), cpcDao.totalRevenue(id));



            return cpc;
        } else throw new Exception();

    }

    public CPC total(long id, long cId) throws Exception  {

        if(cpcDao.existsByUserUserId(id) && cpcDao.existsByCountry_CountryId(cId)) {
            CPC cpc = new CPC(cpcDao.totalSessions(id, cId), cpcDao.totalRevenue(id, cId));
            return cpc;

        } else throw new Exception();


    }
    public List<CPC> listOfCPCByCountry(long uId, long cId){

        return cpcDao.findAllByUser_UserIdAndCountry_CountryId(uId, cId);
    }

    public List<CPC> listOfCPC(long uId){

        return cpcDao.findAllByUser_UserId(uId);
    }

    public List<CPC> cpcByDate(long uId, String startDate, String endDate){
        //TODO Insert proper exception handling
        return cpcDao.cpcByDate(uId, startDate, endDate);
    }


    public List<CPC> cpcAverageByDate(String startDate, String endDate){
        //TODO Insert proper exception handling
        return cpcDao.cpcAverageByDate(startDate, endDate);
    }


}
