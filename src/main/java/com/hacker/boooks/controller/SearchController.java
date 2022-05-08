package com.hacker.boooks.controller;

import com.hacker.boooks.bean.SearchResult;
import com.hacker.boooks.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for search
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/boooks")
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
    @PostMapping("/search")
    @ResponseBody
    public List<SearchResult> search(@RequestHeader String keyword) {
        return searchService.search(keyword);
    }

}
