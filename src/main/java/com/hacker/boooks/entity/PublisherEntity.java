package com.hacker.boooks.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "publisher")
public class PublisherEntity {

    @Id
    @Column(name = "publisher_id")
    int publisherId;
    String name;

    public PublisherEntity() {
    }

    public PublisherEntity(int publisherId, String name) {
        this.publisherId = publisherId;
        this.name = name;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
