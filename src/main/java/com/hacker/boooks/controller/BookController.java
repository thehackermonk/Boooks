package com.hacker.boooks.controller;

import com.hacker.boooks.bean.Book;
import com.hacker.boooks.bean.BookProfile;
import com.hacker.boooks.bean.Member;
import com.hacker.boooks.bo.BookBO;
import com.hacker.boooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for everything related to book
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/boooks/book")
@SuppressWarnings("unused")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * @param name        Name of the book
     * @param author      Author of the book
     * @param publication Publication of the book
     * @param genre       Genre of the book
     * @return true if book added successfully, false otherwise
     * @apiNote Add book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/add")
    @ResponseBody
    public Map<String, Boolean> addBook(@RequestHeader String name, @RequestHeader String author, @RequestHeader String publication, @RequestHeader String genre) {

        BookBO bookBO = new BookBO(name, author, publication, genre);

        return bookService.addBook(bookBO);

    }

    /**
     * @param name Name of the book
     * @return true if book removed successfully, false otherwise
     * @apiNote Remove book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/remove")
    @ResponseBody
    public Map<String, Boolean> removeBook(@RequestHeader String name) {
        return bookService.removeBook(name);
    }

    /**
     * @return All authors
     * @apiNote Get all authors
     * @author [@thehackermonk]
     * @since 1.0
     */
    @GetMapping("/getauthors")
    @ResponseBody
    public List<String> getAuthors() {
        return bookService.getAuthors();
    }

    /**
     * @return All available books
     * @apiNote Get all available books
     * @author [@thehackermonk]
     * @since 1.0
     */
    @GetMapping("/getavailablebooks")
    @ResponseBody
    public List<String> getAvailableBooks() {
        return bookService.getAvailableBookNames();
    }

    /**
     * @param name Name of the book
     * @return Book details
     * @apiNote Get book details
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/getdetails")
    @ResponseBody
    public Book getBookDetails(@RequestHeader String name) {
        return bookService.getBookDetails(name);
    }

    /**
     * @param bookID Unique ID of the book
     * @return Details of the member who holds the book
     * @apiNote Get details of the member who holds the book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/whoholds")
    @ResponseBody
    public Member whoHoldsTheBook(@RequestHeader int bookID) {
        return bookService.whoHoldsTheBook(bookID);
    }

    /**
     * @return Name of all the book
     * @apiNote Get name of all the book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @GetMapping("/getnames")
    @ResponseBody
    public List<String> getBookNames() {
        return bookService.getBookNames();
    }

    /**
     * @param name Name of the book
     * @return Book profile
     * @apiNote Get book profile
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/getprofile")
    @ResponseBody
    public BookProfile getBookProfile(@RequestHeader String name) {
        return bookService.getBookProfile(name);
    }

    /**
     * @param name Name of the book
     * @return true if book updated successfully, false otherwise
     * @apiNote Update book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/update")
    @ResponseBody
    public Map<String, Boolean> updateBook(@RequestHeader int bookID, @RequestHeader String name, @RequestHeader String author, @RequestHeader String publication, @RequestHeader String genre) {

        BookBO bookBO = new BookBO(name, author, publication, genre);

        return bookService.updateBook(bookID, bookBO);

    }

}
