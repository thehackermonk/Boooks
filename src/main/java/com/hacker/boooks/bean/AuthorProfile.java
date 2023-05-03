package com.hacker.boooks.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorProfile {

    private String name;
    private int numBooksWritten;
    private String genreMostWritten;
    private Book mostPopularBook;

}
