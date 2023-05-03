package com.hacker.boooks.controller;

import com.hacker.boooks.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for issue book
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/books")
@CrossOrigin(origins = "*")
@SuppressWarnings("unused")
public class IssueBookController {

    @Autowired
    private IssueService issueService;

    /**
     * @param bookName     Name of the book
     * @param membershipID Unique ID of the member
     * @return true if book issued successfully, false otherwise
     * @apiNote Issue book
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @PostMapping("/{bookId}/issue")
//    public LocalDate issueBook(@RequestHeader String bookName, @RequestHeader int membershipID) {
//
//        return issueService.issueBook(bookName, membershipID);
//
//    }

    /**
     * @param membershipID Unique ID of the member
     * @param bookID       Unique ID of the book
     * @return Issue date, expected return date, return date, and fine
     * @apiNote Return book
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @PostMapping("/{bookId}/return")
//    public ReturnBook returnBook(@RequestHeader int membershipID, @RequestHeader int bookID) {
//        return returnService.returnBook(membershipID, bookID);
//    }

}
