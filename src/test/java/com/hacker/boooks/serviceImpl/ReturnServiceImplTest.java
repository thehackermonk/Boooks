package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.*;
import com.hacker.boooks.entity.BookEntity;
import com.hacker.boooks.repository.BookRepository;
import com.hacker.boooks.service.FineService;
import com.hacker.boooks.service.LogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ReturnServiceImplTest {

    @InjectMocks
    private ReturnServiceImpl underTest;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private FineService fineService;
    @Mock
    private LogService logService;

    @Test
    void returnBook() {

        // given
        BookEntity bookEntity = new BookEntity(1, "In Search of Lost Time", "Marcel Proust", 1, "Romance", true, -1);
        Publication publication = new Publication(1, "Pearson");
        Member member = new Member(1, "Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");
        Book book = new Book(1, "In Search of Lost Time", "Marcel Proust", publication, "Romance", false, member);
        Log log = new Log(book, member, LocalDate.now().minusDays(10), null, 0.0f);

        when(bookRepository.getById(anyInt())).thenReturn(bookEntity);
        when(logService.getUnreturnedBookLog(anyInt(), anyInt())).thenReturn(log);
        when(fineService.getDaysAfterFineIsCharged()).thenReturn(5);
        when(fineService.getFineAmount()).thenReturn(1.0f);

        // when
        ReturnBook response = underTest.returnBook(1, 1);

        // then
        assertThat(response).isNotNull();

    }
}