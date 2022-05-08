package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.Member;
import com.hacker.boooks.entity.BookEntity;
import com.hacker.boooks.repository.BookRepository;
import com.hacker.boooks.service.FineService;
import com.hacker.boooks.service.IssueService;
import com.hacker.boooks.service.LogService;
import com.hacker.boooks.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@SuppressWarnings("unused")
public class IssueServiceImpl implements IssueService {

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
     * @param bookName     Name of the book
     * @param membershipID Unique ID of the member
     * @apiNote Issue book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public LocalDate issueBook(String bookName, int membershipID) {

        BookEntity book = bookRepository.getBookByName(bookName);
        Member member = memberService.getMemberDetails(membershipID);

        LocalDate today = LocalDate.now();
        LocalDate toBePaid = today.plusDays(fineService.getDaysAfterFineIsCharged());

        int logSlNo = logService.getNextSlNo();

        book.setAvailable(false);
        book.setHolder(membershipID);

        bookRepository.save(book);

        logService.addLog(logSlNo, book.getBookID(), membershipID, today);

        log.info(bookName + " issued to " + member.getName());

        return toBePaid;

    }

}
