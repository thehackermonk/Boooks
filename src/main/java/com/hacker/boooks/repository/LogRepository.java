package com.hacker.boooks.repository;

import com.hacker.boooks.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author [@thehackermonk]
 * @apiNote Repository for 'log' table
 * @since 1.0
 */
public interface LogRepository extends JpaRepository<LogEntity, Integer> {

    int countByBookId(int bookID);
}
