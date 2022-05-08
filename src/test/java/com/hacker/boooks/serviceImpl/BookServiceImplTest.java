package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.*;
import com.hacker.boooks.bo.BookBO;
import com.hacker.boooks.entity.BookEntity;
import com.hacker.boooks.entity.MemberEntity;
import com.hacker.boooks.repository.BookRepository;
import com.hacker.boooks.repository.LogRepository;
import com.hacker.boooks.repository.MemberRepository;
import com.hacker.boooks.service.PublicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl underTest;

    @Mock
    private PublicationService publicationService;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private LogRepository logRepository;
    @Mock
    private MemberRepository memberRepository;

    @Test
    void addBook() {

        // given
        BookBO bookBO = new BookBO("In Search of Lost Time", "Marcel Proust", "Pearson", "Romance");
        Publication publication = new Publication(1, "Pearson");

        when(publicationService.getPublication(anyString())).thenReturn(publication);

        // when
        Map<String, Boolean> response = underTest.addBook(bookBO);

        // then
        assertThat(response.get("result")).isEqualTo(true);

    }

    @Test
    void updateBook() {

        // given
        BookBO bookBO = new BookBO("In Search of Lost Time", "Marcel Proust", "Pearson", "Romance");
        BookEntity bookEntity = new BookEntity(1, "In Search of Lost Time", "Marcel Proust", 1, "Romance", true, -1);
        Publication publication = new Publication(1, "Pearson");

        when(bookRepository.getById(anyInt())).thenReturn(bookEntity);
        when(publicationService.getPublication(anyString())).thenReturn(publication);

        // when
        Map<String, Boolean> response = underTest.updateBook(1, bookBO);

        // then
        assertThat(response.get("result")).isEqualTo(true);

    }

    @Test
    void removeBook() {

        // given
        BookEntity bookEntity = new BookEntity(1, "In Search of Lost Time", "Marcel Proust", 1, "Romance", true, -1);

        when(bookRepository.getBookByName(anyString())).thenReturn(bookEntity);

        // when
        Map<String, Boolean> response = underTest.removeBook("In Search of Lost Time");

        // then
        assertThat(response.get("result")).isEqualTo(true);

    }

    @Test
    void getAuthors() {

        // when
        underTest.getAuthors();

        // then
        verify(bookRepository).getAllAuthors();

    }

    @Test
    void getAvailableBookNames() {

        // when
        underTest.getAvailableBookNames();

        // then
        verify(bookRepository).findAll();

    }

    @Test
    void getBookNames() {

        // when
        underTest.getBookNames();

        //then
        verify(bookRepository).findAll();

    }

    @Test
    void getBookDetails() {

        // given
        BookEntity bookEntity = new BookEntity(1, "In Search of Lost Time", "Marcel Proust", 1, "Romance", true, -1);
        MemberEntity member = new MemberEntity(1, "Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");
        Publication publication = new Publication(1, "Pearson");

        when(bookRepository.getBookByName(anyString())).thenReturn(bookEntity);
        when(memberRepository.getById(anyInt())).thenReturn(member);
        when(publicationService.getPublication(anyString())).thenReturn(publication);

        // when
        Book response = underTest.getBookDetails("In Search of Lost Time");

        // then
        assertThat(response.getBookID()).isEqualTo(1);

    }

    @Test
    void getBookProfile() {

        // given
        BookEntity bookEntity = new BookEntity(1, "In Search of Lost Time", "Marcel Proust", 1, "Romance", true, -1);
        MemberEntity member = new MemberEntity(1, "Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");
        Publication publication = new Publication(1, "Pearson");

        when(bookRepository.getBookByName(anyString())).thenReturn(bookEntity);
        when(memberRepository.getById(anyInt())).thenReturn(member);
        when(publicationService.getPublication(anyString())).thenReturn(publication);

        // when
        BookProfile response = underTest.getBookProfile("In Search of Lost Time");

        // then
        assertThat(response).isNotNull();

    }

    @Test
    void whoHoldsTheBook() {

        // given
        MemberEntity member = new MemberEntity(1, "Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");

        when(logRepository.whoHoldsTheBook(anyInt())).thenReturn(1);
        when(memberRepository.getById(anyInt())).thenReturn(member);

        // when
        Member response = underTest.whoHoldsTheBook(1);

        // then
        assertThat(response.getName()).isEqualTo("Brandon Reynolds");

    }

    @Test
    void getAuthorProfile() {

        // given
        BookEntity bookEntity1 = new BookEntity(1, "In Search of Lost Time", "Marcel Proust", 1, "Romance", true, -1);
        BookEntity bookEntity2 = new BookEntity(2, "Ulysses", "James Joyce", 1, "Romance", true, -1);
        List<BookEntity> bookEntities = new ArrayList<>(Arrays.asList(bookEntity1, bookEntity2));
        List<String> genres = new ArrayList<>(Collections.singleton("Romance"));

        when(bookRepository.getBooksWrittenBy(anyString())).thenReturn(bookEntities);
        when(logRepository.getBookCount(anyInt())).thenReturn(1);
        when(bookRepository.getById(anyInt())).thenReturn(bookEntity1);
        when(bookRepository.getGenresByAuthor(anyString())).thenReturn(genres);
        when(bookRepository.getGenreWiseBookCountByAuthor(anyString(), anyString())).thenReturn(2);

        // when
        AuthorProfile response = underTest.getAuthorProfile("Marcel Proust");

        // then
        assertThat(response).isNotNull();

    }

    @Test
    void getBooksWrittenBy() {

        // given
        BookEntity bookEntity = new BookEntity(1, "In Search of Lost Time", "Marcel Proust", 1, "Romance", true, -1);
        List<BookEntity> bookEntities = new ArrayList<>(Collections.singleton(bookEntity));

        when(bookRepository.getBooksWrittenBy(anyString())).thenReturn(bookEntities);

        // when
        List<Book> response = underTest.getBooksWrittenBy("Marcel Proust");

        // then
        assertThat(response).isNotNull();

    }

    @Test
    void getGenreWiseBookCountByAuthor() {

        // given
        ArrayList<String> genres = new ArrayList<>(Collections.singleton("Romance"));

        when(bookRepository.getGenresByAuthor(anyString())).thenReturn(genres);
        when(bookRepository.getGenreWiseBookCountByAuthor(anyString(), anyString())).thenReturn(1);

        // when
        Map<String, Integer> response = underTest.getGenreWiseBookCountByAuthor("Marcel Proust");

        // then
        assertThat(response).isNotNull();

    }
}