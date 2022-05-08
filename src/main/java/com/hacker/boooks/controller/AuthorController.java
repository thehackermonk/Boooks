package com.hacker.boooks.controller;

import com.hacker.boooks.bean.AuthorProfile;
import com.hacker.boooks.bean.Book;
import com.hacker.boooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for everything related to book
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/boooks/author")
@SuppressWarnings("unused")
public class AuthorController {

    @Autowired
    private BookService bookService;

    /**
     * @param author Name of the author
     * @return Author profile
     * @apiNote Get author profile
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/getprofile")
    @ResponseBody
    public AuthorProfile getAuthorProfile(@RequestHeader String author) {
        return bookService.getAuthorProfile(author);
    }

    /**
     * @param author Name of the author
     * @return Details of books written by the author
     * @apiNote Get books written by the author
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/getBooksWrittenBy")
    @ResponseBody
    public List<Book> getBooksWrittenBy(@RequestHeader String author) {
        return bookService.getBooksWrittenBy(author);
    }

    /**
     * @param author Name of the author
     * @return Genre wise book count written by the author
     * @apiNote Get genre wise book count written by the author
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/getGenreWiseBookCount")
    @ResponseBody
    public Map<String, Integer> getGenreWiseBookCountByAuthor(@RequestHeader String author) {
        return bookService.getGenreWiseBookCountByAuthor(author);
    }

}
