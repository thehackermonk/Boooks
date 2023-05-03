package com.hacker.boooks.service.impl;

import com.hacker.boooks.bean.AuthorProfile;
import com.hacker.boooks.bean.Book;
import com.hacker.boooks.bean.BookProfile;
import com.hacker.boooks.bean.Member;
import com.hacker.boooks.bo.BookBO;
import com.hacker.boooks.entity.BookEntity;
import com.hacker.boooks.entity.MemberEntity;
import com.hacker.boooks.entity.PublisherEntity;
import com.hacker.boooks.repository.BookRepository;
import com.hacker.boooks.repository.LogRepository;
import com.hacker.boooks.repository.MemberRepository;
import com.hacker.boooks.repository.PublisherRepository;
import com.hacker.boooks.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing books.
 */
@Service
@AllArgsConstructor
@Log4j2
@SuppressWarnings("unused")
public class BookServiceImpl implements BookService {

    private static final String FETCHING_BOOKS_MESSAGE = "Fetching books written by author: {}";
    private static final String NO_BOOKS_MESSAGE = "No books found for author: {}";
    private static final String BOOK_WITH_ID_MESSAGE = "Book with ID: {} - {}";

    private final BookRepository bookRepository;
    private final LogRepository logRepository;
    private final MemberRepository memberRepository;
    private final PublisherRepository publisherRepository;

