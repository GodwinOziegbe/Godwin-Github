package com.BrathLia.SEOAnalyzer.Repository;

import com.BrathLia.SEOAnalyzer.Entities.SistrixData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Allows for CRUD functions
 */
public interface SistrixDataDao extends JpaRepository<SistrixData, Long> {

}
