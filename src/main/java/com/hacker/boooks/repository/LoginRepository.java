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

}
