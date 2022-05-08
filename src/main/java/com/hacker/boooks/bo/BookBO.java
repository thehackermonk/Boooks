package com.hacker.boooks.bo;

public class BookBO {

    private String name;
    private String author;
    private String publication;
    private String genre;

    public BookBO() {
    }

    public BookBO(String name, String author, String publication, String genre) {
        this.name = name;
        this.author = author;
        this.publication = publication;
        this.genre = genre;
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

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
