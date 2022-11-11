package com.BrathLia.SEOAnalyzer.Service;

import com.google.api.services.analyticsreporting.v4.model.DateRangeValues;

import java.util.List;

public interface ServiceInterface <T> {
    public void store(T t);

    public void saveAll(List<T> t);




}
