package com.hacker.boooks.controller;

import com.hacker.boooks.bean.ReturnBook;
import com.hacker.boooks.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for return book
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/boooks")
@SuppressWarnings("unused")
public class ReturnBookController {

    @Autowired
    private ReturnService returnService;

    /**
     * @param membershipID Unique ID of the member
     * @param bookID       Unique ID of the book
     * @return Issue date, expected return date, return date, and fine
     * @apiNote Return book
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/returnbook")
    @ResponseBody
    public ReturnBook returnBook(@RequestHeader int membershipID, @RequestHeader int bookID) {
        return returnService.returnBook(membershipID, bookID);
    }

}
