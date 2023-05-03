package com.hacker.boooks.bean;

import com.hacker.boooks.entity.PublisherEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Publication {

    private String publicationName;
    private String email;
    private String website;

    public Publication(PublisherEntity publisherEntity) {
        this.publicationName = publisherEntity.getName();
        this.email = publisherEntity.getEmail();
        this.website = publisherEntity.getWebsite();
    }

}
