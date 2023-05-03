package com.hacker.boooks.controller;

import com.hacker.boooks.bean.Book;
import com.hacker.boooks.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for suggestions
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/suggestions")
@CrossOrigin(origins = "*")
@SuppressWarnings("unused")
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;

    /**
     * @param membershipID Unique ID of the member
     * @return book suggestions
     * @apiNote Get suggestions to read
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @PostMapping("")
//    public List<Book> getSuggestions(@RequestHeader int membershipID) {
//        return suggestionService.getSuggestions(membershipID);
//    }

}
