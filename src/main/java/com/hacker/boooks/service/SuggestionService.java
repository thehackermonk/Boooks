package com.hacker.boooks.service;

import com.hacker.boooks.bean.Book;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @apiNote Service class for suggestions
 */
@Service
public interface SuggestionService {

    /**
     * @apiNote Get suggestions for a member
     * @author [@thehackermonk]
     * @since 1.0
     */
    List<Book> getSuggestions(int membershipID);

}
