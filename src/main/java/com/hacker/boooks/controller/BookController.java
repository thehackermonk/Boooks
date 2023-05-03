package com.hacker.boooks.controller;

import com.hacker.boooks.bean.Book;
import com.hacker.boooks.bean.BookProfile;
import com.hacker.boooks.bean.BookRequest;
import com.hacker.boooks.bean.Member;
import com.hacker.boooks.bo.BookBO;
import com.hacker.boooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for everything related to book
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/books")
@CrossOrigin(origins = "*")
@SuppressWarnings("unused")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Adds a new book to the library with the provided information.
     *
     * @param bookRequest the book request containing the name, author, publication, and genre of the book
     * @return ResponseEntity<String> with HTTP status OK and a message indicating successful addition of book, or HTTP status BAD_REQUEST and an error message otherwise.
     * @apiNote Add book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("")
    public ResponseEntity<String> addBook(@RequestBody BookRequest bookRequest) {
        BookBO bookBO = new BookBO(bookRequest);
        return bookService.addBook(bookBO);
    }

    /**
     * @param bookId ID of the book
     * @return message if book removed successfully or not
     * @apiNote Remove book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> removeBook(@PathVariable int bookId) {
        return bookService.removeBook(bookId);
    }

    /**
     * @param bookId ID of the book
     * @return message if book updated successfully or not
     * @apiNote Update book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PutMapping("/{bookId}")
    public ResponseEntity<String> updateBook(@RequestHeader int bookId, @RequestBody BookRequest bookRequest) {

        BookBO bookBO = new BookBO(bookRequest);

        return bookService.updateBook(bookId, bookBO);

    }

    /**
     * @return All available books
     * @apiNote Get all available books
     * @author [@thehackermonk]
     * @since 1.0
     */
    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    /**
     * @param name Name of the book
     * @return Book details
     * @apiNote Get book details
     * @author [@thehackermonk]
     * @since 1.0
     */
    @GetMapping("/{name}")
    public ResponseEntity<Book> getBookDetails(@PathVariable String name) {
        return bookService.getBookDetails(name);
    }

    /**
     * @param bookId Unique ID of the book
     * @return Details of the member who holds the book
     * @apiNote Get details of the member who holds the book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @GetMapping("/{bookId}/holder")
    public ResponseEntity<Member> whoHoldsTheBook(@PathVariable int bookId) {
        return bookService.whoHoldsTheBook(bookId);
    }

    /**
     * @return Name of all the book
     * @apiNote Get name of all the book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @GetMapping("/names")
    public ResponseEntity<List<String>> getBookNames() {
        return bookService.getBookNames();
    }

    /**
     * @param bookId Name of the book
     * @return Book profile
     * @apiNote Get book profile
     * @author [@thehackermonk]
     * @since 1.0
     */
    @GetMapping("/{bookId}/profile")
    public ResponseEntity<BookProfile> getBookProfile(@PathVariable int bookId) {
        return bookService.getBookProfile(bookId);
    }

}
