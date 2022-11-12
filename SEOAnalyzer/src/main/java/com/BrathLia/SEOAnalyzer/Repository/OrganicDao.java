package com.BrathLia.SEOAnalyzer.Repository;

import com.BrathLia.SEOAnalyzer.Entities.CPC;
import com.BrathLia.SEOAnalyzer.Entities.Organic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
/**
 * Allows for CRUD functions and custom querys when talking to database
 */
public interface OrganicDao extends JpaRepository<Organic, Long> {

    //Total sessions per organic from user_id
    @Query(value = "select sum(sessions) from organic where user_id = :id", nativeQuery = true)
    int totalSessions(@Param("id") long id);

    //Total sessions per organic and per country_id from user_id
    @Query (value = "select sum(sessions) from organic where user_id = :uId and country_id = :cId", nativeQuery = true)
    int totalSessions(@Param("uId") long uId, @Param("cId") long cId);

    //Total revenue per organic from user_id
    @Query (value = "Select sum(revenue) from organic where user_id = :id", nativeQuery = true)
    double totalRevenue(@Param("id") long id);

    //Total revenue per organic and per country_id from user_id
    @Query (value = "select sum(revenue) from organic where user_id = :uId and country_id = :cId", nativeQuery = true)
    double totalRevenue(@Param("uId") long uId, @Param("cId") long cId);

    boolean existsByUserUserId(long id);

    boolean existsByCountry_CountryId(long cId);

    List<Organic> findAllByUser_UserIdAndCountry_CountryId(long uId, long cId);

    List<Organic> findAllByUser_UserId(long uId);

    /**
     * Returns a list of CPC objects from set of "days" from userID
     * @param uId
     * @param days
     * @return
     */
    @Query (value ="select organic_id, country_id, date_time, user_id ,sum(sessions)/(select count(user_id) from user ) as sessions, " +
            "sum(revenue)/(select count(user_id) from user ) as revenue, " +
            "date from organic where user_id = :uId and Date BETWEEN :startDate and :endDate " +
            "group by date ORDER BY date;", nativeQuery = true)
    List<Organic> organicByDate(@Param("uId") long uId, @Param("startDate") String startDate, @Param ("endDate") String endDate);


    @Query (value = "select organic_id, country_id, date_time, user_id ,sum(sessions)/(select count(user_id) from user ) as sessions, " +
            "sum(revenue)/(select count(user_id) from user ) as revenue," +
            "date from organic where Date BETWEEN :startDate and :endDate " +
            "group by date ORDER BY date;", nativeQuery = true)
    List<Organic> organicAverageByDate(@Param("startDate") String startDate, @Param ("endDate") String endDate);

}
