package com.hacker.boooks.controller;

import com.hacker.boooks.bean.AuthorProfile;
import com.hacker.boooks.bean.Book;
import com.hacker.boooks.service.BookService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for everything related to book author
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/authors")
@AllArgsConstructor
@CrossOrigin(origins = "*")
@SuppressWarnings("unused")
public class AuthorController {

    private final BookService bookService;

    /**
     * Retrieves an author profile for the given author name.
     *
     * @return a ResponseEntity containing an AuthorProfile object with the relevant fields
     * @author [@thehackermonk]
     * @since 1.0
     */
    @GetMapping("/{author}/profile")
    public ResponseEntity<AuthorProfile> getAuthorProfile(@PathVariable @NotBlank @NotNull String author) {
        return bookService.getAuthorProfile(author);
    }

    /**
     * Retrieves a list of all the unique authors of books in the database.
     *
     * @return A ResponseEntity containing a list of author names if found, or a NOT_FOUND status if no authors were found.
     * @author [@thehackermonk]
     * @since 1.0
     */
    @GetMapping("")
    public ResponseEntity<List<String>> getAuthors() {
        return bookService.getAuthors();
    }

    /**
     * Fetches books written by a specific author.
     *
     * @param author The name of the author whose books are to be fetched.
     * @return A ResponseEntity containing a list of Book objects representing the books written by the author.
     * @author [@thehackermonk]
     * @since 1.0
     */
    @GetMapping("/{author}/books")
    public ResponseEntity<List<Book>> getBooksWrittenBy(@PathVariable @NotBlank @NotNull String author) {
        return bookService.getBooksWrittenBy(author);
    }

    /**
     * Fetches the count of books written by a specific author grouped by genre.
     *
     * @return A ResponseEntity containing a map of genre names and the count of books written by the author in that genre.
     * @author [@thehackermonk]
     * @since 1.0
     */
    @GetMapping("/{author}/books/genre-count")
    public ResponseEntity<Map<String, Integer>> getGenreWiseBookCountByAuthor(@PathVariable @NotBlank @NotNull String author) {
        return bookService.getGenreWiseBookCountByAuthor(author);
    }

}
