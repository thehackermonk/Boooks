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

    /**
     * @return Return the last publisher ID or return 0 if empty
     * @apiNote Get the last publisher ID
     * @since 1.0
     */
    @Query(value = "SELECT IFNULL(MAX(sl_no),0) FROM log", nativeQuery = true)
    int getLastSlNo();

    /**
     * @param bookID       Unique ID of the book
     * @param membershipID Unique ID of the member
     * @return Unreturned book when book ID and membership ID is given
     * @apiNote Get unreturned book when book ID and membership ID is given
     * @since 1.0
     */
    @Query(value = "SELECT * FROM `log` WHERE `book_id`=?1 AND `membership_id`=?2 AND `return_date` IS NULL", nativeQuery = true)
    LogEntity getUnreturnedBookLog(int bookID, int membershipID);

    /**
     * @param membershipID Unique ID of the member
     * @return Unreturned books when membership ID is given
     * @apiNote Get unreturned books when membership ID is given
     * @since 1.0
     */
    @Query(value = "SELECT * FROM `log` WHERE `membership_id`=?1 AND `return_date` IS NULL", nativeQuery = true)
    List<LogEntity> getUnreturnedBooks(int membershipID);

    /**
     * @param bookID Unique ID of the book
     * @return Unique ID of the member who holds the book
     * @apiNote Get unique ID of the member who holds the book
     * @since 1.0
     */
    @Query(value = "SELECT `membership_id` FROM `log` WHERE `book_id`=?1 AND `return_date` IS NULL", nativeQuery = true)
    int whoHoldsTheBook(int bookID);

    /**
     * @param bookID Unique ID of the book
     * @return no. of times the book is present in the log
     * @apiNote Get no. of times the book is present in the log
     * @since 1.0
     */
    @Query(value = "SELECT COUNT(*) FROM `log` WHERE `book_id`=?1", nativeQuery = true)
    int getBookCount(int bookID);

    /**
     * @param membershipID Unique ID of the member
     * @return Unique ID books that the member read
     * @apiNote Get books that the member read
     * @since 1.0
     */
    @Query(value = "SELECT `book_id` FROM `log` WHERE `membership_id`=?1", nativeQuery = true)
    List<Integer> getBooksReadBy(int membershipID);

}
