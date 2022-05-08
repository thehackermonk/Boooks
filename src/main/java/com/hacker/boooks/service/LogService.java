package com.hacker.boooks.service;

import com.hacker.boooks.bean.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @apiNote Service class for logs
 */
@Service
public interface LogService {

    /**
     * @apiNote Get next sl. no.
     * @author [@thehackermonk]
     * @since 1.0
     */
    int getNextSlNo();

    /**
     * @apiNote Get all logs
     * @author [@thehackermonk]
     * @since 1.0
     */
    List<Log> getLogs();

    /**
     * @apiNote Get log of unreturned book
     * @author [@thehackermonk]
     * @since 1.0
     */
    Log getUnreturnedBookLog(int bookID, int membershipID);

    /**
     * @apiNote Get unreturned books
     * @author [@thehackermonk]
     * @since 1.0
     */
    List<Integer> getUnreturnedBooks(int membershipID);

    /**
     * @apiNote Add log
     * @author [@thehackermonk]
     * @since 1.0
     */
    void addLog(int slNo, int bookID, int membershipID, LocalDate issueDate);

    /**
     * @apiNote Update log
     * @author [@thehackermonk]
     * @since 1.0
     */
    void updateLog(int bookID, int membershipID, LocalDate returnDate, float fine);

}
