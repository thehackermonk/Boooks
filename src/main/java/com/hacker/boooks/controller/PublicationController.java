package com.hacker.boooks.controller;

import com.hacker.boooks.bean.Book;
import com.hacker.boooks.bean.Publication;
import com.hacker.boooks.bean.PublicationProfile;
import com.hacker.boooks.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for everything related to publisher
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/publications")
@CrossOrigin(origins = "*")
@SuppressWarnings("unused")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    /**
     * @param name Name of the publication
     * @return true if addition succeeds, false otherwise
     * @apiNote Add new publication
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @PostMapping("/{name}/books")
//    public Map<String, Boolean> addPublisher(@PathVariable String name) {
//        return publicationService.addPublication(name);
//    }

    /**
     * @param name name of the publication
     * @return List of books published by a publication
     * @apiNote Get books published by a publication
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @GetMapping("/{name}/books")
//    public List<Book> getBooksPublishedBy(@PathVariable String name) {
//        return publicationService.getBooksPublishedBy(name);
//    }

    /**
     * @param name name of the publication
     * @return Count of books genre wise which are published by a publication
     * @apiNote Get count of books genre wise which are published by a publication
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @GetMapping("/{name}/books/genre-count")
//    public Map<String, Integer> getBookCountGenreWise(@PathVariable String name) {
//        return publicationService.getBookCountGenreWise(name);
//    }

    /**
     * @param name name of the publication
     * @return Publication profile
     * @apiNote Get publication profile
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @GetMapping("/{name}/profile")
//    public PublicationProfile getPublicationProfile(@PathVariable String name) {
//        return publicationService.getPublicationProfile(name);
//    }

    /**
     * @param name name of the publication
     * @return Publication details
     * @apiNote Get publication details
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @PostMapping("/{name}")
//    public Publication getPublication(@PathVariable String name) {
//        return publicationService.getPublication(name);
//    }

    /**
     * @return All publication names
     * @apiNote Get all publications
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @GetMapping("")
//    public List<Publication> getPublications() {
//        return publicationService.getPublications();
//    }

    /**
     * @param name name of the publication
     * @return true if publication removed successfully, false otherwise
     * @apiNote Remove publications
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @DeleteMapping("/{name}")
//    public Map<String, Boolean> removePublication(@PathVariable String name) {
//        return publicationService.removePublication(name);
//    }

    /**
     * @param oldName        Unique ID to identify the publisher
     * @param newPublicationName New name of the publisher
     * @return true if publication updated successfully, false otherwise
     * @apiNote Update publications
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @PutMapping("/{oldName}")
//    public Map<String, Boolean> updatePublication(@PathVariable String oldName, @RequestHeader String newPublicationName) {
//        return publicationService.updatePublication(publisherID, newPublicationName);
//        return null;
//    }

    /**
     * @param name Name of the publication
     * @return true if name doesn't exist, false otherwise
     * @apiNote Check if publication name already exists
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @GetMapping("/check-publication/{name}")
//    public Map<String, Boolean> checkPublication(@PathVariable String name) {
//        return publicationService.checkPublication(name);
//    }

}
