package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.Book;
import com.hacker.boooks.bean.Publication;
import com.hacker.boooks.bean.PublicationProfile;
import com.hacker.boooks.entity.BookEntity;
import com.hacker.boooks.entity.MemberEntity;
import com.hacker.boooks.entity.PublisherEntity;
import com.hacker.boooks.repository.BookRepository;
import com.hacker.boooks.repository.MemberRepository;
import com.hacker.boooks.repository.PublisherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PublicationServiceImplTest {

    @InjectMocks
    private PublicationServiceImpl underTest;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private PublisherRepository publisherRepository;

    @Test
    void addPublication() {

        // when
        underTest.addPublication(anyString());

        // then
        verify(publisherRepository).save(any());

    }

    @Test
    void getPublicationByID() {

        // given
        PublisherEntity publisherEntity = new PublisherEntity(1, "Pearson");
        when(publisherRepository.getById(anyInt())).thenReturn(publisherEntity);

        // when
        Publication response = underTest.getPublication(anyInt());

        // then
        assertThat(response).isNotNull();

    }

    @Test
    void testGetPublicationByName() {

        // given
        PublisherEntity publisherEntity = new PublisherEntity(1, "Pearson");

        when(publisherRepository.getPublisher(anyString())).thenReturn(publisherEntity);
        when(publisherRepository.getById(anyInt())).thenReturn(publisherEntity);

        // when
        Publication response = underTest.getPublication(anyString());

        // then
        assertThat(response).isNotNull();

    }

    @Test
    void getPublications() {

        // when
        underTest.getPublications();

        // then
        verify(publisherRepository).findAll();

    }

    @Test
    void updatePublication() {

        // given
        PublisherEntity publisherEntity = new PublisherEntity(1, "Pearson");

        when(publisherRepository.getById(anyInt())).thenReturn(publisherEntity);

        // when
        Map<String, Boolean> response = underTest.updatePublication(anyInt(), "Pearson");

        // then
        assertThat(response.get("result")).isTrue();

    }

    @Test
    void removePublication() {

        // given
        PublisherEntity publisherEntity = new PublisherEntity(1, "Pearson");

        when(publisherRepository.getPublisher(anyString())).thenReturn(publisherEntity);

        // when
        Map<String, Boolean> response = underTest.removePublication(anyString());

        // then
        assertThat(response.get("result")).isTrue();

    }

    @Test
    void getBooksPublishedBy() {

        // given
        BookEntity bookEntity = new BookEntity(1, "In Search of Lost Time", "Marcel Proust", 1, "Romance", true, -1);
        List<BookEntity> bookEntities = new ArrayList<>(Collections.singleton(bookEntity));
        MemberEntity memberEntity = new MemberEntity(1, "Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");
        PublisherEntity publisherEntity = new PublisherEntity(1, "Pearson");

        when(bookRepository.getBooksPublishedBy(anyInt())).thenReturn(bookEntities);
        when(memberRepository.getById(anyInt())).thenReturn(memberEntity);
        when(publisherRepository.getPublisher(anyString())).thenReturn(publisherEntity);
        when(publisherRepository.getById(anyInt())).thenReturn(publisherEntity);

        // when
        List<Book> response = underTest.getBooksPublishedBy(anyString());

        // then
        assertThat(response).isNotNull();

    }

    @Test
    void getBookCountGenreWise() {

        // given
        PublisherEntity publisherEntity = new PublisherEntity(1, "Pearson");
        List<String> genres = new ArrayList<>(Collections.singleton("Romance"));

        when(publisherRepository.getPublisher(anyString())).thenReturn(publisherEntity);
        when(bookRepository.getAllGenres()).thenReturn(genres);
        when(bookRepository.getBookCountByGenreAndPublication(anyString(), anyInt())).thenReturn(1);

        // when
        Map<String, Integer> response = underTest.getBookCountGenreWise(anyString());

        // then
        assertThat(response).isNotNull();

    }

    @Test
    void getPublicationProfile() {

        // given
        BookEntity bookEntity = new BookEntity(1, "In Search of Lost Time", "Marcel Proust", 1, "Romance", true, -1);
        List<BookEntity> bookEntities = new ArrayList<>(Collections.singleton(bookEntity));
        PublisherEntity publisherEntity = new PublisherEntity(1, "Pearson");
        List<String> authors = new ArrayList<>(Collections.singleton("Marcel Proust"));
        List<String> genres = new ArrayList<>(Collections.singleton("Romance"));

        when(publisherRepository.getPublisher(anyString())).thenReturn(publisherEntity);
        when(bookRepository.getBooksPublishedBy(anyInt())).thenReturn(bookEntities);
        when(bookRepository.getAllGenres()).thenReturn(genres);
        when(bookRepository.getAllAuthors()).thenReturn(authors);
        when(bookRepository.getBookCountByGenreAndPublication(anyString(), anyInt())).thenReturn(1);
        when(bookRepository.getBookCountByAuthorAndPublication(anyString(), anyInt())).thenReturn(1);

        // when
        PublicationProfile response = underTest.getPublicationProfile(anyString());

        // then
        assertThat(response).isNotNull();

    }

    @Test
    void checkPublication() {

        // when
        underTest.checkPublication(anyString());

        // then
        verify(publisherRepository).getPublisher(anyString());

    }
}