package com.BrathLia.SEOAnalyzer.Repository;

import com.BrathLia.SEOAnalyzer.Entities.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Allows for CRUD functions
 */
public interface ViewDao extends JpaRepository<View, Long> {

    View findByUser_UserId(long id);



}
