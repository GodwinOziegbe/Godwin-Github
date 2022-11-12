package com.BrathLia.SEOAnalyzer.Repository;

import com.BrathLia.SEOAnalyzer.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
/**
 * Allows for CRUD functions
 */
public interface UserDao extends JpaRepository <User, Long> {


    Optional<User> findUserByEmail(String email);

    User findUserByUserId(long id);
}
