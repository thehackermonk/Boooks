package com.hacker.boooks.repository;

import com.hacker.boooks.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author [@thehackermonk]
 * @apiNote Repository for 'login' table
 * @since 1.0
 */
public interface LoginRepository extends JpaRepository<LoginEntity, String> {

    /**
     * @param userName User ID of the user
     * @return User details
     * @apiNote Get user details while username is provided
     * @since 1.0
     */
    @Query(value = "SELECT * FROM `login` WHERE username=?1", nativeQuery = true)
    LoginEntity getLoginByUsername(String userName);

    /**
     * @param userName User ID of the user
     * @param token    access token provided to user
     * @return User details
     * @apiNote Get user details while username and access token is provided
     * @since 1.0
     */
    @Query(value = "SELECT * FROM `login` WHERE username=?1 AND token=?2", nativeQuery = true)
    LoginEntity getLoginByUsernameAndToken(String userName, String token);

}
