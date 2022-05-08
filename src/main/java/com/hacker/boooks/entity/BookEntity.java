package com.hacker.boooks.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @Column(name = "book_id")
    int bookID;
    String name;
    String author;
    int publication;
    String genre;
    boolean available;
    int holder;

    public BookEntity() {
    }

    public BookEntity(int bookID, String name, String author, int publication, String genre, boolean available, int holder) {
        this.bookID = bookID;
        this.name = name;
        this.author = author;
        this.publication = publication;
        this.genre = genre;
        this.available = available;
        this.holder = holder;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublication() {
        return publication;
    }

    public void setPublication(int publication) {
        this.publication = publication;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getHolder() {
        return holder;
    }

    public void setHolder(int holder) {
        this.holder = holder;
    }
}
