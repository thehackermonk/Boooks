package com.hacker.boooks.bean;

import com.hacker.boooks.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

    private int bookID;
    private String title;
    private String author;
    private String publication;
    private String genre;
    private boolean available;
    private Integer holder;

    public Book(BookEntity bookEntity) {
        this.bookID = bookEntity.getBookID();
        this.title = bookEntity.getTitle();
        this.author = bookEntity.getAuthor();
        this.publication = bookEntity.getPublication();
        this.genre = bookEntity.getGenre();
        this.available = bookEntity.isAvailable();
        this.holder = bookEntity.getHolder();
    }

}
