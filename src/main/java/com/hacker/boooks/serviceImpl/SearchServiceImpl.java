package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.SearchResult;
import com.hacker.boooks.entity.BookEntity;
import com.hacker.boooks.entity.MemberEntity;
import com.hacker.boooks.entity.PublisherEntity;
import com.hacker.boooks.repository.BookRepository;
import com.hacker.boooks.repository.MemberRepository;
import com.hacker.boooks.repository.PublisherRepository;
import com.hacker.boooks.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@SuppressWarnings("unused")
public class SearchServiceImpl implements SearchService {

    Logger log = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    /**
     * @param keyword Keyword to search
     * @return Search results
     * @apiNote Search entire application
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public List<SearchResult> search(String keyword) {

        List<SearchResult> searchResults = new ArrayList<>();

        List<BookEntity> bookSearch = bookRepository.searchBook("%" + keyword + "%");

        List<String> authorSearch = bookRepository.searchAuthor("%" + keyword + "%");
        List<MemberEntity> memberSearch = memberRepository.searchMember("%" + keyword + "%");
        List<PublisherEntity> publisherSearch = publisherRepository.searchPublisher("%" + keyword + "%");

        for (BookEntity book : bookSearch) {
            SearchResult searchResult = new SearchResult(book.getName(), "Book");
            searchResults.add(searchResult);

        }

        for (String author : authorSearch) {
            SearchResult searchResult = new SearchResult(author, "Author");
            searchResults.add(searchResult);
        }

        for (MemberEntity member : memberSearch) {
            SearchResult searchResult = new SearchResult(member.getName(), "Member");
            searchResults.add(searchResult);
        }

        for (PublisherEntity publisher : publisherSearch) {
            SearchResult searchResult = new SearchResult(publisher.getName(), "Publisher");
            searchResults.add(searchResult);
        }

        log.info(keyword+" searched.");

        return searchResults;

    }

}
