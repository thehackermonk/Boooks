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
     * @apiNote Get suggestions for a member
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public List<Book> getSuggestions(int membershipID) {

        List<Book> books = new ArrayList<>();

        MemberEntity member = memberRepository.getById(membershipID);
        List<MemberEntity> membersOfSameAgeGroup;

        List<Integer> years;
        int startYear, endYear;

        int age = (LocalDate.now().getYear()) - (member.getDateOfBirth().toLocalDate().getYear());

        if (age >= 18 && age < 20) {

            startYear = (LocalDate.now().getYear()) - 18;
            endYear = (LocalDate.now().getYear()) - 20;

        } else if (age >= 20 && age < 30) {

            startYear = (LocalDate.now().getYear()) - 20;
            endYear = (LocalDate.now().getYear()) - 30;

        } else if (age >= 30 && age < 40) {

            startYear = (LocalDate.now().getYear()) - 30;
            endYear = (LocalDate.now().getYear()) - 40;

        } else if (age >= 40 && age < 50) {

            startYear = (LocalDate.now().getYear()) - 40;
            endYear = (LocalDate.now().getYear()) - 50;

        } else {

            startYear = (LocalDate.now().getYear()) - 50;
            endYear = (LocalDate.now().getYear());
        }

        membersOfSameAgeGroup = memberRepository.getMembersOfAgeBetween(startYear, endYear);

        for (MemberEntity memberOfSameAgeGroup : membersOfSameAgeGroup) {

            List<Integer> booksReadBy = logRepository.getBooksReadBy(memberOfSameAgeGroup.getMembershipId());

            for (int bookID : booksReadBy) {

                BookEntity bookEntity = bookRepository.getById(bookID);

                Book book = new Book();

                int holderID = bookEntity.getHolder();

                Publication publication = publicationService.getPublication(bookEntity.getPublication());

                book.setBookID(bookEntity.getBookID());
                book.setName(bookEntity.getName());
                book.setAuthor(bookEntity.getAuthor());
                book.setPublication(publication);
                book.setGenre(bookEntity.getGenre());
                book.setAvailable(bookEntity.isAvailable());

                if (holderID == -1)
                    book.setHolder(null);
                else {

                    MemberEntity memberEntity = memberRepository.getById(holderID);

                    Member holder = new Member(memberEntity);

                    book.setHolder(holder);

                }

                books.add(book);

            }

        }

        log.info(Response.SUGGESTIONS_FRIENDS_FETCHED);

        List<BookEntity> genreBasedSuggestion=bookRepository.getFiveBooksOfGenre(member.getFavoriteGenre());

        for(BookEntity bookEntity:genreBasedSuggestion) {

            Book book = new Book();

            int holderID = bookEntity.getHolder();

            Publication publication = publicationService.getPublication(bookEntity.getPublication());

            book.setBookID(bookEntity.getBookID());
            book.setName(bookEntity.getName());
            book.setAuthor(bookEntity.getAuthor());
            book.setPublication(publication);
            book.setGenre(bookEntity.getGenre());
            book.setAvailable(bookEntity.isAvailable());

            if (holderID == -1)
                book.setHolder(null);
            else {

                MemberEntity memberEntity = memberRepository.getById(holderID);

                Member holder = new Member(memberEntity);

                book.setHolder(holder);

            }

            books.add(book);

        }

        log.info(Response.SUGGESTIONS_GENRE_FETCHED);

        return books;

    }

}