    /**
     * Retrieves an author profile for the given author name.
     *
     * @param author the name of the author
     * @return a ResponseEntity containing an AuthorProfile object with the relevant fields
     */
    public ResponseEntity<AuthorProfile> getAuthorProfile(String author) {
        try {
            log.info(FETCHING_BOOKS_MESSAGE, author);
            // retrieve the relevant data from the database
            List<BookEntity> booksWrittenByAuthor = bookRepository.findByAuthor(author);

            // calculate the number of books written by the author
            int numBooksWritten = booksWrittenByAuthor.size();

            if (numBooksWritten == 0)
                return ResponseEntity.notFound().build();

            // calculate the genre in which the author wrote the most books
            Map<String, Long> genreCountMap = booksWrittenByAuthor.stream()
                    .collect(Collectors.groupingBy(BookEntity::getGenre, Collectors.counting()));
            // calculate the book which was the most popular
            int maxCount = 0;
            Optional<BookEntity> mostPopularBook = Optional.empty();

            for (BookEntity book : booksWrittenByAuthor) {
                int count = logRepository.countByBookId(book.getBookID());
                if (count > maxCount) {
                    mostPopularBook = Optional.of(book);
                    maxCount = count;
                }
            }

            // create an AuthorProfile object with the relevant fields
            AuthorProfile authorProfile = new AuthorProfile();
            authorProfile.setName(author);
            authorProfile.setNumBooksWritten(numBooksWritten);
            authorProfile.setGenreMostWritten(Collections.max(genreCountMap.entrySet(), Map.Entry.comparingByValue())
                    .getKey());
            mostPopularBook.ifPresent(bookEntity -> authorProfile.setMostPopularBook(new Book(bookEntity)));

            // return the AuthorProfile object as part of a ResponseEntity
            return ResponseEntity.ok(authorProfile);
        } catch (Exception e) {
            log.error("An error occurred while fetching author profile: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    /**
     * Returns a list of all the unique authors of books in the database.
     *
     * @return A ResponseEntity containing a list of author names if found, or a NOT_FOUND status if no authors were found.
     * @apiNote The list returned by this method may be empty if no books are found for the given author.
     */
    @Override
    public ResponseEntity<List<String>> getAuthors() {
        try {
            log.info("Fetching all unique authors of books from the database");
            List<String> authors = bookRepository.findAll()
                    .stream()
                    .map(BookEntity::getAuthor)
                    .distinct()
                    .toList();

            return ResponseEntity.status(authors.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK)
                    .body(authors);
        } catch (Exception e) {
            log.error("An error occurred while fetching authors: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Fetches books written by a specific author.
     *
     * @param author The name of the author whose books are to be fetched.
     * @return A ResponseEntity containing a list of Book objects representing the books written by the author.
     * If books are found, a ResponseEntity with HTTP status 200 (OK) and a list of Book objects representing the books
     * written by the author is returned. If no books are found for the given author, a ResponseEntity with HTTP status 404
     * (Not Found) is returned.
     * @apiNote The list returned by this method may be empty if no books are found for the given author.
     */
    public ResponseEntity<List<Book>> getBooksWrittenBy(String author) {
        try {
            log.info(FETCHING_BOOKS_MESSAGE, author);
            List<Book> books = bookRepository.findByAuthor(author)
                    .stream()
                    .map(Book::new)
                    .toList();

            if(books.isEmpty())
                return ResponseEntity.notFound().build();

            return ResponseEntity.ok(books);
        } catch (Exception e) {
            log.error("An error occurred while fetching books: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Fetches the count of books written by a specific author grouped by genre.
     *
     * @param author The name of the author whose books are to be counted.
     * @return A ResponseEntity containing a map of genre names and the count of books written by the author in that genre.
     * If no books are found for the given author, returns ResponseEntity with HTTP status 404 (Not Found).
     * If books are found, returns ResponseEntity with HTTP status 200 (OK).
     * @apiNote The map returned by this method may be empty if no books are found for the given author.
     */
    public ResponseEntity<Map<String, Integer>> getGenreWiseBookCountByAuthor(String author) {
        try {
            log.info(FETCHING_BOOKS_MESSAGE, author);
            List<BookEntity> bookEntitiesByAuthor = bookRepository.findByAuthor(author);

            if (bookEntitiesByAuthor == null || bookEntitiesByAuthor.isEmpty()) {
                log.warn(NO_BOOKS_MESSAGE, author);
                return ResponseEntity.notFound().build();
            }

            Map<String, Integer> bookCountByGenre = bookEntitiesByAuthor.stream()
                    .collect(Collectors.groupingBy(BookEntity::getGenre, Collectors.summingInt(book -> 1)));

            log.info("Books found for author: {}", author);
            return ResponseEntity.ok(bookCountByGenre);
        } catch (Exception e) {
            log.error("An error occurred while fetching books: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Adds a new book to the library.
     *
     * @param bookBO the book object containing name, author, publication, and genre
     * @return a ResponseEntity with a message indicating if the book was added successfully or not
     * @apiNote Add book
     */
    @Override
    public ResponseEntity<String> addBook(BookBO bookBO) {

        try {
            BookEntity bookEntity = new BookEntity();
            bookEntity.setTitle(bookBO.getName());
            bookEntity.setAuthor(bookBO.getAuthor());
            bookEntity.setPublication(bookBO.getPublication());
            bookEntity.setGenre(bookBO.getGenre());
            bookEntity.setAvailable(true);
            bookEntity.setHolder(null);

            BookEntity resultEntity = bookRepository.save(bookEntity);

            log.info("Book with ID: {} added successfully", resultEntity.getBookID());
            return ResponseEntity.ok("Book added successfully");
        } catch (Exception e) {
            log.error("An error occurred while adding book: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    /**
     * Removes a book with the given ID from the system.
     *
     * @param bookId The ID of the book to be removed.
     * @return A response entity indicating whether the removal was successful or not.
     * @apiNote Remove book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public ResponseEntity<String> removeBook(int bookId) {
        try {
            Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookId);
            if (optionalBookEntity.isEmpty()) {
                log.info("Book with ID: {} not found", bookId);
                return ResponseEntity.notFound().build();
            }

            bookRepository.delete(optionalBookEntity.get());
            log.info("Book with ID: {} removed successfully", bookId);
            return ResponseEntity.ok("Book removed successfully");
        } catch (Exception e) {
            log.error("An error occurred while removing book: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Updates the book with the given ID.
     *
     * @param bookId the ID of the book to be updated
     * @param bookBO the BookBO object containing the updated book information
     * @return ResponseEntity with a String indicating if the update was successful or not
     * @apiNote Update book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public ResponseEntity<String> updateBook(int bookId, BookBO bookBO) {
        try {
            Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookId);
            if (optionalBookEntity.isEmpty()) {
                log.info("Book with ID: {} not found", bookId);
                return ResponseEntity.notFound().build();
            }

            optionalBookEntity.get().setTitle(bookBO.getName());
            optionalBookEntity.get().setAuthor(bookBO.getAuthor());
            optionalBookEntity.get().setPublication(bookBO.getPublication());
            optionalBookEntity.get().setGenre(bookBO.getGenre());

            bookRepository.save(optionalBookEntity.get());

            log.info("Book with ID: {} updated successfully", bookId);
            return ResponseEntity.ok("Book updated successfully");

        } catch (Exception e) {
            log.error("An error occurred while updating book: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Returns a list of all available books in the database.
     *
     * @return A ResponseEntity containing a list of available Book objects if found, or a NOT_FOUND status if no available books were found.
     * @apiNote The list returned by this method may be empty if no books are available.
     */
    @Override
    public ResponseEntity<List<Book>> getAvailableBooks() {
        try {
            log.info("Fetching available books");
            List<Book> books = bookRepository.findAll()
                    .stream()
                    .filter(BookEntity::isAvailable)
                    .map(Book::new)
                    .toList();

            if(books.isEmpty())
                return ResponseEntity.notFound().build();

            return ResponseEntity.ok(books);
        } catch (Exception e) {
            log.error("An error occurred while fetching available books: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    /**
     * Retrieves the book with the specified ID from the database.
     *
     * @param name The name of the book to retrieve.
     * @return A ResponseEntity containing the requested book if found, or a NOT_FOUND status if no book was found.
     * @apiNote This method will return null if no book was found with the specified ID.
     */
    @Override
    public ResponseEntity<Book> getBookDetails(String name) {

        try {
            log.info("Fetching book with name {}", name);
            Book book = bookRepository.findAll()
                    .stream()
                    .filter(bookEntity -> bookEntity.getTitle().equalsIgnoreCase(name))
                    .findFirst()
                    .map(Book::new)
                    .orElse(null);

            log.info("Fetched book with name {}", name);

            return ResponseEntity.status(book != null ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                    .body(book);
        } catch (Exception e) {
            log.error("An error occurred while fetching book details: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Returns the member who is currently holding the book with the given ID.
     *
     * @param bookId The ID of the book to find the current holder of.
     * @return A ResponseEntity containing the Member object if found, or a NOT_FOUND status if the book or member were not found.
     * @apiNote The returned ResponseEntity may have a NOT_FOUND status if either the book with the given ID was not found or if the book is not currently being held by any member.
     */
    @Override
    public ResponseEntity<Member> whoHoldsTheBook(int bookId) {

        try {
            log.info("Fetching book with ID: {}", bookId);
            Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookId);
            if (optionalBookEntity.isEmpty()) {
                log.info(BOOK_WITH_ID_MESSAGE, bookId, "Book not found");
                return ResponseEntity.notFound().build();
            }
            BookEntity bookEntity = optionalBookEntity.get();
            if (bookEntity.getHolder() == null) {
                log.info(BOOK_WITH_ID_MESSAGE, bookId, "Book is not currently held by any member");
                return ResponseEntity.notFound().build();
            } else {
                Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(bookEntity.getHolder());
                if (optionalMemberEntity.isPresent()) {
                    Member member = new Member(optionalMemberEntity.get());
                    log.info(BOOK_WITH_ID_MESSAGE, bookId, "The book is held by member: " + member.getName());
                    return ResponseEntity.ok(member);
                } else {
                    log.info(BOOK_WITH_ID_MESSAGE, bookId, "Member holding the book not found");
                    return ResponseEntity.notFound().build();
                }
            }
        } catch (Exception e) {
            log.error("An error occurred while fetching member holding the book: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Returns a list of all the unique book names in the database.
     *
     * @return A ResponseEntity containing a list of book names if found, or a NOT_FOUND status if no book names were found.
     * @apiNote The list returned by this method may be empty if no books are found in the database.
     */
    @Override
    public ResponseEntity<List<String>> getBookNames() {
        try {
            log.info("Fetching book names");
            List<String> books = bookRepository.findAll()
                    .stream()
                    .map(BookEntity::getTitle)
                    .distinct()
                    .toList();
            log.info("Fetched {} book names", books.size());
            return ResponseEntity.status(books.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK)
                    .body(books);
        } catch (Exception e) {
            log.error("An error occurred while fetching book names: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Returns a book profile for the given book ID, including information about the book, its publisher (if available),
     * and the member who currently holds it (if any).
     *
     * @param bookId The ID of the book to retrieve the profile for.
     * @return A ResponseEntity containing the book profile if found, or a NOT_FOUND status if no book was found.
     */
    public ResponseEntity<BookProfile> getBookProfile(int bookId) {
        try {
            log.info("Fetching book with ID {}", bookId);
            Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookId);
            Optional<MemberEntity> optionalMemberEntity = Optional.empty();
            if (optionalBookEntity.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            BookEntity bookEntity = optionalBookEntity.get();
            Optional<PublisherEntity> optionalPublisherEntity = publisherRepository.findById(bookEntity.getPublication());
            if (optionalBookEntity.get().getHolder() != null)
                optionalMemberEntity = memberRepository.findById(bookEntity.getHolder());
            BookProfile bookProfile = new BookProfile(bookEntity, optionalPublisherEntity.orElse(null), optionalMemberEntity.orElse(null));
            log.info("Fetched book with ID {}", bookId);
            return ResponseEntity.ok(bookProfile);
        } catch (Exception e) {
            log.error("An error occurred while fetching book profile: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
