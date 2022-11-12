package com.BrathLia.SEOAnalyzer.Service;

import com.BrathLia.SEOAnalyzer.Auth.PasswordConfig;
import com.BrathLia.SEOAnalyzer.Entities.View;
import com.BrathLia.SEOAnalyzer.Repository.ViewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ViewService implements ServiceInterface<View> {

    @Autowired
    private ViewDao viewDao;


    @Override
    public void store(View view) {
        viewDao.save(view);
    }

    @Override
    public void saveAll(List<View> t) {

        viewDao.saveAll(t);
    }

    public View findByUser_UserId(long id){
        return  viewDao.findByUser_UserId(id);


    }
}
