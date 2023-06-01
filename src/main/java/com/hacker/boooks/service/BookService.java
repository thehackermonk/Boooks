package com.hacker.boooks.service;

import com.hacker.boooks.bean.AuthorProfile;
import com.hacker.boooks.bean.Book;
import com.hacker.boooks.bean.BookBO;
import com.hacker.boooks.bean.BookProfile;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Book management service.
 */
public interface BookService {

    /**
     * Retrieves a list of books.
     *
     * @return ResponseEntity containing the list of books
     */
    ResponseEntity<List<Book>> getBooks();

    /**
     * Retrieves a book by ID.
     *
     * @param bookId the ID of the book to retrieve
     * @return ResponseEntity containing the book if found, or not found response if not found
     */
    ResponseEntity<Book> getBook(int bookId);

    /**
     * Retrieves the profile of a book.
     *
     * @param bookId the ID of the book to retrieve the profile for
     * @return ResponseEntity containing the book profile if found, or not found response if not found
     */
    ResponseEntity<BookProfile> getBookProfile(int bookId);

    /**
     * Adds a new book to the library.
     *
     * @param bookBO the book business object containing the details of the book to be added
     * @return ResponseEntity indicating the success or failure of the book addition
     */
    ResponseEntity<String> addBook(BookBO bookBO);

    /**
     * Updates a book in the library.
     *
     * @param bookId the ID of the book to be updated
     * @param bookBO the book business object containing the updated details
     * @return ResponseEntity indicating the success or failure of the book update
     */
    ResponseEntity<String> updateBook(int bookId, BookBO bookBO);

    /**
     * Deletes a book from the library.
     *
     * @param bookId the ID of the book to be deleted
     * @return ResponseEntity indicating the success or failure of the book deletion
     */
    ResponseEntity<String> deleteBook(int bookId);

    ResponseEntity<AuthorProfile> getAuthorProfile(String name);

}
