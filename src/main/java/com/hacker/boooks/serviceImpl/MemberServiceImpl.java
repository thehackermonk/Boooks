package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.Book;
import com.hacker.boooks.bean.Member;
import com.hacker.boooks.bean.Publication;
import com.hacker.boooks.bo.MemberBO;
import com.hacker.boooks.constant.Response;
import com.hacker.boooks.entity.BookEntity;
import com.hacker.boooks.entity.MemberEntity;
import com.hacker.boooks.repository.BookRepository;
import com.hacker.boooks.repository.LogRepository;
import com.hacker.boooks.repository.MemberRepository;
import com.hacker.boooks.service.LogService;
import com.hacker.boooks.service.MemberService;
import com.hacker.boooks.service.PublicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
@SuppressWarnings("unused")
public class MemberServiceImpl implements MemberService {

    Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LogService logService;
    @Autowired
    private PublicationService publicationService;

    /**
     * @return Next membership ID
     * @apiNote Get next membership ID
     * @author [@thehackermonk]
     * @since 1.0
     */
    private int getNextMembershipID() {

        int lastMembershipID = memberRepository.getLastMemberID();

        return lastMembershipID + 1;

    }

    /**
     * @param membershipID Unique ID of the member
     * @return Member details
     * @apiNote Get member details
     * @author [@thehackermonk]
     * @since 1.0
     */
    public Member getMemberDetails(int membershipID) {

        MemberEntity memberEntity = memberRepository.getById(membershipID);

        log.info("Details of member " + memberEntity.getName() + " (" + membershipID + ") fetched.");

        return new Member(memberEntity);

    }

    /**
     * @param memberBO member details
     * @return true if addition succeeds, and false otherwise
     * @apiNote Add new member
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Map<String, Boolean> addMember(MemberBO memberBO) {

        MemberEntity memberEntity, memberContactEntity, memberEmailEntity;

        memberContactEntity = memberRepository.getMemberByContact(memberBO.getContact());
        memberEmailEntity = memberRepository.getMemberByEmail(memberBO.getEmail());

        if (memberContactEntity == null && memberEmailEntity == null) {

            memberEntity = new MemberEntity(getNextMembershipID(), memberBO);

            memberRepository.save(memberEntity);

            log.info(memberBO.getName() + " added successfully!");

            return Collections.singletonMap("result", true);

        } else {

            return Collections.singletonMap("result", false);

        }
    }

    /**
     * @param contact Contact of the member
     * @return True if contact is unused, and false otherwise
     * @apiNote To check if contact is unused
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Map<String, Boolean> checkContact(String contact) {

        MemberEntity memberEntity = memberRepository.getMemberByContact(contact);

        if (memberEntity == null)
            return Collections.singletonMap("result", true);
        else
            return Collections.singletonMap("result", false);

    }

    /**
     * @param email Email ID of the member
     * @return True if email ID is unused, and false otherwise
     * @apiNote To check if email ID is unused
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Map<String, Boolean> checkEmail(String email) {

        MemberEntity memberEntity = memberRepository.getMemberByEmail(email);

        if (memberEntity == null)
            return Collections.singletonMap("result", true);
        else
            return Collections.singletonMap("result", false);

    }

    /**
     * @param membershipID Unique ID of the member
     * @param memberBO     Member details
     * @return true if update succeeds, false otherwise
     * @apiNote Update member
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Map<String, Boolean> updateMember(int membershipID, MemberBO memberBO) {

        MemberEntity memberEntity = memberRepository.getById(membershipID);

        if (!memberEntity.getName().equals(memberBO.getName()))
            memberEntity.setName(memberBO.getName());

        if (!Objects.equals(memberEntity.getDateOfBirth(), Date.valueOf(memberBO.getDateOfBirth())))
            memberEntity.setDateOfBirth(java.sql.Date.valueOf(memberBO.getDateOfBirth()));

        if (!memberEntity.getEmail().equals(memberBO.getEmail()))
            memberEntity.setEmail(memberBO.getEmail());

        if (!memberEntity.getContact().equals(memberBO.getContact()))
            memberEntity.setContact(memberBO.getContact());

        if (!memberEntity.getGender().equals(memberBO.getGender()))
            memberEntity.setGender(memberBO.getGender());

        if (!memberEntity.getFavoriteGenre().equals(memberBO.getFavoriteGenre()))
            memberEntity.setFavoriteGenre(memberBO.getFavoriteGenre());

        log.info(membershipID + " updated!");

        memberRepository.save(memberEntity);

        return Collections.singletonMap("result", true);

    }

    /**
     * @param membershipID Unique ID of the member
     * @return true if update succeeds, false otherwise
     * @apiNote Remove member
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Map<String, Boolean> removeMember(int membershipID) {

        MemberEntity member = memberRepository.getById(membershipID);

        log.info(membershipID + " removed successfully.");

        memberRepository.delete(member);

        return Collections.singletonMap("result", true);

    }

    /**
     * @param membershipID Unique ID of member
     * @return List of books which a member is holding
     * @apiNote Get books which a member is holding
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public List<Book> getBooksHoldingByMember(int membershipID) {

        List<Book> booksMemberIsHolding = new ArrayList<>();

        List<Integer> bookIDList = logService.getUnreturnedBooks(membershipID);

        for (int bookID : bookIDList) {

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

            MemberEntity memberEntity = memberRepository.getById(holderID);

            Member holder = new Member(memberEntity);

            book.setHolder(holder);

            booksMemberIsHolding.add(book);

        }

        log.info("Books " + membershipID + " is holding fetched.");

        return booksMemberIsHolding;

    }

    /**
     * @return List of membership IDs
     * @apiNote Get membership IDs
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public List<Integer> getMembershipIDs() {

        List<MemberEntity> memberEntities = memberRepository.findAll();
        List<Integer> membershipIDs = new ArrayList<>();

        for (MemberEntity memberEntity : memberEntities)
            membershipIDs.add(memberEntity.getMembershipId());

        log.info(Response.MEMBERSHIP_IDS_FETCHED);

        return membershipIDs;

    }

    /**
     * @param membershipID Unique ID of the member
     * @return Count of books read genre-wise
     * @apiNote Get count of books read genre-wise
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Map<String, Integer> getBookCountGenreWise(int membershipID) {

        Map<String, Integer> genrewiseBookCount = new HashMap<>();

        List<Integer> booksReadByMember = logRepository.getBooksReadBy(membershipID);

        List<String> genres = bookRepository.getAllGenres();

        for (String genre : genres)
            genrewiseBookCount.put(genre, 0);

        for (int bookID : booksReadByMember) {

            BookEntity bookEntity = bookRepository.getById(bookID);
            genrewiseBookCount.put(bookEntity.getGenre(), genrewiseBookCount.get(bookEntity.getGenre()) + 1);

        }

        return genrewiseBookCount;

    }

}
