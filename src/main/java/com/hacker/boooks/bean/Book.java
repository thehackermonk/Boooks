package com.hacker.boooks.bean;

public class Book {

    private int bookID;
    private String name;
    private String author;
    private Publication publication;
    private String genre;
    private boolean available;
    private Member holder;

    public Book() {
    }

    public Book(int bookID, String name, String author, Publication publication, String genre, boolean available, Member holder) {
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

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
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

    public Member getHolder() {
        return holder;
    }

    public void setHolder(Member holder) {
        this.holder = holder;
    }
}
