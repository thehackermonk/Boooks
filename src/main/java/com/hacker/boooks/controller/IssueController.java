package com.hacker.boooks.controller;

import com.hacker.boooks.bean.IssueResponse;
import com.hacker.boooks.bean.ReturnResponse;
import com.hacker.boooks.service.IssueService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@Tag(name = "Book Management", description = "APIs for managing books")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@SuppressWarnings("unused")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping("/issue")
    public ResponseEntity<IssueResponse> issueBook(@RequestParam int bookId, @RequestParam int memberId) {
        return issueService.issueBook(bookId, memberId);
    }

    @PostMapping("/return")
    public ResponseEntity<ReturnResponse> returnBook(@RequestParam int bookId, @RequestParam int memberId) {
        return issueService.returnBook(bookId, memberId);
    }

}
