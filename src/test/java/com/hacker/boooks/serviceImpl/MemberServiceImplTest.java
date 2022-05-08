package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.Book;
import com.hacker.boooks.bean.Publication;
import com.hacker.boooks.bo.MemberBO;
import com.hacker.boooks.entity.BookEntity;
import com.hacker.boooks.entity.MemberEntity;
import com.hacker.boooks.repository.BookRepository;
import com.hacker.boooks.repository.LogRepository;
import com.hacker.boooks.repository.MemberRepository;
import com.hacker.boooks.service.LogService;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MemberServiceImplTest {

    @InjectMocks
    private MemberServiceImpl underTest;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private LogRepository logRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private LogService logService;
    @Mock
    private PublicationService publicationService;

    @Test
    void getMemberDetails() {

        // given
        MemberEntity memberEntity = new MemberEntity(1, "Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");

        when(memberRepository.getById(anyInt())).thenReturn(memberEntity);

        // when
        underTest.getMemberDetails(anyInt());

        // then
        verify(memberRepository).getById(anyInt());

    }

    @Test
    void addMember() {

        // given
        MemberBO memberBO = new MemberBO("Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");

        // when
        underTest.addMember(memberBO);

        // then
        verify(memberRepository).save(any());

    }

    @Test
    void checkContact() {

        // when
        underTest.checkContact(anyString());

        // then
        verify(memberRepository).getMemberByContact(anyString());

    }

    @Test
    void checkEmail() {

        // when
        underTest.checkEmail(anyString());

        // then
        verify(memberRepository).getMemberByEmail(anyString());

    }

    @Test
    void updateMember() {

        // given
        MemberBO memberBO = new MemberBO("Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");
        MemberEntity memberEntity = new MemberEntity(1, "Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");

        when(memberRepository.getById(anyInt())).thenReturn(memberEntity);

        // when
        underTest.updateMember(anyInt(), memberBO);

        // then
        verify(memberRepository).save(any());

    }

    @Test
    void removeMember() {

        // given
        MemberEntity memberEntity = new MemberEntity(1, "Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");

        when(memberRepository.getById(anyInt())).thenReturn(memberEntity);

        // when
        underTest.removeMember(anyInt());

        // then
        verify(memberRepository).delete(any());

    }

    @Test
    void getBooksHoldingByMember() {

        // given
        List<Integer> bookIDList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        BookEntity bookEntity = new BookEntity(1, "In Search of Lost Time", "Marcel Proust", 1, "Romance", true, -1);
        MemberEntity memberEntity = new MemberEntity(1, "Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");
        Publication publication = new Publication(1, "Pearson");

        when(logService.getUnreturnedBooks(anyInt())).thenReturn(bookIDList);
        when(bookRepository.getById(anyInt())).thenReturn(bookEntity);
        when(publicationService.getPublication(anyString())).thenReturn(publication);
        when(memberRepository.getById(anyInt())).thenReturn(memberEntity);

        // when
        List<Book> response = underTest.getBooksHoldingByMember(anyInt());

        // then
        assertThat(response).isNotNull();

    }

    @Test
    void getMembershipIDs() {

        // given
        MemberEntity memberEntity = new MemberEntity(1, "Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");
        List<MemberEntity> memberEntities = new ArrayList<>(Collections.singleton(memberEntity));

        // when
        List<Integer> response = underTest.getMembershipIDs();

        // then
        assertThat(response).isNotNull();

    }

    @Test
    void getBookCountGenreWise() {

        // given
        List<Integer> booksReadByMember = new ArrayList<>(Collections.singleton(1));
        List<String> genres = new ArrayList<>(Collections.singleton("Romance"));
        BookEntity bookEntity = new BookEntity(1, "In Search of Lost Time", "Marcel Proust", 1, "Romance", true, -1);

        when(logRepository.getBooksReadBy(anyInt())).thenReturn(booksReadByMember);
        when(bookRepository.getAllGenres()).thenReturn(genres);
        when(bookRepository.getById(anyInt())).thenReturn(bookEntity);

        // when
        Map<String, Integer> response = underTest.getBookCountGenreWise(anyInt());

        // then
        assertThat(response).isNotNull();

    }
}