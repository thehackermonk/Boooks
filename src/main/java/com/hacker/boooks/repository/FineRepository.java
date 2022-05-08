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

    /**
     * @return No. of days
     * @apiNote No. of days after which fine is charged
     * @since 1.0
     */
    @Query(value = "SELECT `days_for_fine` FROM `fine`", nativeQuery = true)
    int getDaysAfterFineIsCharged();

    /**
     * @return Amount
     * @apiNote Amount to be paid per day
     * @since 1.0
     */
    @Query(value = "SELECT `amount` FROM `fine`", nativeQuery = true)
    int getFineAmount();

}
