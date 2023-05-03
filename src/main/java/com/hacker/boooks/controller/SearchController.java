package com.hacker.boooks.controller;

import com.hacker.boooks.bean.SearchResult;
import com.hacker.boooks.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for search
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/search")
@CrossOrigin(origins = "*")
@SuppressWarnings("unused")
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * @param keyword Keyword to search
     * @return Search results
     * @apiNote Search entire application
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @GetMapping("/{keyword}")
//    public List<SearchResult> search(@PathVariable String keyword) {
//        return searchService.search(keyword);
//    }

}
