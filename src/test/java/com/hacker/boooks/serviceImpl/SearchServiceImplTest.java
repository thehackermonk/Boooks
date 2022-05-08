package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.SearchResult;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SearchServiceImplTest {

    @InjectMocks
    private SearchServiceImpl underTest;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private PublisherRepository publisherRepository;

    @Test
    void search() {

        // given
        BookEntity bookEntity = new BookEntity(1, "In Search of Lost Time", "Marcel Proust", 1, "Romance", true, -1);
        MemberEntity memberEntity = new MemberEntity(1, "Brandon Reynolds", LocalDate.now(), "brandon.reynolds@gmail.com", "9090974785", "male", "Romance");
        PublisherEntity publisherEntity = new PublisherEntity(1, "Pearson");
        List<BookEntity> bookEntities = new ArrayList<>(Collections.singleton(bookEntity));
        List<MemberEntity> memberEntities = new ArrayList<>(Collections.singleton(memberEntity));
        List<PublisherEntity> publisherEntities = new ArrayList<>(Collections.singleton(publisherEntity));
        List<String> stringList = new ArrayList<>(Collections.singleton("string"));

        when(bookRepository.searchBook(anyString())).thenReturn(bookEntities);
        when(bookRepository.searchAuthor(anyString())).thenReturn(stringList);
        when(memberRepository.searchMember(anyString())).thenReturn(memberEntities);
        when(publisherRepository.searchPublisher(anyString())).thenReturn(publisherEntities);

        // when
        List<SearchResult> response = underTest.search(anyString());

        // then
        assertThat(response).isNotNull();

    }
}