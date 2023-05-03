package com.hacker.boooks.repository;

import com.hacker.boooks.entity.FineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author [@thehackermonk]
 * @apiNote Repository for 'fine' table
 * @since 1.0
 */
public interface FineRepository extends JpaRepository<FineEntity, Integer> {

}
