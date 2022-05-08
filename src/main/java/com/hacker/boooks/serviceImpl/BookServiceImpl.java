package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.*;
import com.hacker.boooks.bo.BookBO;
import com.hacker.boooks.constant.Response;
import com.hacker.boooks.entity.BookEntity;
import com.hacker.boooks.entity.MemberEntity;
import com.hacker.boooks.repository.BookRepository;
import com.hacker.boooks.repository.LogRepository;
import com.hacker.boooks.repository.MemberRepository;
import com.hacker.boooks.service.BookService;
import com.hacker.boooks.service.PublicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@SuppressWarnings("unused")
public class BookServiceImpl implements BookService {

    Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PublicationService publicationService;

    /**
     * @return Next book ID
     * @apiNote Get next book ID
     * @author [@thehackermonk]
     * @since 1.0
     */
    private int getNextBookID() {

        int lastBookID = bookRepository.getLastBookID();

        if (lastBookID == 0)
            return 1;
        else
            return (lastBookID + 1);

    }

    /**
     * @param bookBO book details
     * @return true if addition succeeds, and false otherwise
     * @apiNote Add new book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Map<String, Boolean> addBook(BookBO bookBO) {

        int bookID = getNextBookID();

        Publication publication = publicationService.getPublication(bookBO.getPublication());

        BookEntity checkBookName = bookRepository.getBookByName(bookBO.getName());

        if (checkBookName == null) {

            BookEntity book = new BookEntity(bookID, bookBO.getName(), bookBO.getAuthor(), publication.getPublisherID(), bookBO.getGenre(), true, -1);

            bookRepository.save(book);

            log.info(bookBO.getName() + " added successfully with ID " + bookID);

            return Collections.singletonMap("result", true);

        } else {

            log.info(bookBO.getName() + " already exist!");

            return Collections.singletonMap("result", false);

        }

    }

    /**
     * @param bookID Unique identifier of book
     * @param bookBO Book details
     * @return true if publication update succeeded, and false otherwise
     * @apiNote Update book details
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Map<String, Boolean> updateBook(int bookID, BookBO bookBO) {

        BookEntity book = bookRepository.getById(bookID);

        Publication publication = publicationService.getPublication(bookBO.getPublication());


        if (!book.getName().equals(bookBO.getName())) {

            BookEntity checkBookName = bookRepository.getBookByName(bookBO.getName());

            if (checkBookName == null)

                book.setName(bookBO.getName());

            else {

                log.info(bookBO.getName() + " already exist!");

                return Collections.singletonMap("result", false);

            }

        }

        if (!book.getAuthor().equals(bookBO.getAuthor()))
            book.setAuthor(bookBO.getAuthor());

        if (book.getPublication() != publication.getPublisherID())
            book.setPublication(publication.getPublisherID());

        if (!book.getGenre().equals(bookBO.getGenre()))
            book.setGenre(bookBO.getGenre());

        bookRepository.save(book);

        log.info(bookBO.getName() + " updated successfully!");

        return Collections.singletonMap("result", true);

    }

    /**
     * @param name Name of the book
     * @return true if book removed successfully, and false otherwise
     * @apiNote Remove book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Map<String, Boolean> removeBook(String name) {

        BookEntity book = bookRepository.getBookByName(name);

        bookRepository.delete(book);

        log.info(name + " removed successfully!");

        return Collections.singletonMap("result", true);

    }

    /**
     * @return all author names
     * @apiNote Get all authors
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public List<String> getAuthors() {

        log.info(Response.AUTHOR_NAMES_FETCHED);

        return bookRepository.getAllAuthors();

    }

    /**
     * @return all available book names
     * @apiNote Get all available books
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public List<String> getAvailableBookNames() {

        List<BookEntity> books = bookRepository.findAll();
        List<String> availableBooks = new ArrayList<>();

        for (BookEntity book : books) {

            if (book.isAvailable())
                availableBooks.add(book.getName());

        }

        log.info(Response.AVAILABLE_BOOK_NAMES_FETCHED);

        return availableBooks;

    }

    /**
     * @return all book names
     * @apiNote Get all books
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public List<String> getBookNames() {

        List<BookEntity> books = bookRepository.findAll();
        List<String> bookNames = new ArrayList<>();

        for (BookEntity book : books)
            bookNames.add(book.getName());

        log.info(Response.BOOK_NAMES_FETCHED);

        return bookNames;

    }

    /**
     * @param name Name of the book
     * @return book details
     * @apiNote Get book details while name is provided
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Book getBookDetails(String name) {

        BookEntity bookEntity = bookRepository.getBookByName(name);

        if (bookEntity != null) {

            Book book = new Book();

            int holderID = bookEntity.getHolder();

            Publication publication = publicationService.getPublication(bookEntity.getPublication());

            book.setBookID(bookEntity.getBookID());
            book.setName(bookEntity.getName());
            book.setAuthor(bookEntity.getAuthor());
            book.setPublication(publication);
            book.setGenre(bookEntity.getGenre());
            book.setAvailable(bookEntity.isAvailable());

            if (holderID == -1)
                book.setHolder(null);
            else {

                MemberEntity memberEntity = memberRepository.getById(holderID);

                Member holder = new Member(memberEntity);

                book.setHolder(holder);

            }

            log.info("Details of " + name + " fetched.");

            return book;

        } else
            return null;

    }

    /**
     * @param name Name of the book
     * @return Book profile
     * @apiNote Profile of the book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public BookProfile getBookProfile(String name) {

        BookEntity book = bookRepository.getBookByName(name);

        if (book != null) {

            BookProfile bookProfile = new BookProfile();

            int holderID = book.getHolder();

            Publication publication = publicationService.getPublication(book.getPublication());

            bookProfile.setName(book.getName());
            bookProfile.setAuthor(book.getAuthor());
            bookProfile.setPublication(publication);
            bookProfile.setGenre(book.getGenre());
            bookProfile.setAvailable(book.isAvailable());

            if (holderID == -1)
                bookProfile.setHolder(null);
            else {

                MemberEntity memberEntity = memberRepository.getById(holderID);

                Member holder = new Member(memberEntity);

                bookProfile.setHolder(holder);

            }

            log.info("Profile of " + name + " fetched!");

            return bookProfile;

        } else
            return null;

    }

    /**
     * @param bookID Unique ID of the book
     * @return Member details who hold the book
     * @apiNote Find member who holds the book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Member whoHoldsTheBook(int bookID) {

        int membershipID = logRepository.whoHoldsTheBook(bookID);
        MemberEntity memberEntity = memberRepository.getById(membershipID);

        log.info("Details of member who holds the book " + bookID);

        return new Member(memberEntity);

    }

    /**
     * @param author Name of the author
     * @return Author profile
     * @apiNote Get author profile
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public AuthorProfile getAuthorProfile(String author) {

        String favoriteGenre;
        int maxCount = 0;

        Book mostReadBookBy;

        int noOfBooksWritten = bookRepository.noOfBooksWrittenBy(author);

        favoriteGenre = getFavoriteGenreOfAuthor(author);
        mostReadBookBy = getMostReadBookByAuthor(author);

        log.info("Profile of " + author + " fetched!");

        return new AuthorProfile(author, noOfBooksWritten, favoriteGenre, mostReadBookBy);

    }

    /**
     * @param author Name of the author
     * @return Details of books written by the author
     * @apiNote Get books written by the author
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public List<Book> getBooksWrittenBy(String author) {

        List<Book> books = new ArrayList<>();
        List<BookEntity> booksWrittenByAuthor = bookRepository.getBooksWrittenBy(author);

        for (BookEntity bookEntity : booksWrittenByAuthor) {

            Book book = getBookDetails(bookEntity.getName());
            books.add(book);

        }

        log.info("Books written by " + author + " fetched!");

        return books;

    }

    /**
     * @param author Name of the author
     * @return Genre wise book count written by the author
     * @apiNote Get genre wise book count written by the author
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Map<String, Integer> getGenreWiseBookCountByAuthor(String author) {

        Map<String, Integer> genreWiseBookCount = new HashMap<>();

        List<String> genresByAuthor = bookRepository.getGenresByAuthor(author);

        for (String genre : genresByAuthor) {

            int count = bookRepository.getGenreWiseBookCountByAuthor(author, genre);
            genreWiseBookCount.put(genre, count);

        }

        log.info("Count of books (genre wise) written by " + author + " fetched.");

        return genreWiseBookCount;

    }

    /**
     * @param author Name of the author
     * @return Most written genre
     * @apiNote Get most written genre
     * @author [@thehackermonk]
     * @since 1.0
     */
    private String getFavoriteGenreOfAuthor(String author) {

        String favoriteGenre = "";
        int maxCount = 0;

        List<String> genresByAuthor = bookRepository.getGenresByAuthor(author);

        for (String genre : genresByAuthor) {

            int count = bookRepository.getGenreWiseBookCountByAuthor(author, genre);

            if (count > maxCount) {
                favoriteGenre = genre;
                maxCount = count;
            }

        }

        log.info("Most written genre by " + author + " fetched.");

        return favoriteGenre;

    }

    /**
     * @param author Name of the author
     * @return Most read book written by the author
     * @apiNote Get most read book written by the author
     * @author [@thehackermonk]
     * @since 1.0
     */
    private Book getMostReadBookByAuthor(String author) {

        Book book = null;
        int maxCount = 0;

        List<BookEntity> booksWrittenBy = bookRepository.getBooksWrittenBy(author);

        for (BookEntity bookWrittenBy : booksWrittenBy) {

            int count = logRepository.getBookCount(bookWrittenBy.getBookID());

            if (count > maxCount) {

                maxCount = count;
                BookEntity bookEntity = bookRepository.getById(bookWrittenBy.getBookID());

                book = getBookDetails(bookEntity.getName());

            }

        }

        log.info("Most written book written by " + author + " fetched.");

        return book;

    }

}
