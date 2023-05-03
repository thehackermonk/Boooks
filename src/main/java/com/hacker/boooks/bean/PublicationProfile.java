package com.hacker.boooks.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PublicationProfile {

    private String publicationName;
    private int noOfBooksPublished;
    private String favoriteGenre;
    private String favoriteAuthor;

}
