package com.hacker.boooks.bean;

public class AuthorProfile {

    private String name;
    private int noOfBooksWritten;
    private String favoriteGenre;
    private Book mostReadBookBy;

    public AuthorProfile() {
    }

    public AuthorProfile(String name, int noOfBooksWritten, String favoriteGenre, Book mostReadBookBy) {
        this.name = name;
        this.noOfBooksWritten = noOfBooksWritten;
        this.favoriteGenre = favoriteGenre;
        this.mostReadBookBy = mostReadBookBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoOfBooksWritten() {
        return noOfBooksWritten;
    }

    public void setNoOfBooksWritten(int noOfBooksWritten) {
        this.noOfBooksWritten = noOfBooksWritten;
    }

    public String getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(String favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    public Book getMostReadBookBy() {
        return mostReadBookBy;
    }

    public void setMostReadBookBy(Book mostReadBookBy) {
        this.mostReadBookBy = mostReadBookBy;
    }
}
