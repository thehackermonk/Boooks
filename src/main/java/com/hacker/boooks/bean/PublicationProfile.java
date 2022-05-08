package com.hacker.boooks.bean;

public class PublicationProfile {

    private String publicationName;
    private int noOfBooksPublished;
    private String favoriteGenre;
    private String favoriteAuthor;

    public PublicationProfile() {
    }

    public PublicationProfile(String publicationName, int noOfBooksPublished, String favoriteGenre, String favoriteAuthor) {
        this.publicationName = publicationName;
        this.noOfBooksPublished = noOfBooksPublished;
        this.favoriteGenre = favoriteGenre;
        this.favoriteAuthor = favoriteAuthor;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
    }

    public int getNoOfBooksPublished() {
        return noOfBooksPublished;
    }

    public void setNoOfBooksPublished(int noOfBooksPublished) {
        this.noOfBooksPublished = noOfBooksPublished;
    }

    public String getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(String favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    public String getFavoriteAuthor() {
        return favoriteAuthor;
    }

    public void setFavoriteAuthor(String favoriteAuthor) {
        this.favoriteAuthor = favoriteAuthor;
    }

}
