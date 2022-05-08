package com.hacker.boooks.repository;

import com.hacker.boooks.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author [@thehackermonk]
 * @apiNote Repository for 'book' table
 * @since 1.0
 */
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    /**
     * @param name Name of the book
     * @return Book details
     * @apiNote Get book details while name is provided
     * @since 1.0
     */
    @Query(value = "SELECT * FROM `book` WHERE `name`=?1", nativeQuery = true)
    BookEntity getBookByName(String name);

    /**
     * @param publicationID Unique ID of the publisher
     * @return Book details
     * @apiNote Get book details while publication ID is provided
     * @since 1.0
     */
    @Query(value = "SELECT * FROM `book` WHERE `publication`=?1", nativeQuery = true)
    List<BookEntity> getBooksPublishedBy(int publicationID);

    /**
     * @return Genres
     * @apiNote Get all genres
     * @since 1.0
     */
    @Query(value = "SELECT DISTINCT(genre) FROM `book`", nativeQuery = true)
    List<String> getAllGenres();

    /**
     * @return Authors
     * @apiNote Get all authors
     * @since 1.0
     */
    @Query(value = "SELECT DISTINCT(author) FROM `book`", nativeQuery = true)
    List<String> getAllAuthors();

    /**
     * @param publicationID Unique ID of the publisher
     * @param genre         Genre
     * @return Book details
     * @apiNote Get no of books while genre and publication ID is provided
     * @since 1.0
     */
    @Query(value = "SELECT COUNT(*) FROM `book` WHERE `genre`=?1 AND `publication`=?2", nativeQuery = true)
    int getBookCountByGenreAndPublication(String genre, int publicationID);

    /**
     * @param publicationID Unique ID of the publisher
     * @param author        Author
     * @return Book details
     * @apiNote Get no of books while author and publication ID is provided
     * @since 1.0
     */
    @Query(value = "SELECT COUNT(*) FROM `book` WHERE `author`=?1 AND `publication`=?2", nativeQuery = true)
    int getBookCountByAuthorAndPublication(String author, int publicationID);

    /**
     * @return Return the last book_id or return 0 if empty
     * @apiNote Get the last book_id
     * @since 1.0
     */
    @Query(value = "SELECT IFNULL(MAX(book_id),0) FROM book", nativeQuery = true)
    int getLastBookID();

    /**
     * @param author Author
     * @return no. of books written by the author
     * @apiNote Get no. of books written by the author
     * @since 1.0
     */
    @Query(value = "SELECT COUNT(*) FROM `book` WHERE `author`=?1", nativeQuery = true)
    int noOfBooksWrittenBy(String author);

    /**
     * @param author Author
     * @return books written by the author
     * @apiNote Get books written by the author
     * @since 1.0
     */
    @Query(value = "SELECT * FROM `book` WHERE `author`=?1", nativeQuery = true)
    List<BookEntity> getBooksWrittenBy(String author);

    /**
     * @param author Author
     * @return genres written by the author
     * @apiNote Get genres written by the author
     * @since 1.0
     */
    @Query(value = "SELECT DISTINCT `genre` FROM `book` WHERE `author`=?1 GROUP BY `genre`", nativeQuery = true)
    List<String> getGenresByAuthor(String author);

    /**
     * @param author Author
     * @return no. of books (genre-wise) written by the author
     * @apiNote Get no. of books (genre-wise) written by the author
     * @since 1.0
     */
    @Query(value = "SELECT COUNT(*) FROM `book` WHERE `author`=?1 AND `genre`=?2", nativeQuery = true)
    int getGenreWiseBookCountByAuthor(String author, String genre);

    /**
     * @param keyword search keyword
     * @return all books which has the keyword in its title
     * @apiNote Get all books which has the keyword in its title
     * @since 1.0
     */
    @Query(value = "SELECT * FROM `book` WHERE LOWER(`name`) LIKE LOWER(?1)", nativeQuery = true)
    List<BookEntity> searchBook(String keyword);

    /**
     * @param keyword search keyword
     * @return all authors who have the keyword in their name
     * @apiNote Get all authors who have the keyword in their name
     * @since 1.0
     */
    @Query(value = "SELECT `author` FROM `book` WHERE LOWER(`author`) LIKE LOWER(?1)", nativeQuery = true)
    List<String> searchAuthor(String keyword);

    /**
     * @param genre genre of the book
     * @return books
     * @apiNote Get five books in the given genre
     * @since 1.0
     */
    @Query(value = "SELECT * FROM `book` WHERE `genre`=?1 LIMIT 5;", nativeQuery = true)
    List<BookEntity> getFiveBooksOfGenre(String genre);

}
