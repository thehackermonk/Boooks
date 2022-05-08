package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.Book;
import com.hacker.boooks.bean.Publication;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SuggestionServiceImplTest {

    @InjectMocks
    private SuggestionServiceImpl underTest;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private LogRepository logRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private PublicationService publicationService;

    @Test
    void getSuggestions() {

        // given
        BookEntity bookEntity = new BookEntity(1, "In Search of Lost Time", "Marcel Proust", 1, "Romance", true, -1);
        MemberEntity memberEntity = new MemberEntity(1, "Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");
        Publication publication = new Publication(1, "Pearson");
        List<MemberEntity> memberEntities = new ArrayList<>(Collections.singleton(memberEntity));
        List<Integer> integers = new ArrayList<>(Collections.singleton(1));

        when(memberRepository.getById(anyInt())).thenReturn(memberEntity);
        when(memberRepository.getMembersOfAgeBetween(anyInt(), anyInt())).thenReturn(memberEntities);
        when(logRepository.getBooksReadBy(anyInt())).thenReturn(integers);
        when(bookRepository.getById(anyInt())).thenReturn(bookEntity);
        when(publicationService.getPublication(anyString())).thenReturn(publication);

        // when
        List<Book> response = underTest.getSuggestions(anyInt());

        // then
        assertThat(response).isNotNull();

    }
}