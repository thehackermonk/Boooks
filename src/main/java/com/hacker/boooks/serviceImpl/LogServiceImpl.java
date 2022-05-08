package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.Book;
import com.hacker.boooks.bean.Log;
import com.hacker.boooks.bean.Member;
import com.hacker.boooks.constant.Response;
import com.hacker.boooks.entity.BookEntity;
import com.hacker.boooks.entity.LogEntity;
import com.hacker.boooks.entity.MemberEntity;
import com.hacker.boooks.repository.BookRepository;
import com.hacker.boooks.repository.LogRepository;
import com.hacker.boooks.repository.MemberRepository;
import com.hacker.boooks.service.BookService;
import com.hacker.boooks.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@SuppressWarnings("unused")
public class LogServiceImpl implements LogService {

    Logger log = LoggerFactory.getLogger(LogServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookService bookService;

    /**
     * @return Next sl. no.
     * @apiNote Get next sl. no.
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public int getNextSlNo() {

        int lastSlNo = logRepository.getLastSlNo();

        return (lastSlNo + 1);

    }

    /**
     * @return Logs
     * @apiNote Get all logs
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public List<Log> getLogs() {

        List<Log> logs = new ArrayList<>();

        List<LogEntity> logEntities = logRepository.findAll();

        for (LogEntity logEntity : logEntities) {

            BookEntity bookEntity = bookRepository.getById(logEntity.getBookId());
            Book book = bookService.getBookDetails(bookEntity.getName());

            MemberEntity memberEntity = memberRepository.getById(logEntity.getMembershipId());
            Member member = new Member(memberEntity);

            LocalDate issueDate = logEntity.getIssueDate().toLocalDate();
            LocalDate returnDate;

            if (logEntity.getReturnDate() != null)
                returnDate = logEntity.getReturnDate().toLocalDate();
            else
                returnDate = null;

            float fine = logEntity.getFine();

            Log log = new Log(book, member, issueDate, returnDate, fine);

            logs.add(log);

        }

        log.info(Response.LOGS_FETCHED);

        return logs;

    }

    /**
     * @param membershipID Unique ID of the member
     * @return Unreturned books
     * @apiNote Get unreturned books
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public List<Integer> getUnreturnedBooks(int membershipID) {

        List<Integer> bookIDList = new ArrayList<>();

        List<LogEntity> unreturnedBooks = logRepository.getUnreturnedBooks(membershipID);

        for (LogEntity bookLog : unreturnedBooks) {

            bookIDList.add(bookLog.getBookId());

        }

        log.info(Response.UNRETURNED_BOOKS_FETCHED);

        return bookIDList;

    }

    /**
     * @param bookID       Unique ID of the book
     * @param membershipID Unique ID of the member
     * @return Log of unreturned books
     * @apiNote Get log of unreturned books
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Log getUnreturnedBookLog(int bookID, int membershipID) {

        Log bookLog = new Log();

        LogEntity logEntity = logRepository.getUnreturnedBookLog(bookID, membershipID);

        BookEntity bookEntity = bookRepository.getById(logEntity.getBookId());
        Book book = bookService.getBookDetails(bookEntity.getName());

        MemberEntity memberEntity = memberRepository.getById(logEntity.getMembershipId());
        Member member = new Member((memberEntity));

        bookLog.setBook(book);
        bookLog.setMember(member);

        bookLog.setIssueDate(logEntity.getIssueDate().toLocalDate());

        log.info("Log of unreturned book " + bookID + " fetched.");

        return bookLog;

    }

    /**
     * @param slNo         Sl. no.
     * @param bookID       Unique ID of the book
     * @param membershipID Unique ID of the book
     * @param issueDate    Issue date
     * @apiNote Add log
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public void addLog(int slNo, int bookID, int membershipID, LocalDate issueDate) {

        LogEntity logEntity = new LogEntity();

        logEntity.setSlNo(slNo);
        logEntity.setBookId(bookID);
        logEntity.setMembershipId(membershipID);
        logEntity.setIssueDate(java.sql.Date.valueOf(issueDate));
        logEntity.setFine(0);

        logRepository.save(logEntity);

        log.info("Log for book " + bookID + " and membership id " + membershipID + " added.");

    }

    /**
     * @param bookID       Unique ID of the book
     * @param membershipID Unique of the member
     * @param returnDate   Date of return
     * @param fine         Fine amount
     * @apiNote Update log
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public void updateLog(int bookID, int membershipID, LocalDate returnDate, float fine) {

        LogEntity logEntity = logRepository.getUnreturnedBookLog(bookID, membershipID);

        logEntity.setReturnDate(java.sql.Date.valueOf(returnDate));
        logEntity.setFine(fine);

        log.info("Log for book " + bookID + " and membership id " + membershipID + " updated.");

        logRepository.save(logEntity);

    }

}
