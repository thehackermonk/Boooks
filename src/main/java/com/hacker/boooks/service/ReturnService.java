package com.hacker.boooks.service;

import com.hacker.boooks.bean.ReturnBook;
import org.springframework.stereotype.Service;

/**
 * @apiNote Service class for returning book
 */
@Service
public interface ReturnService {

    /**
     * @apiNote Return book
     * @author [@thehackermonk]
     * @since 1.0
     */
    ReturnBook returnBook(int membershipID, int bookID);

}
