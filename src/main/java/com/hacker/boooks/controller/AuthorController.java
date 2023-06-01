package com.hacker.boooks.controller;

import com.hacker.boooks.bean.AuthorProfile;
import com.hacker.boooks.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@Tag(name = "Author Management", description = "APIs for managing authors")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@SuppressWarnings("unused")
public class AuthorController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{name}/profile")
    @Operation(summary = "Get author Profile", description = "This API allows you to retrieve the profile of an author based on their name. It provides information such as the author's name, the number of books written by the author, the most written genre by the author, a list of books written by the author, and the most read book by the author's audience. Use this API to gain insights into an author's background and literary accomplishments.")
    public ResponseEntity<AuthorProfile> getAuthorProfile(@PathVariable String name) {
        return bookService.getAuthorProfile(name);
    }

}
