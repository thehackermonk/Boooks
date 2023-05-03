package com.hacker.boooks.bo;

import com.hacker.boooks.bean.BookRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookBO {

    private String name;
    private String author;
    private String publication;
    private String genre;

    public BookBO(BookRequest bookRequest) {
        this.name = bookRequest.getName();
        this.author = bookRequest.getAuthor();
        this.publication = bookRequest.getPublication();
        this.genre = bookRequest.getGenre();
    }
}
