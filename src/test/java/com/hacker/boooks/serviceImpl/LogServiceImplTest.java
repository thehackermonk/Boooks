package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.Log;
import com.hacker.boooks.entity.BookEntity;
import com.hacker.boooks.entity.LogEntity;
import com.hacker.boooks.entity.MemberEntity;
import com.hacker.boooks.repository.BookRepository;
import com.hacker.boooks.repository.LogRepository;
import com.hacker.boooks.repository.MemberRepository;
import com.hacker.boooks.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LogServiceImplTest {

    @InjectMocks
    private LogServiceImpl underTest;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private LogRepository logRepository;
    @Mock
    private MemberRepository memberRepository;

    @Mock
    @SuppressWarnings("unused")
    private BookService bookService;

    @Test
    void getNextSlNo() {

        // when
        underTest.getNextSlNo();

        // then
        verify(logRepository).getLastSlNo();

    }

    @Test
    void getLogs() {

        // given
        BookEntity bookEntity = new BookEntity(1, "In Search of Lost Time", "Marcel Proust", 1, "Romance", true, -1);

        MemberEntity memberEntity = new MemberEntity(1, "Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");

        when(bookRepository.getById(anyInt())).thenReturn(bookEntity);
        when(memberRepository.getById(anyInt())).thenReturn(memberEntity);

        // when
        List<Log> logs = underTest.getLogs();

        // then
        assertThat(logs).isNotNull();

    }

    @Test
    void getUnreturnedBooks() {

        // when
        underTest.getUnreturnedBooks(anyInt());

        // then
        verify(logRepository).getUnreturnedBooks(anyInt());

    }

    @Test
    void getUnreturnedBookLog() {

        // given
        BookEntity bookEntity = new BookEntity(1, "In Search of Lost Time", "Marcel Proust", 1, "Romance", true, -1);
        LogEntity logEntity = new LogEntity(1, 1, 1, LocalDate.now(), LocalDate.now(), 7.5f);
        MemberEntity memberEntity = new MemberEntity(1, "Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");

        when(logRepository.getUnreturnedBookLog(anyInt(), anyInt())).thenReturn(logEntity);
        when(bookRepository.getById(anyInt())).thenReturn(bookEntity);
        when(memberRepository.getById(anyInt())).thenReturn(memberEntity);

        // when
        underTest.getUnreturnedBookLog(anyInt(), anyInt());

        // then
        verify(logRepository).getUnreturnedBookLog(anyInt(), anyInt());

    }

    @Test
    void addLog() {

        // when
        underTest.addLog(1, 1, 1, LocalDate.now());

        // then
        verify(logRepository).save(any());

    }

    @Test
    void updateLog() {

        // given
        LogEntity logEntity = new LogEntity(1, 1, 1, LocalDate.now(), LocalDate.now(), 7.5f);

        when(logRepository.getUnreturnedBookLog(anyInt(), anyInt())).thenReturn(logEntity);

        // when
        underTest.updateLog(1, 1, LocalDate.now(), 7.5f);

        // then
        verify(logRepository).save(any());

    }
}