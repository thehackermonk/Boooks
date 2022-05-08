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
@Controller
@RequestMapping(value = "/boooks/publication")
@SuppressWarnings("unused")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    /**
     * @param publicationName Name of the publication
     * @return true if addition succeeds, false otherwise
     * @apiNote Add new publication
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/add")
    @ResponseBody
    public Map<String, Boolean> addPublisher(@RequestHeader String publicationName) {
        return publicationService.addPublication(publicationName);
    }

    /**
     * @param publicationName name of the publication
     * @return List of books published by a publication
     * @apiNote Get books published by a publication
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/getpublishedbooks")
    @ResponseBody
    public List<Book> getBooksPublishedBy(@RequestHeader String publicationName) {
        return publicationService.getBooksPublishedBy(publicationName);
    }

    /**
     * @param publicationName name of the publication
     * @return Count of books genre wise which are published by a publication
     * @apiNote Get count of books genre wise which are published by a publication
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/getgenrewisebookcount")
    @ResponseBody
    public Map<String, Integer> getBookCountGenreWise(@RequestHeader String publicationName) {
        return publicationService.getBookCountGenreWise(publicationName);
    }

    /**
     * @param publicationName name of the publication
     * @return Publication profile
     * @apiNote Get publication profile
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/getprofile")
    @ResponseBody
    public PublicationProfile getPublicationProfile(@RequestHeader String publicationName) {
        return publicationService.getPublicationProfile(publicationName);
    }

    /**
     * @param publicationName name of the publication
     * @return Publication details
     * @apiNote Get publication details
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/getpublication")
    @ResponseBody
    public Publication getPublication(@RequestHeader String publicationName) {
        return publicationService.getPublication(publicationName);
    }

    /**
     * @return All publication names
     * @apiNote Get all publications
     * @author [@thehackermonk]
     * @since 1.0
     */
    @GetMapping("/getpublications")
    @ResponseBody
    public List<Publication> getPublications() {
        return publicationService.getPublications();
    }

    /**
     * @param publicationName name of the publication
     * @return true if publication removed successfully, false otherwise
     * @apiNote Remove publications
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/remove")
    @ResponseBody
    public Map<String, Boolean> removePublication(@RequestHeader String publicationName) {
        return publicationService.removePublication(publicationName);
    }

    /**
     * @param publisherID        Unique ID to identify the publisher
     * @param newPublicationName New name of the publisher
     * @return true if publication updated successfully, false otherwise
     * @apiNote Update publications
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/update")
    @ResponseBody
    public Map<String, Boolean> updatePublication(@RequestHeader int publisherID, @RequestHeader String newPublicationName) {
        return publicationService.updatePublication(publisherID, newPublicationName);
    }

    /**
     * @param publicationName Name of the publication
     * @return true if name doesn't exist, false otherwise
     * @apiNote Check if publication name already exists
     * @author [@thehackermonk]
     * @since 1.0
     */
    @PostMapping("/checkpublication")
    @ResponseBody
    public Map<String, Boolean> checkPublication(@RequestHeader String publicationName) {
        return publicationService.checkPublication(publicationName);
    }

}
