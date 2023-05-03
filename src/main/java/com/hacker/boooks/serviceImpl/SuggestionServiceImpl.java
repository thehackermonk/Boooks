package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.Book;
import com.hacker.boooks.bean.Member;
import com.hacker.boooks.bean.Publication;
import com.hacker.boooks.constant.Response;
import com.hacker.boooks.entity.BookEntity;
import com.hacker.boooks.entity.MemberEntity;
import com.hacker.boooks.repository.BookRepository;
import com.hacker.boooks.repository.LogRepository;
import com.hacker.boooks.repository.MemberRepository;
import com.hacker.boooks.service.PublicationService;
import com.hacker.boooks.service.SuggestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@SuppressWarnings("unused")
public class SuggestionServiceImpl implements SuggestionService {

    Logger log = LoggerFactory.getLogger(SuggestionServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PublicationService publicationService;

    /**
     * @param membershipID Unique ID of member
     * @return Book suggestions curated for the member
     * @apiNote Users favourite genre and books that same age group read
     * @author [@thehackermonk]
     * @since 1.0
     */

}
