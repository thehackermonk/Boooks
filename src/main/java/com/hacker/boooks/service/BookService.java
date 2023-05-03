package com.hacker.boooks.service;

import com.hacker.boooks.bean.AuthorProfile;
import com.hacker.boooks.bean.Book;
import com.hacker.boooks.bean.BookProfile;
import com.hacker.boooks.bean.Member;
import com.hacker.boooks.bo.BookBO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service class for managing books.
 */
@Service
public interface BookService {

    /**
     * Retrieves an author profile for the given author name.
     *
     * @param author the name of the author
     * @return a ResponseEntity containing an AuthorProfile object with the relevant fields
     */
    ResponseEntity<AuthorProfile> getAuthorProfile(String author);

    /**
     * Returns a list of all the unique authors of books in the database.
     *
     * @return A ResponseEntity containing a list of author names if found, or a NOT_FOUND status if no authors were found.
     */
    ResponseEntity<List<String>> getAuthors();

    /**
     * Fetches books written by a specific author.
     *
     * @param author The name of the author whose books are to be fetched.
     * @return A ResponseEntity containing a list of Book objects representing the books written by the author.
     */
    ResponseEntity<List<Book>> getBooksWrittenBy(String author);

    /**
     * Fetches the count of books written by a specific author grouped by genre.
     *
     * @param author The name of the author whose books are to be counted.
     * @return A ResponseEntity containing a map of genre names and the count of books written by the author in that genre.
     */
    ResponseEntity<Map<String, Integer>> getGenreWiseBookCountByAuthor(String author);

    ResponseEntity<String> addBook(BookBO bookBO);

    ResponseEntity<String> removeBook(int bookId);

    ResponseEntity<String> updateBook(int bookId, BookBO bookBO);

    ResponseEntity<List<Book>> getAvailableBooks();

    ResponseEntity<Book> getBookDetails(String name);

    ResponseEntity<Member> whoHoldsTheBook(int bookId);

    ResponseEntity<List<String>> getBookNames();

    ResponseEntity<BookProfile> getBookProfile(int bookId);

}
