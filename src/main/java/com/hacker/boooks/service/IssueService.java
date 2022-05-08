package com.hacker.boooks.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @apiNote Service class for issuing book
 */
@Service
public interface IssueService {

    /**
     * @apiNote Issue book
     * @author [@thehackermonk]
     * @since 1.0
     */
    LocalDate issueBook(String bookName, int membershipID);

}
