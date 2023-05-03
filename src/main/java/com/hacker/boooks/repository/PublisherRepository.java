package com.hacker.boooks.repository;

import com.hacker.boooks.entity.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author [@thehackermonk]
 * @apiNote Repository for 'publisher' table
 * @since 1.0
 */
public interface PublisherRepository extends JpaRepository<PublisherEntity, String> {

    /**
     * @return Return the last publisher ID or return 0 if empty
     * @apiNote Get the last publisher ID
     * @since 1.0
     */
    @Query(value = "SELECT IFNULL(MAX(publisher_id),0) FROM publisher", nativeQuery = true)
    int getLastPublisherID();

}
