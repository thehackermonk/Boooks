package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.Log;
import com.hacker.boooks.bean.ReturnBook;
import com.hacker.boooks.entity.BookEntity;
import com.hacker.boooks.repository.BookRepository;
import com.hacker.boooks.service.FineService;
import com.hacker.boooks.service.LogService;
import com.hacker.boooks.service.MemberService;
import com.hacker.boooks.service.ReturnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@SuppressWarnings("unused")
public class ReturnServiceImpl implements ReturnService {

    Logger log = LoggerFactory.getLogger(IssueServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private FineService fineService;
    @Autowired
    private LogService logService;
    @Autowired
    private MemberService memberService;

    /**
     * @param membershipID Unique ID of the member
     * @param bookID       Unique ID of the book
     * @return Issue date, expected return date, return date, fine
     * @apiNote Return book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public ReturnBook returnBook(int membershipID, int bookID) {

        ReturnBook returnBook = new ReturnBook();

        BookEntity book = bookRepository.getById(bookID);
        Log bookLog = logService.getUnreturnedBookLog(bookID, membershipID);

        int daysWithinBookShouldBeReturned = fineService.getDaysAfterFineIsCharged();
        float fine = 0.0f;

        LocalDate today = LocalDate.now();
        LocalDate expectedReturnDate = bookLog.getIssueDate().plusDays(daysWithinBookShouldBeReturned);

        book.setAvailable(true);
        book.setHolder(-1);

        bookRepository.save(book);

        if (today.isAfter(expectedReturnDate)) {

            fine = calculateFine(expectedReturnDate, today);

        }

        logService.updateLog(bookID, membershipID, today, fine);

        returnBook.setIssueDate(bookLog.getIssueDate());
        returnBook.setExpectedReturnDate(expectedReturnDate);
        returnBook.setReturnDate(today);
        returnBook.setFine(fine);

        log.info("Member " + membershipID + " returned book " + bookID);

        if (fine > 0)
            log.info("Fine to be paid: " + fine);

        return returnBook;

    }

    /**
     * @param expectedReturnDate Date when book should have been returned
     * @param today              Today's date
     * @return fine
     * @apiNote To calculate fine
     */
    private float calculateFine(LocalDate expectedReturnDate, LocalDate today) {

        float amountPerDay, amountToBePaid;

        long dateDifference = ChronoUnit.DAYS.between(expectedReturnDate, today);

        amountPerDay = fineService.getFineAmount();
        amountToBePaid = amountPerDay * dateDifference;

        log.info("Fine calculated.");

        return amountToBePaid;

    }

}
