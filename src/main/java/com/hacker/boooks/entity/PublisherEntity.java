package com.hacker.boooks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "publisher")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PublisherEntity {

    @Id
    String name;
    String email;
    String website;

}
