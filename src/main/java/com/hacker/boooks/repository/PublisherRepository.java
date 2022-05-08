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
public interface PublisherRepository extends JpaRepository<PublisherEntity, Integer> {

    /**
     * @return Return the last publisher ID or return 0 if empty
     * @apiNote Get the last publisher ID
     * @since 1.0
     */
    @Query(value = "SELECT IFNULL(MAX(publisher_id),0) FROM publisher", nativeQuery = true)
    int getLastPublisherID();

    /**
     * @param publisher Publication name
     * @return Publication details
     * @apiNote Get publication details while publication name is provided
     * @since 1.0
     */
    @Query(value = "SELECT * FROM publisher WHERE name=?1", nativeQuery = true)
    PublisherEntity getPublisher(String publisher);

    /**
     * @param keyword search keyword
     * @return Publishers who have the keyword in their name
     * @apiNote Get pblishers who have the keyword in their name
     * @since 1.0
     */
    @Query(value = "SELECT * FROM `publisher` WHERE LOWER(`name`) LIKE LOWER(?1)", nativeQuery = true)
    List<PublisherEntity> searchPublisher(String keyword);

}
