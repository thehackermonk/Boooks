package com.hacker.boooks.service;

import com.hacker.boooks.bean.AuthorProfile;
import com.hacker.boooks.bean.Book;
import com.hacker.boooks.bean.BookProfile;
import com.hacker.boooks.bean.Member;
import com.hacker.boooks.bo.BookBO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @apiNote Service class for everything related to book
 */
@Service
public interface BookService {

    /**
     * @apiNote Add new book
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Boolean> addBook(BookBO bookBO);

    /**
     * @apiNote Update book details
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Boolean> updateBook(int bookID, BookBO bookBO);

    /**
     * @apiNote Remove book
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Boolean> removeBook(String name);

    /**
     * @apiNote Get all authors
     * @author [@thehackermonk]
     * @since 1.0
     */
    List<String> getAuthors();

    /**
     * @apiNote Get all books
     * @author [@thehackermonk]
     * @since 1.0
     */
    List<String> getBookNames();

    /**
     * @apiNote Get all available books
     * @author [@thehackermonk]
     * @since 1.0
     */
    List<String> getAvailableBookNames();

    /**
     * @apiNote Get book details while name is provided
     * @author [@thehackermonk]
     * @since 1.0
     */
    Book getBookDetails(String name);

    /**
     * @apiNote Profile of the book
     * @author [@thehackermonk]
     * @since 1.0
     */
    BookProfile getBookProfile(String name);

    /**
     * @apiNote Find member who holds the book
     * @author [@thehackermonk]
     * @since 1.0
     */
    Member whoHoldsTheBook(int bookID);

    /**
     * @apiNote Get author profile
     * @author [@thehackermonk]
     * @since 1.0
     */
    AuthorProfile getAuthorProfile(String author);

    /**
     * @apiNote Get books written by the author
     * @author [@thehackermonk]
     * @since 1.0
     */
    List<Book> getBooksWrittenBy(String author);

    /**
     * @apiNote Get genre wise book count written by the author
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Integer> getGenreWiseBookCountByAuthor(String author);

}
