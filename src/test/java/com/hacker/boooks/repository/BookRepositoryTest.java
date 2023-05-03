package com.hacker.boooks.repository;

import com.hacker.boooks.entity.BookEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @SuppressWarnings("unused")
    @Autowired
    private BookRepository underTest;

    private BookEntity book1;
    private BookEntity book2;

    @BeforeEach
    void setUp() {

        book1 = new BookEntity();
        book1.setTitle("Book 1");
        book1.setAuthor("Author 1");
        book1.setPublication("Publication 1");
        book1.setGenre("Genre 1");
        book1.setAvailable(true);
        book1.setHolder(null);

        book2 = new BookEntity();
        book2.setTitle("Book 2");
        book2.setAuthor("Author 2");
        book2.setPublication("Publication 2");
        book2.setGenre("Genre 2");
        book2.setAvailable(false);
        book2.setHolder(1);

        underTest.saveAll(Arrays.asList(book1, book2));

    }

    @Test
    void findByIdShouldReturnBook() {
        List<BookEntity> savedBooks = underTest.saveAll(Arrays.asList(book1, book2));
        int bookId = savedBooks.get(0).getBookID();
        Optional<BookEntity> bookOptional = underTest.findById(bookId);
        assertTrue(bookOptional.isPresent());
        assertEquals("Book 1", bookOptional.get().getTitle());
    }

    @Test
    void testFindByIdBookNotFound() {
        Optional<BookEntity> bookOptional = underTest.findById(3);
        assertFalse(bookOptional.isPresent());
    }

    @Test
    void findAllShouldReturnListOfBooks() {
        List<BookEntity> books = underTest.findAll();
        assertEquals(2, books.size());
    }

    @Test
    void findByAuthorShouldReturnListOfBooks() {
        List<BookEntity> books = underTest.findByAuthor("Author 1");
        assertEquals(1, books.size());
        assertEquals("Book 1", books.get(0).getTitle());
        assertEquals("Author 1", books.get(0).getAuthor());
        assertEquals("Publication 1", books.get(0).getPublication());
        assertEquals("Genre 1", books.get(0).getGenre());
        assertTrue(books.get(0).isAvailable());
        assertNull(books.get(0).getHolder());
    }

    @Test
    void testFindByAuthorBookNotFound() {
        List<BookEntity> books = underTest.findByAuthor("Author 3");
        assertTrue(books.isEmpty());
    }
}