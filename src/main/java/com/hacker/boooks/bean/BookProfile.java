package com.hacker.boooks.bean;

import com.hacker.boooks.entity.BookEntity;
import com.hacker.boooks.entity.MemberEntity;
import com.hacker.boooks.entity.PublisherEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookProfile {

    private String title;
    private String author;
    private Publication publication;
    private String genre;
    private boolean available;
    private Member holder;

    public BookProfile(BookEntity bookEntity, PublisherEntity publisherEntity, MemberEntity memberEntity) {
        this.setTitle(bookEntity.getTitle());
        this.setAuthor(bookEntity.getAuthor());
        if (publisherEntity != null)
            this.setPublication(new Publication(publisherEntity));
        this.setGenre(bookEntity.getGenre());
        this.setAvailable(bookEntity.isAvailable());
        if (memberEntity != null)
            this.setHolder(new Member(memberEntity));
    }

}
