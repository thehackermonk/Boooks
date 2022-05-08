package com.hacker.boooks.service;

import com.hacker.boooks.bean.Book;
import com.hacker.boooks.bean.Publication;
import com.hacker.boooks.bean.PublicationProfile;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

/**
 * @apiNote Service class for everything related to publication
 */
@Service
public interface PublicationService {

    /**
     * @apiNote Add new publication
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Boolean> addPublication(String publicationName);

    /**
     * @apiNote Get publication details
     * @author [@thehackermonk]
     * @since 1.0
     */
    Publication getPublication(int publicationID);

    /**
     * @apiNote Get publication details
     * @author [@thehackermonk]
     * @since 1.0
     */
    Publication getPublication(String publicationName);

    /**
     * @apiNote Get all publications
     * @author [@thehackermonk]
     * @since 1.0
     */
    List<Publication> getPublications();

    /**
     * @apiNote Update publications
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Boolean> updatePublication(int publisherID, String newPublicationName);

    /**
     * @apiNote Remove publications
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Boolean> removePublication(String publicationName);

    /**
     * @apiNote Get books published by a publication
     * @author [@thehackermonk]
     * @since 1.0
     */
    List<Book> getBooksPublishedBy(String publicationName);

    /**
     * @apiNote Get count of books by genre published by a publication
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Integer> getBookCountGenreWise(String publicationName);

    /**
     * @apiNote Get publication profile
     * @author [@thehackermonk]
     * @since 1.0
     */
    PublicationProfile getPublicationProfile(String publicationName);

    /**
     * @apiNote Check if publication name already exists
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Boolean> checkPublication(String publicationName);

}
