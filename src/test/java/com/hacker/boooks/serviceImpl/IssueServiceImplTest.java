package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.Member;
import com.hacker.boooks.entity.BookEntity;
import com.hacker.boooks.repository.BookRepository;
import com.hacker.boooks.service.FineService;
import com.hacker.boooks.service.LogService;
import com.hacker.boooks.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class IssueServiceImplTest {

    @InjectMocks
    private IssueServiceImpl underTest;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private FineService fineService;
    @Mock
    private LogService logService;
    @Mock
    private MemberService memberService;

    @Test
    void issueBook() {

        // given
        BookEntity bookEntity = new BookEntity(1, "In Search of Lost Time", "Marcel Proust", 1, "Romance", true, -1);
        Member member = new Member(1, "Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");

        when(bookRepository.getBookByName(anyString())).thenReturn(bookEntity);
        when(memberService.getMemberDetails(anyInt())).thenReturn(member);
        when(logService.getNextSlNo()).thenReturn(1);
        when(fineService.getDaysAfterFineIsCharged()).thenReturn(5);

        // when
        underTest.issueBook("In Search of Lost Time", 1);

        // then
        verify(bookRepository).save(any());
        verify(logService).addLog(anyInt(), anyInt(), anyInt(), any());

    }
}