package com.hacker.boooks.controller;

import com.hacker.boooks.bean.Book;
import com.hacker.boooks.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for suggestions
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/boooks")
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
    @PostMapping("/getsuggestions")
    @ResponseBody
    public List<Book> getSuggestions(@RequestHeader int membershipID) {
        return suggestionService.getSuggestions(membershipID);
    }

}
