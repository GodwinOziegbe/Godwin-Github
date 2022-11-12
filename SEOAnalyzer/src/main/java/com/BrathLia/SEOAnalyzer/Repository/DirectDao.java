package com.BrathLia.SEOAnalyzer.Repository;

import com.BrathLia.SEOAnalyzer.Entities.CPC;
import com.BrathLia.SEOAnalyzer.Entities.Direct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
/**
 * Allows for CRUD functions and custom querys when talking to database
 */
public interface DirectDao extends JpaRepository<Direct, Long> {

    //Total sessions per direct from user_id
    @Query (value = "select sum(sessions) from direct where user_id = :id", nativeQuery = true)
    int totalSessions(@Param("id") long id);

     //Total sessions per direct and per country_id from user_id
    @Query (value = "select sum(sessions) from direct where user_id = :uId and country_id = :cId", nativeQuery = true)
    int totalSessions(@Param("uId") long uId, @Param("cId") long cId);

    //Total revenue per direct from user_id
    @Query (value = "Select sum(revenue) from direct where user_id = :id", nativeQuery = true)
    double totalRevenue(@Param("id") long id);

    //Total revenue per direct and per country_id from user_id
    @Query (value = "select sum(revenue) from direct where user_id = :uId and country_id = :cId", nativeQuery = true)
    double totalRevenue(@Param("uId") long uId, @Param("cId") long cId);

    boolean existsByUserUserId(long id);

    boolean existsByCountry_CountryId(long cId);

    List<Direct> findAllByUser_UserIdAndCountry_CountryId(long uId, long cId);

    List<Direct> findAllByUser_UserId(long uId);

    /**
     * Returns a list of CPC objects from set of "days" from userID
     * @param uId
     * @param days
     * @return
     */
    @Query (value ="select direct_id, country_id, date_time, sum(sessions) as sessions, user_id, sum(revenue) as revenue,date from direct " +
            "where user_id=:uId and Date BETWEEN :startDate and :endDate " +
            "group by date ORDER BY date;", nativeQuery = true)
    List<Direct> directByDate(@Param("uId") long uId, @Param("startDate") String startDate, @Param("endDate") String endDate);



    @Query (value = "select direct_id, country_id, date_time, user_id ,sum(sessions)/(select count(user_id) from user ) as sessions, " +
            " sum(revenue)/(select count(user_id) from user ) as revenue " +
            " ,date from direct where  Date BETWEEN :startDate and :endDate  " +
            "group by date ORDER BY date;", nativeQuery = true)
    List<Direct> directAverageByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);



}
