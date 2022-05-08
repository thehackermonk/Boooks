package com.hacker.boooks.bean;

import com.hacker.boooks.entity.PublisherEntity;

public class Publication {

    private int publisherID;
    private String publicationName;

    public Publication() {
    }

    public Publication(PublisherEntity publisherEntity) {
        this.publisherID = publisherEntity.getPublisherId();
        this.publicationName = publisherEntity.getName();
    }

    public Publication(int publisherID, String publicationName) {
        this.publisherID = publisherID;
        this.publicationName = publicationName;
    }

    public int getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(int publisherID) {
        this.publisherID = publisherID;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
    }
}
