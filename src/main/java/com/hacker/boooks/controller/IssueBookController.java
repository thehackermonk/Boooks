package com.hacker.boooks.controller;

import com.hacker.boooks.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for issue book
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/boooks")
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
    @PostMapping("/issuebook")
    @ResponseBody
    public LocalDate issueBook(@RequestHeader String bookName, @RequestHeader int membershipID) {

        return issueService.issueBook(bookName, membershipID);

    }

}
